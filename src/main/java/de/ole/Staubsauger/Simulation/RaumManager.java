package de.ole.Staubsauger.Simulation;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.Random;

public class RaumManager {
    Random r = new Random();

    ArrayList<Hinderniss> hindernisse = new ArrayList<>();
    ArrayList<Schmutz> schmutzTeilchen = new ArrayList<>();
    Ladestation ladestation;



    int breite, hoehe;

    public void erstelleRaum(int breite, int hoehe, int anzahlSchmutz) {
        this.breite = breite;
        this.hoehe = hoehe;
        Hinderniss wand1 = new Wand(0, 0, 10, hoehe);
        Hinderniss wand2 = new Wand(0, 0, breite, 10);
        Hinderniss wand3 = new Wand(0, hoehe - 10, breite, 10);
        Hinderniss wand4 = new Wand(breite - 10, 0, 10, hoehe);

        hindernisse.add(wand1);
        hindernisse.add(wand2);
        hindernisse.add(wand3);
        hindernisse.add(wand4);

        for (int i = 0; i < anzahlSchmutz; i++) {
            double randomX = r.nextInt(breite);
            double randomY = r.nextInt(hoehe);
            schmutzTeilchen.add(new Schmutz(randomX, randomY));
        }

        ladestation = new Ladestation(breite / 2, 20);

        Circle roboter = new Circle(200, 200, 20);
        roboter.setFill(Color.BLUE);
    }

    public ArrayList<Hinderniss> getHindernisse() {
        return hindernisse;
    }

    public ArrayList<Schmutz> getSchmutzTeilchen() {
        return schmutzTeilchen;
    }

    public Ladestation getLadestation() {
        return ladestation;
    }

}
