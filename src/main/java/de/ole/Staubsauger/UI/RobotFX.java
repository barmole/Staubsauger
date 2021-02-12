package de.ole.Staubsauger.UI;

import de.ole.Staubsauger.Simulation.Roboter;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;

public class RobotFX {
    public Group getRobotFX(Roboter roboter) {
        Group robotFX = new Group();

        Circle r = new Circle(roboter.getPosX(), roboter.getPosY(), 20);
        r.setFill(new ImagePattern(new Image("file:src/main/resources/Gesicht.png"),-1.5,-0.5,4,1.5,true));

        Rotate rotation = new Rotate(roboter.getRotation(),roboter.getPosX(),roboter.getPosY());
        r.getTransforms().add(rotation);
        robotFX.getChildren().add(r);

        if(roboter.laserAn){
            Rectangle laser = new Rectangle(roboter.getPosX()-2,roboter.getPosY(),4,400);
            laser.setFill(Color.RED);
            laser.getTransforms().add(rotation);
            robotFX.getChildren().add(laser);
        }

        return robotFX;
    }
}
