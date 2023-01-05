package main;

import drawing.*;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	public static Stage stage;

	@Override
	public void start(Stage primaryStage) {
		stage = primaryStage;

		Parent mainMenu = new MainMenu();
		Scene mainScene = new Scene(mainMenu);
		stage.setTitle("Mobile Suit Gundam: Epyon's Counter Attack"); // Set the stage title\
		stage.setScene(mainScene);
		stage.setResizable(false);
		stage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
}