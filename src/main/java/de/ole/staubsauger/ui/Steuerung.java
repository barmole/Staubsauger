package de.ole.staubsauger.ui;

import de.ole.staubsauger.simulation.Roboter;
import de.ole.staubsauger.simulation.Status;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;

public class Steuerung {
    private final Roboter roboter;
    public Button startToggle;
    public ProgressBar batteriestand, beutelinhalt, reparaturstatus;
    public Label restzeit, status;
    public ComboBox<String> wochentag;
    public Spinner<Integer> stunde,minute;

    public Steuerung(Roboter roboter) {
        this.roboter = roboter;
    }

    public void initialize(){
        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "Montag",
                        "Dienstag",
                        "Mittwoch",
                        "Donnerstag",
                        "Freitag",
                        "Samstag",
                        "Sonntag"
                );
        wochentag.getItems().addAll(options);

        SpinnerValueFactory<Integer> svfStunde = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 1);
        stunde.setValueFactory(svfStunde);
        SpinnerValueFactory<Integer> svfMinute = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 1);
        minute.setValueFactory(svfMinute);

        stunde.getValueFactory().setValue(roboter.putzStunde);
        minute.getValueFactory().setValue(roboter.putzMinute);
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

        if (roboter.getStatus() == Status.IDLE || roboter.getStatus() == Status.LADEN) {
            startToggle.setText("Start");
        }

        beutelinhalt.setProgress(roboter.getBeutelinhalt());
        reparaturstatus.setProgress(roboter.getReparaturstatus());
        restzeit.setText((int) roboter.getRestzeit() + "s");
        status.setText("Status: " + roboter.getStatus().toString());
    }

    public void leerenStaubfach() {
        roboter.leereStaubfach();
    }

    public void aufladen() {
        roboter.setStatus(Status.RUECKWEG);
    }

    public void setzeSaugzeit(){
        roboter.putzTag = wochentag.getValue();
        roboter.putzStunde = stunde.getValue();
        roboter.putzMinute = minute.getValue();
    }
}
