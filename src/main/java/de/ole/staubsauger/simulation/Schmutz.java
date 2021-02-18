package de.ole.staubsauger.simulation;

public class Schmutz {

    double posX;
    double posY;

    public Schmutz(double posX, double posY) {
        this.posX = posX;
        this.posY = posY;
    }

    /**
     * Gibt die X-Position des Schmutzes zurück
     * @return posX
     */
    public double getPosX() {
        return posX;
    }

    /**
     * Gibt die Y-Position des Schmutzes zurück
     * @return posY
     */
    public double getPosY() {
        return posY;
    }

}
