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

public class GameEnd extends BorderPane {
	private Scene scene;

	public GameEnd() {
		super();
		this.setPrefSize(1020, 780);
		Canvas canvas = new Canvas();

		// set height and width
		canvas.setHeight(780);
		canvas.setWidth(1020);

		GraphicsContext gcm = canvas.getGraphicsContext2D();

		gcm.drawImage(RenderableHolder.gameEnd, 0, 0, 1020, 780);

		Font theFont = Font.font("Impact", FontWeight.BOLD, 34);
		Font gameEndTextFont = Font.font("Impact", FontWeight.EXTRA_BOLD, 64);
		Font backTextFont = Font.font("Cochin", FontWeight.EXTRA_BOLD, 58);

		gcm.setFont(gameEndTextFont);
		gcm.setFill(Color.SNOW);
		gcm.setStroke(Color.GAINSBORO);
		gcm.fillText("MISSION FAILED!", 30, 60);

		gcm.setFont(theFont);
		gcm.setFill(Color.SNOW);
		gcm.setStroke(Color.SNOW);
		gcm.fillText("at Wave  " + Integer.toString(GameLogic.getRoundCount()) + "  with ", 30, 110);
		gcm.fillText("SCORE : " + GameLogic.intToString(GameLogic.getScore(), 8), 30, 155);

		this.getChildren().addAll(canvas);

		Text newGameText = new Text("> NEW GAME <");
		newGameText.setFont(backTextFont);
		newGameText.setFill(Color.GHOSTWHITE);
		newGameText.setStroke(Color.BEIGE);
		newGameText.setOpacity(0.7);
		BorderPane.setAlignment(newGameText, Pos.BOTTOM_RIGHT);
		BorderPane.setMargin(newGameText, new Insets(0, 25, 12, 0));
		this.setCenter(newGameText);

		Text backText = new Text("> QUIT GAME <");
		backText.setFont(backTextFont);
		backText.setFill(Color.GHOSTWHITE);
		backText.setStroke(Color.BEIGE);
		backText.setOpacity(0.7);
		BorderPane.setAlignment(backText, Pos.BOTTOM_RIGHT);
		BorderPane.setMargin(backText, new Insets(0, 25, 25, 0));
		this.setBottom(backText);

		newGameText.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				newGameText.setOpacity(1);
			}
		});

		newGameText.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				newGameText.setOpacity(0.7);
			}
		});

		backText.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				backText.setOpacity(1);
			}
		});

		backText.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				backText.setOpacity(0.7);
			}
		});

		newGameText.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				toGameScreen();
			}
		});

		backText.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				toQuit();
			}
		});
	}

	public void toGameScreen() {

		RenderableHolder.textClickedSound.play();

		GameScreen gameScreen = new GameScreen(1020, 780);
		Group group = new Group(gameScreen);
		scene = new Scene(group);
		RenderableHolder.getInstance().clear();
		GameLogic.getInstance().gameStart();
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

	public void toQuit() {
		RenderableHolder.textClickedSound.play();
		Main.stage.close();
	}
}