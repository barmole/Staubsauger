package de.ole.Staubsauger.UI;

import de.ole.Staubsauger.Simulation.RaumManager;
import de.ole.Staubsauger.Simulation.Roboter;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lombok.Data;


@Data
public class Main extends Application {

    @FXML
    Einstellungen einstellungen;

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage simulation) throws Exception {
        try {
            RaumManager manager = new RaumManager();
            RaumFX raumFX = new RaumFX();
            Roboter roboter = new Roboter(200, 30, 0);
            RobotFX robotFX = new RobotFX();

            manager.erstelleRaum(400, 400, 2000);
            Pane p = new Pane(raumFX.getRaumFX(manager), robotFX.getRobotFX(roboter));
            Scene raum = new Scene(p);


            simulation.setTitle("Simulation");
            simulation.setScene(raum);
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


    }
}
