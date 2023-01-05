package drawing;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import logic.GameLogic;
import main.Main;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;
import utils.KeyUtils;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;

public class GameScreen extends Canvas {
	private Scene scene;
	private Boolean endGameCk = false;

	public GameScreen(double width, double height) {
		super(width, height);
		this.setVisible(true);
		addListener();
	}

	public void addListener() {
		this.setOnKeyPressed((KeyEvent event) -> {
			KeyUtils.setKeyPressed(event.getCode(), true);
		});

		this.setOnKeyReleased((KeyEvent event) -> {
			KeyUtils.setKeyPressed(event.getCode(), false);
		});

		this.setOnMousePressed((MouseEvent event) -> {
			if (event.getButton() == MouseButton.PRIMARY)
				KeyUtils.mouseLeftDown();
		});

		this.setOnMouseReleased((MouseEvent event) -> {
			if (event.getButton() == MouseButton.PRIMARY)
				KeyUtils.mouseLeftRelease();
		});

		this.setOnMouseEntered((MouseEvent event) -> {
			KeyUtils.mouseOnScreen = true;
		});

		this.setOnMouseExited((MouseEvent event) -> {
			KeyUtils.mouseOnScreen = false;
		});

		this.setOnMouseMoved((MouseEvent event) -> {
			if (KeyUtils.mouseOnScreen) {
				KeyUtils.mouseX = (float) event.getX();
				KeyUtils.mouseY = (float) event.getY();
			}
		});

		this.setOnMouseDragged((MouseEvent event) -> {
			if (KeyUtils.mouseOnScreen) {
				KeyUtils.mouseX = (float) event.getX();
				KeyUtils.mouseY = (float) event.getY();
			}
		});
	}

	public void paintComponent() {
		GraphicsContext gc = this.getGraphicsContext2D();
		gc.setFill(Color.BLACK);
		for (IRenderable entity : RenderableHolder.getInstance().getEntities()) {
			entity.draw(gc);
		}
		if (GameLogic.getInstance().isOver())
			toGameEnd();
	}

	public void toGameEnd() {
		if (endGameCk == false) {
			endGameCk = true;
			RenderableHolder.endGame.play();

			// change to GameEnd
			GameEnd ge = new GameEnd();
			scene = new Scene(ge);
			Main.stage.setScene(scene);
		}
	}

}