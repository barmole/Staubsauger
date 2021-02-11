package de.ole.Staubsauger.UI;

import de.ole.Staubsauger.Simulation.Hinderniss;
import de.ole.Staubsauger.Simulation.RaumManager;
import de.ole.Staubsauger.Simulation.Schmutz;
import de.ole.Staubsauger.Simulation.Wand;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class RaumFX {

    public Group getRaumFX(RaumManager raum) {
        Group raumFX = new Group();
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
        }

        Circle ladestationFX = new Circle(raum.getLadestation().getPosX(), raum.getLadestation().getPosY(), 5);
        ladestationFX.setFill(Color.ORANGERED);
        raumFX.getChildren().add(ladestationFX);


        return raumFX;
    }
}
