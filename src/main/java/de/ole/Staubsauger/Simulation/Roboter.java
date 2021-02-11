package de.ole.Staubsauger.Simulation;

import java.util.Random;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class Roboter {
    double posX, posY, rotation, zielRotation;
    double geschwindigkeit = 1;
    Random r = new Random();
    Status status = Status.IDLE;

    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }

    public double getRotation() {
        return rotation;
    }

    public Roboter(double posX, double posY, double rotation) {
        this.posX = posX;
        this.posY = posY;
        this.rotation = rotation;
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
        }
    }

    private void fahre(double strecke) {
        posX -= cos(Math.toRadians(rotation - 90)) * strecke * geschwindigkeit;
        posY -= sin(Math.toRadians(rotation - 90)) * strecke * geschwindigkeit;
    }

    boolean kollision(RaumManager manager) {
        for (Hinderniss hinderniss : manager.getHindernisse()) {
            if (hinderniss.getPosX() <= posX + 20 &&
                    hinderniss.getPosX() + hinderniss.getBreite() >= posX - 20 &&
                    hinderniss.getPosY() <= posY + 20 &&
                    hinderniss.getPosY() + hinderniss.getHoehe() >= posY - 20) {
                return true;
            }
        }
        return false;
    }

    void ueberpruefeSchmutz(RaumManager manager) {
        manager.getSchmutzTeilchen().removeIf(schmutz ->
                schmutz.getPosX() <= posX + 20 &&
                        schmutz.getPosX() >= posX - 20 &&
                        schmutz.getPosY() <= posY + 20 &&
                        schmutz.getPosY() >= posY - 20);
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setGeschwindigkeit(double geschwindigkeit) {
        this.geschwindigkeit = geschwindigkeit;
    }
}
