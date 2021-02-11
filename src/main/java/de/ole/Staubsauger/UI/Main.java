package de.ole.Staubsauger.UI;

import de.ole.Staubsauger.Simulation.RaumManager;
import de.ole.Staubsauger.Simulation.Roboter;
import de.ole.Staubsauger.Simulation.Status;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import lombok.Data;


@Data
public class Main extends Application {
    RaumManager manager = new RaumManager();
    RaumFX raumFX = new RaumFX();
    Roboter roboter = new Roboter(200, 30, 0);
    RobotFX robotFX = new RobotFX();

    Stage simulation;
    Scene raumScene;
    StackPane p;


    public static void main(String[] args) {
        launch(args);
        Main main = new Main();
    }

    private void berechne() {
        roboter.berechne(manager);

        p.getChildren().remove(0);
        p.getChildren().add(new Pane(raumFX.getRaumFX(manager), robotFX.getRobotFX(roboter)));


        Platform.runLater(this::berechne);

    }


    @Override
    public void start(Stage simulation) throws Exception {
        this.simulation = simulation;
        try {
            manager.erstelleRaum(400, 400, 2000);
            p = new StackPane(raumFX.getRaumFX(manager), robotFX.getRobotFX(roboter));
            raumScene = new Scene(p);


            simulation.setTitle("Simulation");
            simulation.setScene(raumScene);
            simulation.setX(0);
            simulation.setY(0);
            simulation.show();

            Stage einstellungen = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Einstellungen.fxml"));
            AnchorPane rootLayout = loader.load();
            einstellungen.setScene(new Scene(rootLayout));
            einstellungen.setTitle("Einstellungen");
            einstellungen.setX(400);
            einstellungen.setY(0);
            einstellungen.show();

            Stage steuerung = new Stage();
            loader = new FXMLLoader(getClass().getResource("/Steuerung.fxml"));
            rootLayout = loader.load();
            steuerung.setScene(new Scene(rootLayout));
            steuerung.setTitle("Steuerung");
            steuerung.setX(0);
            steuerung.setY(430);
            steuerung.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        thread.setDaemon(true);
        thread.start();
    }

    Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                Thread.sleep(0);
            } catch (InterruptedException exc) {
                throw new Error("Unexpected interruption", exc);
            }
            roboter.setStatus(Status.FAHREN);
            Platform.runLater(() -> berechne());
        }
    });


}
