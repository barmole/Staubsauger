package de.ole.staubsauger.simulation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SchmutzTest {
Schmutz schmutz = new Schmutz(200,700);
    @Test
    void getPosX() {
        assertEquals(200.0, schmutz.getPosX());
        assertNotEquals(700.0, schmutz.getPosX());
    }

    @Test
    void getPosY() {
        assertEquals(700.0, schmutz.getPosY());
        assertNotEquals(200.0, schmutz.getPosY());
    }
}