package de.ole.Staubsauger.Simulation;

public class Roboter {
    double posX, posY, rotation;

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

    public void berechne() {
        posY++;
    }
}
