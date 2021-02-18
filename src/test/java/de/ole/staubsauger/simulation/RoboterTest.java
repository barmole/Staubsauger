package de.ole.staubsauger.simulation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class RoboterTest {
    Roboter roboter = new Roboter(100, 200, 0);
    RaumManager manager = new RaumManager();

    @Test
    void berechne() {
    }

    @Test
    void kollision() {
    }

    @Test
    void ueberpruefeSchmutz() {
    }

    @Test
    void getPosX() {
        assertEquals(100, roboter.getPosX());
        assertNotEquals(200, roboter.getPosX());

        manager.erstelleRaum(100, 100, 100);
        roboter.setStatus(Status.FAHREN);
        roboter.berechne(manager);

        assertEquals(100, roboter.getPosX());
    }

    @Test
    void getPosY() {
        assertEquals(200, roboter.getPosY());
        assertNotEquals(100, roboter.getPosY());

        manager.erstelleRaum(100, 1000, 100);
        roboter.setStatus(Status.FAHREN);
        roboter.berechne(manager);

        assertEquals(199, roboter.getPosY());
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

        manager.erstelleRaum(100, 1000, 100);
        roboter.setStatus(Status.FAHREN);
        roboter.berechne(manager);

        assertEquals(0.9999, roboter.getBatteriestand());
    }

    @Test
    void getBeutelinhalt() {
        assertEquals(0.0, roboter.getBeutelinhalt());
        assertNotEquals(10.0, roboter.getBeutelinhalt());

        manager.erstelleRaum(500, 500, 2000);
        roboter.setStatus(Status.FAHREN);
        roboter.berechne(manager);

        assert 0.0 < roboter.getBeutelinhalt();

    }

    @Test
    void getReparaturstatus() {
        assertEquals(1, roboter.getReparaturstatus());
    }

    @Test
    void getRestzeit() {
    }

    @Test
    void setStatus() {
        roboter.setStatus(Status.IDLE);
        assertEquals(Status.IDLE, roboter.getStatus());
        assertNotEquals(Status.FAHREN, roboter.getStatus());
    }

    @Test
    void setGeschwindigkeit() {

    }

    @Test
    void leereStaubfach() {

    }
}