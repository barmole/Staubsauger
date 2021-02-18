package de.ole.staubsauger.simulation;

public class Ladestation {
    double posX;
    double posY;

    public Ladestation(double posX, double posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }
}
