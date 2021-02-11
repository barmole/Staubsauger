package de.ole.Staubsauger.UI;

import de.ole.Staubsauger.Simulation.Roboter;
import de.ole.Staubsauger.Simulation.Status;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class Steuerung {
    private final Roboter roboter;
    public Button startToggle;
    public ProgressBar batteriestand,beutelinhalt,reparaturstatus;
    public Label restzeit;

    public Steuerung(Roboter roboter) {
        this.roboter = roboter;
    }

    public void toggleStart() {
        if (roboter.getStatus() == Status.IDLE) {
            startToggle.setText("Stop");
            roboter.setStatus(Status.FAHREN);
        } else {
            startToggle.setText("Start");
            roboter.setStatus(Status.IDLE);
        }
    }

    public void aktualisieren() {
        if (roboter.getStatus() == Status.LADEN) {
            batteriestand.setProgress(-0.1);
        } else {
            batteriestand.setProgress(roboter.getBatteriestand());
        }

        beutelinhalt.setProgress(roboter.getBeutelinhalt());
        reparaturstatus.setProgress(roboter.getReparaturstatus());
        restzeit.setText((int)roboter.getRestzeit()+"s");
    }

    public void leerenStaubfach(){
        roboter.leereStaubfach();
    }

    public void aufladen(){
        roboter.setStatus(Status.LADEN);
    }
}
