package de.ole.Staubsauger.Simulation;

public class Kot extends Hinderniss{
    private final double posX;
    private final double posY;
    private final double breite;
    private final double hoehe;

    public Kot(double posX, double posY) {
        this.posX = posX;
        this.posY = posY;
        this.breite = 5;
        this.hoehe = 5;
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
