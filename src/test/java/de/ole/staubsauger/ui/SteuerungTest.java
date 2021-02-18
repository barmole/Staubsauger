package de.ole.staubsauger.ui;

import de.ole.staubsauger.simulation.Roboter;
import de.ole.staubsauger.simulation.Status;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SteuerungTest {

    Roboter roboter = new Roboter(100, 100, 0);
    Steuerung steuerung = new Steuerung(roboter);

    @Test
    void leerenStaubfach() {
        steuerung.leerenStaubfach();
        assertEquals(0, roboter.getBeutelinhalt());
    }

    @Test
    void aufladen() {
        steuerung.aufladen();
        assertEquals(Status.RUECKWEG, roboter.getStatus());
    }

}