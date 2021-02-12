package de.ole.Staubsauger.UI;

import de.ole.Staubsauger.Simulation.*;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class RaumFX {

    public Group getRaumFX(RaumManager raum) {
        Group raumFX = new Group();
        Circle ladestationFX = new Circle(raum.getLadestation().getPosX(), raum.getLadestation().getPosY(), 5);
        ladestationFX.setFill(Color.ORANGE);
        raumFX.getChildren().add(ladestationFX);

        for (Schmutz schmutz : raum.getSchmutzTeilchen()) {
            Circle s = new Circle(schmutz.getPosX(), schmutz.getPosY(), 1);
            s.setFill(Color.LIGHTGRAY);
            raumFX.getChildren().add(s);
        }

        for (Hinderniss hinderniss : raum.getHindernisse()) {
            if (hinderniss.getClass() == Wand.class) {
                Rectangle wand = new Rectangle(((Wand) hinderniss).getPosX(), ((Wand) hinderniss).getPosY(), ((Wand) hinderniss).getBreite(), ((Wand) hinderniss).getHoehe());
                raumFX.getChildren().add(wand);
            }

            if(hinderniss.getClass() == Kot.class){
                Circle kot = new Circle(hinderniss.getPosX(),hinderniss.getPosY(),hinderniss.getBreite());
                kot.setFill(Color.BROWN);
                raumFX.getChildren().add(kot);
            }
        }

        return raumFX;
    }
}
