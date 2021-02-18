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

    public Roboter(double posX, double posY, double rotation) {
        this.posX = posX;
        this.posY = posY;
        this.rotation = rotation;

        status = Status.IDLE;
        batteriestand = 1;
        beutelinhalt = 0;
        reparaturstatus = 1;
    }


    public void berechne(RaumManager manager) {
        switch (status) {
            case IDLE:
                idle();
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

    private void idle() {

        if (putzTag.equals(LocalDateTime.now().format(dateFormat)) &&
                putzStunde == LocalDateTime.now().getHour() &&
                putzMinute == LocalDateTime.now().getMinute()) {
            status = Status.FAHREN;
        }
    }

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

    private void dreheRechtsBisZiel() {
        if (rotation < zielRotation)
            dreheRechts(2);
        else
            status = Status.FAHREN;
    }

    private void dreheLinksBisZiel() {
        if (rotation > zielRotation)
            dreheLinks(2);
        else
            status = Status.FAHREN;
    }

    private void lade() {
        if (batteriestand < 1) {
            batteriestand += 0.0001 * geschwindigkeit;
        } else {
            batteriestand = 1;
            status = Status.IDLE;
        }
    }

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

    private void scanneRaum() {
        laserAn = true;
        if (rotation < 360 && !stationGefunden) {
            dreheRechts(1 / geschwindigkeit);
        } else {
            laserAn = false;
            status = Status.RUECKWEG;
        }
    }

    private void dreheRechts(double v) {
        rotation += v * geschwindigkeit;
    }

    private void dreheLinks(double v) {
        rotation -= v * geschwindigkeit;
    }

    private void fahre(double strecke) {
        if (batteriestand > 0.01 && reparaturstatus > 0.01) {
            posX -= cos(Math.toRadians(rotation - 90)) * strecke * geschwindigkeit;
            posY -= sin(Math.toRadians(rotation - 90)) * strecke * geschwindigkeit;
            batteriestand -= 0.0001 * geschwindigkeit;
        }
    }

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

    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }

    public double getRotation() {
        return rotation;
    }

    public Status getStatus() {
        return status;
    }

    public double getBatteriestand() {
        return batteriestand;
    }

    public double getBeutelinhalt() {
        return beutelinhalt;
    }

    public double getReparaturstatus() {
        return reparaturstatus;
    }

    public double getRestzeit() {
        return batteriestand * ZEIT_BEI_VOLLEM_AKKU;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setGeschwindigkeit(double geschwindigkeit) {
        this.geschwindigkeit = geschwindigkeit;
    }

    public void leereStaubfach() {
        beutelinhalt = 0;
    }

    public double getGeschwindigkeit() {
        return geschwindigkeit;
    }
}
