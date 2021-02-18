package de.ole.staubsauger.simulation;

public class Ladestation {

    double posX;
    double posY;

    public Ladestation(double posX, double posY) {
        this.posX = posX;
        this.posY = posY;
    }

    /**
     * Gibt die X-Position der Ladestation zurück
     * @return posX
     */
    public double getPosX() {
        return posX;
    }

    /**
     * Gibt die Y-Position der Ladestation zurück
     * @return posY
     */
    public double getPosY() {
        return posY;
    }
}
