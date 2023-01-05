package drawing;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import logic.GameLogic;
import main.Main;
import sharedObject.RenderableHolder;
import utils.KeyUtils;

public class MainMenu extends BorderPane {

	private Scene scene;

	public MainMenu() {
		super();
		this.setPrefSize(1020, 780);
		Canvas canvas = new Canvas();

		// set height and width
		canvas.setHeight(780);
		canvas.setWidth(1020);

		GraphicsContext gcm = canvas.getGraphicsContext2D();

		gcm.drawImage(RenderableHolder.wallpaper, 0, 0, 1020, 780);
		gcm.drawImage(RenderableHolder.logo1, 25, 20, 500, 275);

		Font theFont = Font.font("Impact", FontWeight.MEDIUM, 47.5);// Times New Roman, 34
		Font playTextFont = Font.font("Cochin", FontWeight.EXTRA_BOLD, 58);
		Font instructionTextFont = Font.font("Cochin", FontWeight.EXTRA_BOLD, 42);

		gcm.setFont(theFont);
		gcm.setFill(Color.WHITE);
		gcm.setStroke(Color.WHITE);
		gcm.fillText("EPYON'S COUNTER ATTACK", 32, 338); // 32, 326

		this.getChildren().addAll(canvas);

		Text playText = new Text("PLAY -");
		playText.setFont(playTextFont);
		playText.setFill(Color.GHOSTWHITE);
		playText.setStroke(Color.FLORALWHITE);
		playText.setOpacity(0.7);
		BorderPane.setAlignment(playText, Pos.BOTTOM_RIGHT);
		BorderPane.setMargin(playText, new Insets(0, 25, 12, 0));
		this.setCenter(playText);

		Text instructionText = new Text("INSTRUCTIONS -");
		instructionText.setFont(instructionTextFont);
		instructionText.setFill(Color.GHOSTWHITE);
		instructionText.setStroke(Color.AZURE);
		instructionText.setOpacity(0.7);
		BorderPane.setAlignment(instructionText, Pos.BOTTOM_RIGHT);
		BorderPane.setMargin(instructionText, new Insets(0, 25, 25, 0));
		this.setBottom(instructionText);

		playText.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				playText.setOpacity(1);
			}
		});

		playText.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				playText.setOpacity(0.7);
			}
		});

		instructionText.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				instructionText.setOpacity(1);
			}
		});

		instructionText.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				instructionText.setOpacity(0.7);
			}
		});

		playText.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				toGameScreen();
			}
		});

		instructionText.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				toInstruction();
			}
		});
	}

	public void toGameScreen() {
		RenderableHolder.textClickedSound.play();
		GameScreen gameScreen = new GameScreen(1020, 780);
		RenderableHolder.getInstance().clear();
		Group group = new Group(gameScreen);
		scene = new Scene(group);
		Main.stage.setScene(scene);

		gameScreen.requestFocus();

		AnimationTimer animation = new AnimationTimer() {
			public void handle(long now) {
				gameScreen.paintComponent();
				GameLogic.getInstance().gameUpdate();
				RenderableHolder.getInstance().update();
				KeyUtils.updateInputState();
				if (GameLogic.getInstance().isOver()) {
					gameScreen.paintComponent();
					this.stop();
				}
			}
		};
		animation.start();
	}

	public void toInstruction() {
		RenderableHolder.textClickedSound.play();
		// change to Instructions
		Instructions ins = new Instructions();
		scene = new Scene(ins);
		Main.stage.setScene(scene);
	}
}