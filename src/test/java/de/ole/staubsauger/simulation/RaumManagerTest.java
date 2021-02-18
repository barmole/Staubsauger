package de.ole.staubsauger.simulation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RaumManagerTest {
    RaumManager manager = new RaumManager();

    @Test
    void erstelleRaum() {
        manager.erstelleRaum(100, 200, 300);
        assertEquals(100, manager.breite);
        assertEquals(200, manager.hoehe);
    }

    @Test
    void getHindernisse() {
        manager.erstelleRaum(100, 200, 300);
        var hindernisse = manager.getHindernisse();
        assertEquals(Wand.class, hindernisse.get(0).getClass());
    }

    @Test
    void getSchmutzTeilchen() {
        manager.erstelleRaum(100, 200, 300);
        assertEquals(300, manager.getSchmutzTeilchen().size());
    }

    @Test
    void getLadestation() {
        manager.erstelleRaum(100, 200, 300);
        assertEquals(Ladestation.class, manager.getLadestation().getClass());
    }

    @Test
    void getBreite() {
        manager.erstelleRaum(100, 200, 300);
        assertEquals(100.0, manager.getBreite());
        assertNotEquals(200.0, manager.getBreite());
    }

    @Test
    void getHoehe() {
        manager.erstelleRaum(100, 200, 300);
        assertEquals(200.0, manager.getHoehe());
        assertNotEquals(100.0, manager.getHoehe());
    }

    @Test
    void loescheSchmutz() {
        manager.erstelleRaum(100, 200, 300);
        manager.loescheSchmutz();
        assertEquals(0, manager.getSchmutzTeilchen().size());
    }

    @Test
    void streueSchmutz() {
        manager.erstelleRaum(100, 200, 300);
        manager.streueSchmutz(10);
        assertEquals(310, manager.getSchmutzTeilchen().size());
    }

    @Test
    void legeKot() {
        manager.erstelleRaum(100, 200, 300);
        manager.legeKot();
        assertEquals(Kot.class, manager.getHindernisse().get(manager.getHindernisse().size() - 1).getClass());
    }
}