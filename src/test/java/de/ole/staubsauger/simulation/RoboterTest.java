package de.ole.staubsauger.simulation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoboterTest {
    Roboter roboter;
    RaumManager manager;

    @BeforeEach
    void setUp() {
        roboter = new Roboter(100, 200, 0);
        manager = new RaumManager();
        manager.erstelleRaum(400, 1000, 2000);
    }

    @Test
    void initial() {
        assertEquals(100, roboter.getPosX());
        assertNotEquals(200, roboter.getPosX());

        assertEquals(200, roboter.getPosY());
        assertNotEquals(100, roboter.getPosY());
    }

    @Test
    void getPosX() {
        roboter.setStatus(Status.FAHREN);
        roboter.berechne(manager);
        assertEquals(100, roboter.getPosX());
    }

    @Test
    void getPosY() {
        roboter.setStatus(Status.FAHREN);
        roboter.berechne(manager);
        assertEquals(201, roboter.getPosY());
    }

    @Test
    void getRotation() {
        assertEquals(0, roboter.getRotation());
    }

    @Test
    void getStatus() {
        assertEquals(Status.IDLE, roboter.getStatus());
        assertNotEquals(Status.FAHREN, roboter.getStatus());
    }

    @Test
    void getBatteriestand() {
        assertEquals(1, roboter.getBatteriestand());

        roboter.setStatus(Status.FAHREN);
        roboter.berechne(manager);

        assertEquals(0.9999, roboter.getBatteriestand());
    }

    @Test
    void getBeutelinhalt() {
        assertEquals(0.0, roboter.getBeutelinhalt());
        assertNotEquals(10.0, roboter.getBeutelinhalt());

        roboter.setStatus(Status.FAHREN);
        roboter.berechne(manager);

        assertTrue( 0.0 < roboter.getBeutelinhalt());
    }

    @Test
    void getReparaturstatus() {
        assertEquals(1, roboter.getReparaturstatus());
    }

    @Test
    void getRestzeit() {
        assertEquals(300, roboter.getRestzeit());
    }

    @Test
    void setStatus() {
        roboter.setStatus(Status.IDLE);
        assertEquals(Status.IDLE, roboter.getStatus());
        assertNotEquals(Status.FAHREN, roboter.getStatus());
    }

    @Test
    void setGeschwindigkeit() {
        roboter.setGeschwindigkeit(100);
        assertEquals(100, roboter.getGeschwindigkeit());
    }

    @Test
    void leereStaubfach() {
        manager.erstelleRaum(500, 500, 2000);
        roboter.setStatus(Status.FAHREN);
        roboter.berechne(manager);

        assert 0.0 < roboter.getBeutelinhalt();

        roboter.leereStaubfach();

        assertEquals(0, roboter.getBeutelinhalt());
    }
}