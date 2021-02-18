package de.ole.staubsauger.simulation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class Roboter {

    public boolean laserAn;
    public boolean stationGefunden;
    public boolean stehtAufLadestation;
    public String putzTag = "";
    public int putzStunde;
    public int putzMinute;

    private double posX;
    private double posY;
    private double rotation;
    private double zielRotation;
    private double batteriestand;
    private double beutelinhalt;
    private double reparaturstatus;
    private double geschwindigkeit = 1;
    private static final double ZEIT_BEI_VOLLEM_AKKU = 300;

    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("EEEE");

    Random r = new Random();
    Status status;
    private boolean kotstop;

    /**
     * Erzeugt einen Roboter im Raum
     * @param posX Start X-Position des Roboters
     * @param posY Start Y-Position des Roboters
     * @param rotation Anfangsrotation
     */
    public Roboter(double posX, double posY, double rotation) {
        this.posX = posX;
        this.posY = posY;
        this.rotation = rotation;

        status = Status.IDLE;
        batteriestand = 1;
        beutelinhalt = 0;
        reparaturstatus = 1;
    }

    /**
     * Berchnet, was der Roboter bei welchem Status ausführen soll
     * @param manager RaumManager
     */
    public void berechne(RaumManager manager) {
        switch (status) {
            case IDLE:
                ueberpruefePutztag();
                break;

            case FAHREN:
                berechneFahren(manager);
                break;

            case DREHENRECHTS:
                dreheRechtsBisZiel();
                break;

            case DREHENLINKS:
                dreheLinksBisZiel();
                break;

            case LADEN:
                lade();
                break;

            case RUECKWEG:
                geheZurueck(manager);
                break;

            case RAUMSCAN:
                scanneRaum();
                break;

            case KOTSTOP:
                break;
        }

    }

    /**
     * Checkt, ob jetzt ein Putztag ist und wenn, dann wird der Status auf FAHREN gestzt
     */
    private void ueberpruefePutztag() {

        if (putzTag.equals(LocalDateTime.now().format(dateFormat)) &&
                putzStunde == LocalDateTime.now().getHour() &&
                putzMinute == LocalDateTime.now().getMinute()) {
            status = Status.FAHREN;
        }
    }

    /**
     * Fährt eine Einheit nach vorne, wenn alles Frei ist.
     * Wenn der Roboter mit Kot kollidirt wird der Status auf KOTSTOP gestzt
     * Wenn Bateriestand kleiner gleich 25% ist, wird der Status auf RUECKWEG gestzt
     * @param manager RaumManager
     */
    private void berechneFahren(RaumManager manager) {
        if (kollision(manager)) {
            if (kotstop){
                status = Status.KOTSTOP;
                return;
            }

            fahre(-1);
            zielRotation = r.nextInt(360);
            if (rotation < zielRotation)
                status = Status.DREHENRECHTS;
            else
                status = Status.DREHENLINKS;

        } else {
            ueberpruefeSchmutz(manager);
            fahre(1);
        }


        if (batteriestand <= 0.25) status = Status.RUECKWEG;
    }

    /**
     * dreht sich nach rechts, bis die Zielrotation erreicht ist, danach wird der Status auf FAHREN gesetzt
     */
    private void dreheRechtsBisZiel() {
        if (rotation < zielRotation)
            dreheRechts(2);
        else
            status = Status.FAHREN;
    }

    /**
     * dreht sich nach links, bis die Zielrotation erreicht ist, danach wird der Status auf FAHREN gesetzt
     */
    private void dreheLinksBisZiel() {
        if (rotation > zielRotation)
            dreheLinks(2);
        else
            status = Status.FAHREN;
    }

    /**
     * lädt die Batterie, bis diese voll ist und setzt dann den Status auf IDLE
     */
    private void lade() {
        if (batteriestand < 1) {
            batteriestand += 0.0001 * geschwindigkeit;
        } else {
            batteriestand = 1;
            status = Status.IDLE;
        }
    }

    /**
     * Schaut sich nach der Ladestation um und fährt dann zu dieser hin
     * @param manager RaumManager
     */
    private void geheZurueck(RaumManager manager) {
        if (!stationGefunden) {
            if (rotation > 0) {
                zielRotation = 0;
                status = Status.DREHENLINKS;
            } else {
                status = Status.RAUMSCAN;
            }
        } else if (!stehtAufLadestation) {
            if (!kollision(manager)) {
                fahre(1);
            } else {
                stationGefunden = false;
                fahre(-1);
                status = Status.RAUMSCAN;
            }
        } else if (rotation > 0) {
            dreheLinks(2);
        } else {
            stationGefunden = false;
            stehtAufLadestation = false;
            status = Status.LADEN;
        }
    }

    /**
     * Setzt laser auf true und dreht sich um eins nach rechts
     */
    private void scanneRaum() {
        laserAn = true;
        if (rotation < 360 && !stationGefunden) {
            dreheRechts(1 / geschwindigkeit);
        }
        else {
            laserAn = false;
            status = Status.RUECKWEG;
        }
    }

    /**
     * der Rotationswinkel wird erhöht
     * @param v v
     */
    private void dreheRechts(double v) {
        rotation += v * geschwindigkeit;
    }

    /**
     * der Rotationswinkel wird verkleinert
     * @param v v
     */
    private void dreheLinks(double v) {
        rotation -= v * geschwindigkeit;
    }

    /**
     * fährt nach vorne
     * @param strecke strecke, welche zurückgelgt werden soll
     */
    private void fahre(double strecke) {
        if (batteriestand > 0.01 && reparaturstatus > 0.01) {
            posX -= cos(Math.toRadians(rotation - 90)) * strecke * geschwindigkeit;
            posY -= sin(Math.toRadians(rotation - 90)) * strecke * geschwindigkeit;
            batteriestand -= 0.0001 * geschwindigkeit;
        }
    }

    /**
     * Überprüft die Kollision des Roboters
     * @param manager RaumManager
     * @return boolean Ob Kollision vorhanden ist
     */
    boolean kollision(RaumManager manager) {
        for (Hinderniss hinderniss : manager.getHindernisse()) {
            if (hinderniss.getPosX() <= posX + 20 &&
                    hinderniss.getPosX() + hinderniss.getBreite() >= posX - 20 &&
                    hinderniss.getPosY() <= posY + 20 &&
                    hinderniss.getPosY() + hinderniss.getHoehe() >= posY - 20) {
                reparaturstatus -= 0.001;
                if(hinderniss.getClass() == Kot.class){
                    kotstop = true;
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Entfernt schmutz, wo der Roboter rüberfährt
     * @param manager RaumManager
     */
    void ueberpruefeSchmutz(RaumManager manager) {
        if (beutelinhalt < 1) {
            ArrayList<Schmutz> temp = new ArrayList<>(manager.getSchmutzTeilchen());
            for (Schmutz schmutz : manager.getSchmutzTeilchen()) {
                if (schmutz.getPosX() <= posX + 20 &&
                        schmutz.getPosX() >= posX - 20 &&
                        schmutz.getPosY() <= posY + 20 &&
                        schmutz.getPosY() >= posY - 20) {
                    temp.remove(schmutz);
                    beutelinhalt += 0.001;
                }
            }
            manager.schmutzTeilchen = temp;
        }
    }

    /**
     * Gibt die X-Position des Roboters zurück
     * @return posX
     */
    public double getPosX() {
        return posX;
    }

    /**
     * Gibt die Y-Position des Roboters zurück
     * @return posY
     */
    public double getPosY() {
        return posY;
    }

    /**
     * Gibt die Rotation des Roboters zurück
     * @return rotation
     */
    public double getRotation() {
        return rotation;
    }

    /**
     * Gibt den Status des Roboters zurück
     * @return status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Gibt den Batteriestand des Roboters zurück
     * @return batteriestand
     */
    public double getBatteriestand() {
        return batteriestand;
    }

    /**
     * Gibt den Beutelinhalt des Roboters zurück
     * @return beutelinhalt
     */
    public double getBeutelinhalt() {
        return beutelinhalt;
    }

    /**
     * Gibt den Reparaturstatus des Roboters zurück
     * @return reparaturstatus
     */
    public double getReparaturstatus() {
        return reparaturstatus;
    }

    /**
     * Gibt die Restzeit des Roboters zurück
     * Die Restzeit ergibt sich aus Bateriestand und Zeit
     * @return restzeit
     */
    public double getRestzeit() {
        return batteriestand * ZEIT_BEI_VOLLEM_AKKU;
    }

    /**
     * Setzt den Status des Roboters
     * @param status Der Status der gesetzt werden soll
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Setzt die Geschwindigkeit des Roboters zurück
     * @param geschwindigkeit Die Geschwindigkeit des Roboters, die gesetzt werden soll
     */
    public void setGeschwindigkeit(double geschwindigkeit) {
        this.geschwindigkeit = geschwindigkeit;
    }

    /**
     * Setzt den Beutelinhalt des Roboters auf 0
     */
    public void leereStaubfach() {
        beutelinhalt = 0;
    }

    /**
     * Gibt die Geschwindigkeit zurück
     * @return geschwindigkeit
     */
    public double getGeschwindigkeit() {
        return geschwindigkeit;
    }
}
