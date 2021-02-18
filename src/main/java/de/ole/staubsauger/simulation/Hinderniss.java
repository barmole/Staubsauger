package de.ole.staubsauger.simulation;

public abstract class Hinderniss {
    protected double posX;
    protected double posY;
    protected double breite;
    protected double hoehe;

    /**
     * Gibt die X-Position des Hindernisses zurück
     * @return posX
     */
    public double getPosX() {
        return posX;
    }

    /**
     * Gibt die Y-Position des Hindernisses zurück
     * @return posY
     */
    public double getPosY() {
        return posY;
    }

    /**
     * Gibt die Breite des Hindernisses zurück
     * @return breite
     */
    public double getBreite() {
        return breite;
    }

    /**
     * Gibt die Höhe des Hindernisses zurück
     * @return hoehe
     */
    public double getHoehe() {
        return hoehe;
    }
}
