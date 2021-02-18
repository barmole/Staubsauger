package de.ole.staubsauger.simulation;

public enum Status {
    /**
     * Der Roboter hat nichts zu tun
     */
    IDLE,

    /**
     * Der Roboter fährt nach vorne
     */
    FAHREN,
    DREHENRECHTS,
    DREHENLINKS,
    RAUMSCAN,
    RUECKWEG,
    LADEN,
    KOTSTOP;

}
