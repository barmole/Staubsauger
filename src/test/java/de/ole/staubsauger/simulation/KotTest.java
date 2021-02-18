package de.ole.staubsauger.simulation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KotTest {

    Kot kot = new Kot(7.0,4.0);

    @Test
    void getPosX() {
        assertEquals(7.0, kot.getPosX());
        assertNotEquals(2.0, kot.getPosX());
    }

    @Test
    void getPosY() {
        assertEquals(4.0, kot.getPosY());
        assertNotEquals(5.0, kot.getPosY());
    }

    @Test
    void getBreite() {
        assertEquals(5, kot.getBreite());
        assertNotEquals(6, kot.getBreite());
    }

    @Test
    void getHoehe() {
        assertEquals(5, kot.getHoehe());
        assertNotEquals(6, kot.getHoehe());
    }
}