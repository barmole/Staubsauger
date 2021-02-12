package de.ole.Staubsauger.Simulation;

public class Wand extends Hinderniss {
    private final double posX;
    private final double posY;
    private final double breite;
    private final double hoehe;

    public Wand(double posX, double posY, double breite, double hoehe) {
        this.posX = posX;
        this.posY = posY;
        this.breite = breite;
        this.hoehe = hoehe;
    }


    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }

    public double getBreite() {
        return breite;
    }

    public double getHoehe() {
        return hoehe;
    }
}
