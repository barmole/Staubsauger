package de.ole.staubsauger.simulation;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RaumManager {

    Random r = new Random();

    ArrayList<Hinderniss> hindernisse = new ArrayList<>();
    ArrayList<Schmutz> schmutzTeilchen = new ArrayList<>();
    Ladestation ladestation;


    int breite;
    int hoehe;

    /**
     * Erstellt einen Raum
     * @param breite Breite des Raumes in Pixel
     * @param hoehe Höhe des Raumes in Pixel
     * @param anzahlSchmutz anzahl an Schmutzteilchen, weclhe am Start erzeugt werden
     */
    public void erstelleRaum(int breite, int hoehe, int anzahlSchmutz) {
        this.breite = breite;
        this.hoehe = hoehe;
        Hinderniss wand1 = new Wand(0, 0, 10, hoehe);
        Hinderniss wand2 = new Wand(0, 0, breite, 10);
        Hinderniss wand3 = new Wand(0, hoehe - 10.0, breite, 10);
        Hinderniss wand4 = new Wand(breite - 10.0, 0, 10, hoehe);

        hindernisse.add(wand1);
        hindernisse.add(wand2);
        hindernisse.add(wand3);
        hindernisse.add(wand4);

        streueSchmutz(anzahlSchmutz);

        ladestation = new Ladestation(breite / 2.0, 20);

        Circle roboter = new Circle(200, 200, 20);
        roboter.setFill(Color.BLUE);
    }

    /**
     * gibt eine Liste aller Hindernisse zurück
     * @return List
     */
    public List<Hinderniss> getHindernisse() {
        return hindernisse;
    }

    /**
     * gibt eine Liste aller Schmutzteilchen zurück
     * @return List
     */
    public List<Schmutz> getSchmutzTeilchen() {
        return schmutzTeilchen;
    }

    /**
     * gibt die Ladestation zurück
     * @return ladestation
     */
    public Ladestation getLadestation() {
        return ladestation;
    }

    /**
     * gibt die Breite des Raumes in Pixel zurück
     * @return breite
     */
    public int getBreite() {
        return breite;
    }

    /**
     * gibt die Höhe des Raumes in Pixel zurück
     * @return höhe
     */
    public int getHoehe() {
        return hoehe;
    }

    /**
     * Löscht alle Schmutzteile
     */
    public void loescheSchmutz() {
        schmutzTeilchen.clear();
    }

    /**
     * Erzeugt Schmutzteilchen an zufälligen Positionen im Raum
     * @param anzahl Anzahl, wieviel Schmutz erzeugt werden soll
     */
    public void streueSchmutz(int anzahl) {
        for (int i = 0; i < anzahl; i++) {
            double randomX = r.nextInt(breite);
            double randomY = r.nextInt(hoehe);
            schmutzTeilchen.add(new Schmutz(randomX, randomY));
        }
    }

    /**
     * Erzeugt einen Haufen Kot an einer zufälligen Postion
     */
    public void legeKot() {
        double randomX = r.nextInt(breite);
        double randomY = r.nextInt(hoehe);
        hindernisse.add(new Kot(randomX, randomY));
    }

}
