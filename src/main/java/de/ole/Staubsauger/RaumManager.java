package de.ole.Staubsauger;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Random;

public class RaumManager {
    Random r = new Random();

    ArrayList<Hinderniss> hindernisse = new ArrayList<>();
    ArrayList<Schmutz> schmutzTeilchen = new ArrayList<>();
    Ladestation ladestation;

    Scene raumFX;

    public void erstelleRaum(int anzahlSchmutz) {
        Hinderniss wand1 = new Wand(0, 0, 10, 400);
        Hinderniss wand2 = new Wand(0, 0, 400, 10);
        Hinderniss wand3 = new Wand(0, 390, 400, 10);
        Hinderniss wand4 = new Wand(390, 0, 10, 400);

        hindernisse.add(wand1);
        hindernisse.add(wand2);
        hindernisse.add(wand3);
        hindernisse.add(wand4);

        for (int i = 0; i < anzahlSchmutz; i++) {
            double randomX = r.nextInt(400);
            double randomY = r.nextInt(400);
            schmutzTeilchen.add(new Schmutz(randomX, randomY));
        }

        ladestation = new Ladestation(200, 20);

        Circle roboter = new Circle(200, 200, 20);
        roboter.setFill(Color.BLUE);
    }

    public Scene getRaumFX() {
        Pane root = new Pane();
        for (Schmutz schmutz: schmutzTeilchen) {
            Circle s = new Circle(schmutz.getPosX(),schmutz.getPosY(),1);
            s.setFill(Color.LIGHTGRAY);
            root.getChildren().add(s);
        }

        for (Hinderniss hinderniss : hindernisse) {
            if (hinderniss.getClass() == Wand.class) {
                Rectangle wand = new Rectangle(((Wand) hinderniss).getPosX(), ((Wand) hinderniss).getPosY(), ((Wand) hinderniss).getBreite(), ((Wand) hinderniss).getHoehe());
                root.getChildren().add(wand);
            }
        }

        Circle ladestationFX = new Circle(ladestation.getPosX(), ladestation.getPosY(), 5);
        ladestationFX.setFill(Color.ORANGERED);
        root.getChildren().add(ladestationFX);

        raumFX = new Scene(root, 400, 400);
        return raumFX;
    }
}
