package de.ole.staubsauger.simulation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class WandTest {
    Hinderniss wand = new Wand(1.0,3.0,100.0,200.0);

    @Test
    void getPosX() {
        assertEquals(1.0, wand.getPosX());
        assertNotEquals(2.0, wand.getPosX());
    }

    @Test
    void getPosY() {
        assertEquals(3.0, wand.getPosY());
        assertNotEquals(1.0, wand.getPosY());
    }

    @Test
    void getBreite() {
        assertEquals(100.0, wand.getBreite());
        assertNotEquals(200.0, wand.getBreite());
    }

    @Test
    void getHoehe() {
        assertEquals(200.0, wand.getHoehe());
        assertNotEquals(100.0, wand.getHoehe());
    }
}