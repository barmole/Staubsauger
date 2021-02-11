package de.ole.Staubsauger.UI;

import de.ole.Staubsauger.Simulation.RaumManager;
import de.ole.Staubsauger.Simulation.Roboter;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class Einstellungen {

    private final Roboter roboter;
    private final RaumManager raum;
    public Slider geschwindigkeit;
    public Label geschwindikeitAnzeige;
    public TextField anzahlSchmutz;

    public Einstellungen(Roboter roboter, RaumManager raum) {
        this.roboter = roboter;
        this.raum = raum;
    }

    public void initialize(){
        geschwindikeitAnzeige.setLabelFor(geschwindigkeit);
    }

    public void sliderDragDone(MouseEvent dragEvent) {
        geschwindikeitAnzeige.setText(""+geschwindigkeit.getValue());
        roboter.setGeschwindigkeit(geschwindigkeit.getValue());
        System.out.println(geschwindigkeit.getValue());
    }

    public void loeschenSchmutz(){
        raum.loescheSchmutz();
    }

    public void streuenSchmutz(){
        System.out.println(anzahlSchmutz.getText());
        raum.streueSchmutz(Integer.parseInt(anzahlSchmutz.getText()));
    }
}
