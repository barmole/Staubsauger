package de.ole.staubsauger.simulation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LadestationTest {
Ladestation ladestation = new Ladestation(100,200);
    @Test
    void getPosX() {
        assertEquals(100.0, ladestation.getPosX());
        assertNotEquals(200.0, ladestation.getPosX());
    }

    @Test
    void getPosY() {
      assertEquals(200.0, ladestation.getPosY());
      assertNotEquals(1234.0, ladestation.getPosY());
    }
}