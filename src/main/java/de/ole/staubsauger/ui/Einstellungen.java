package de.ole.staubsauger.ui;

import de.ole.staubsauger.simulation.RaumManager;
import de.ole.staubsauger.simulation.Roboter;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

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

    public void sliderDragDone() {
        geschwindikeitAnzeige.setText(""+geschwindigkeit.getValue());
        roboter.setGeschwindigkeit(geschwindigkeit.getValue());
    }

    public void loeschenSchmutz(){
        raum.loescheSchmutz();
    }

    public void streuenSchmutz(){
        raum.streueSchmutz(Integer.parseInt(anzahlSchmutz.getText()));
    }

    public void legeKot(){
        raum.legeKot();
    }
}
