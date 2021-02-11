package de.ole.Staubsauger.Simulation;

import java.util.Random;

public class Roboter {
    double posX, posY, rotation, zielRotation;
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
                    zielRotation = r.nextInt(360);
                    status = Status.DREHEN;
                } else {
                    posY++;
                }
                break;

            case DREHEN:
                if (rotation < zielRotation) {
                    rotation++;
                }else if(rotation>zielRotation){
                    rotation--;
                }else {
                    status = Status.FAHREN;
                }
                break;
        }
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    boolean kollision(RaumManager manager) {
        for (Hinderniss hinderniss : manager.getHindernisse()) {
            if (hinderniss.getPosX() <= posX + 20 &&
                    hinderniss.getPosX() + hinderniss.getBreite() >= posX &&
                    hinderniss.getPosY() <= posY + 20 &&
                    hinderniss.getPosY() + hinderniss.getHoehe() >= posY) {
                return true;
            }
        }
        return false;
    }
}
