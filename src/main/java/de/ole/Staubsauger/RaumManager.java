package de.ole.Staubsauger;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class RaumManager {
    Scene raum;
    public void erstelleRaum() {
        Pane root = new Pane();

        Circle roboter = new Circle(200,200,20);
        roboter.setFill(Color.BLUE);
        Rectangle wand1 = new Rectangle(0,0,10,400);
        Rectangle wand2 = new Rectangle(0,0,400,10);
        Rectangle wand3 = new Rectangle(0,390,400,10);
        Rectangle wand4 = new Rectangle(390,0,10,400);
        Circle ladestation = new Circle(200,20,5);
        ladestation.setFill(Color.ORANGERED);


        root.getChildren().add(wand1);
        root.getChildren().add(wand2);
        root.getChildren().add(wand3);
        root.getChildren().add(wand4);
        root.getChildren().add(ladestation);
        root.getChildren().add(roboter);


        raum = new Scene(root,400,400);
    }

    public Scene getRaum() {
        return raum;
    }
}
