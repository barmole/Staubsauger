package de.ole.Staubsauger.Simulation;

import java.util.ArrayList;
import java.util.Random;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class Roboter {
    private double posX, posY, rotation, zielRotation;
    private double batteriestand, beutelinhalt, reparaturstatus;
    private double geschwindigkeit = 1;
    private final double zeitBeiVollemAkku = 300;
    Random r = new Random();
    Status status;

    public Roboter(double posX, double posY, double rotation) {
        this.posX = posX;
        this.posY = posY;
        this.rotation = rotation;

        batteriestand = 1;
        beutelinhalt = 0;
        reparaturstatus = 1;
    }


    public void berechne(RaumManager manager) {

        switch (status) {
            case FAHREN:
                if (kollision(manager)) {
                    fahre(-1);
                    zielRotation = r.nextInt(360);
                    System.out.println(zielRotation);
                    if (rotation < zielRotation)
                        status = Status.DREHENRECHTS;
                    else
                        status = Status.DREHENLINKS;
                } else {
                    ueberpruefeSchmutz(manager);
                    fahre(1);
                }
                break;

            case DREHENRECHTS:
                if (rotation < zielRotation)
                    rotation += 2 * geschwindigkeit;
                else
                    status = Status.FAHREN;

                break;

            case DREHENLINKS:
                if (rotation > zielRotation)
                    rotation -= 2 * geschwindigkeit;
                else
                    status = Status.FAHREN;

                break;

            case LADEN:
                if (batteriestand < 1) {
                    batteriestand += 0.001 * geschwindigkeit;
                    System.out.println(batteriestand);
                } else {
                    status = Status.IDLE;
                }
        }

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
                reparaturstatus -= 0.01;
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
        return batteriestand * zeitBeiVollemAkku;
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
}
