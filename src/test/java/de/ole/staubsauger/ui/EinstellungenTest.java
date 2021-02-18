package de.ole.staubsauger.ui;

import de.ole.staubsauger.simulation.Kot;
import de.ole.staubsauger.simulation.RaumManager;
import de.ole.staubsauger.simulation.Roboter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EinstellungenTest {

    Roboter roboter = new Roboter(100, 100, 0);
    RaumManager raum = new RaumManager();
    Einstellungen einstellungen = new Einstellungen(roboter, raum);

    @Test
    void loeschenSchmutz() {
        einstellungen.loeschenSchmutz();
        assertEquals(0, raum.getSchmutzTeilchen().size());
    }

    @Test
    void legeKot() {
        raum.erstelleRaum(100,100,200);

        einstellungen.legeKot();
        assertEquals(Kot.class, raum.getHindernisse().get(raum.getHindernisse().size() - 1).getClass());
    }
}