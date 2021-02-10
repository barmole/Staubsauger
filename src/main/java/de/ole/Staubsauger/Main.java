package de.ole.Staubsauger;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Data;


@Data
public class Main extends Application{

	public static void main(String[] args){
		launch(args);
	}


	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			RaumManager manager = new RaumManager();
			manager.erstelleRaum(200);
			primaryStage.setTitle("Simulation");
			Scene raum = manager.getRaumFX();

			primaryStage.setScene(raum);
			primaryStage.show();
		} catch (Exception e){
			e.printStackTrace();
		}

	}
}
