package de.ole.staubsauger.ui;

import de.ole.staubsauger.simulation.RaumManager;
import de.ole.staubsauger.simulation.Roboter;
import de.ole.staubsauger.simulation.Status;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;


public class Main extends Application {
    public static final int FPS = 60;
    RaumManager manager = new RaumManager();
    RaumFX raumFX = new RaumFX();
    Roboter roboter = new Roboter(200, 40, 0);
    RobotFX robotFX = new RobotFX();

    Steuerung steuerungsController = new Steuerung(roboter);

    Stage simulation;
    Scene raumScene;
    StackPane p;


    public static void main(String[] args) {
        launch(args);
    }

    private void berechne() {
        long time = System.currentTimeMillis();

        roboter.berechne(manager);
        steuerungsController.aktualisieren();

        p.getChildren().remove(0);

        Group roboterGruppe = robotFX.getRobotFX(roboter);
        Group raumGruppe = raumFX.getRaumFX(manager);

        if (roboterGruppe.getChildren().size() == 2) {
            var laser = roboterGruppe.getChildren().get(1);
            var ladestation = raumGruppe.getChildren().get(0);

            roboter.stationGefunden = Shape.intersect((Shape) laser, (Shape) ladestation).getLayoutBounds().getWidth() > 0;
            roboterGruppe.getChildren().add(Shape.intersect((Shape) laser, (Shape) ladestation));

        }

        if (roboter.getStatus() == Status.RUECKWEG) {
            var roboterFX = roboterGruppe.getChildren().get(0);
            var ladestation = raumGruppe.getChildren().get(0);
            roboter.stehtAufLadestation = Shape.intersect((Shape) roboterFX, (Shape) ladestation).getLayoutBounds().getWidth() > 0;

        }

        Pane pane = new Pane(raumGruppe, roboterGruppe);
        p.getChildren().add(pane);

        long delay = (1000 / FPS) - (System.currentTimeMillis() - time);
        if (delay > 0) {
            try {
                Thread.sleep(delay);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
            Einstellungen einstellungsController = new Einstellungen(roboter, manager);
            loader.setController(einstellungsController);
            AnchorPane rootLayout = loader.load();
            einstellungen.setScene(new Scene(rootLayout));
            einstellungen.setTitle("Einstellungen");
            einstellungen.setX(manager.getBreite());
            einstellungen.setY(0);
            einstellungen.show();

            Stage steuerung = new Stage();
            loader = new FXMLLoader(getClass().getResource("/Steuerung.fxml"));
            loader.setController(steuerungsController);
            rootLayout = loader.load();
            steuerung.setScene(new Scene(rootLayout));
            steuerung.setTitle("Steuerung");
            steuerung.setX(0);
            steuerung.setY((double) manager.getHoehe() + 30);
            steuerung.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        thread.setDaemon(true);
        thread.start();
    }

    Thread thread = new Thread(() -> {
        roboter.setStatus(Status.IDLE);
        Platform.runLater(this::berechne);
    });


}
