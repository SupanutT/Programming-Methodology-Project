package drawing;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import main.Main;
import sharedObject.RenderableHolder;

public class Instructions extends BorderPane {

	private Scene scene;

	public Instructions() {
		super();
		this.setPrefSize(1020, 780);

		this.setBackground(
				new Background(new BackgroundFill(Color.DARKSLATEBLUE, CornerRadii.EMPTY, new Insets(15, 15, 15, 15))));

		Font theFont = Font.font("Times New Roman", FontWeight.BOLD, 24);
		Font backTextFont = Font.font("Cochin", FontWeight.EXTRA_BOLD, 36);
		Font instructionTextFont = Font.font("Cochin", FontWeight.EXTRA_BOLD, 42);

		Text instructionText = new Text("INSTRUCTIONS");
		instructionText.setFont(instructionTextFont);
		instructionText.setFill(Color.GHOSTWHITE);
		instructionText.setStroke(Color.AZURE);
		instructionText.setUnderline(true);
		BorderPane.setAlignment(instructionText, Pos.TOP_LEFT);
		BorderPane.setMargin(instructionText, new Insets(50, 0, 0, 55));
		this.setTop(instructionText);

		Text wText = new Text(
				"W / ↑ = UP \nA / ← = LEFT \nS / ↓ = DOWN \nD / → = RIGHT \n\nP = Pause / Resume \n\n\nNum1 = Change weapon to HEADGUN \nNum2 = Change weapon to SHOTGUN (First appears at Level 4) \nNum3 = Change weapon to UZI (First appears at Level 7) \nNum4 = Change weapon to BUSTER RIFLE (First appears at Level 10) \n\nSpace Bar = SHOOT");
		wText.setFont(theFont);
		wText.setFill(Color.SNOW);
		BorderPane.setAlignment(wText, Pos.TOP_LEFT);
		BorderPane.setMargin(wText, new Insets(50, 0, 0, 55));
		this.setCenter(wText);

		Text backText = new Text("> BACK TO MENU <");
		backText.setFont(backTextFont);
		backText.setFill(Color.KHAKI);
		backText.setStroke(Color.GOLD);
		backText.setOpacity(0.7);
		BorderPane.setAlignment(backText, Pos.BOTTOM_CENTER);
		BorderPane.setMargin(backText, new Insets(0, 0, 70, 0));
		this.setBottom(backText);

		String image_path = ClassLoader.getSystemResource("gundam/epyon.PNG").toString();
		ImageView imageView = new ImageView(new Image(image_path));

		imageView.setPreserveRatio(true);
		imageView.setFitWidth(225);
		BorderPane.setAlignment(imageView, Pos.TOP_RIGHT);
		BorderPane.setMargin(imageView, new Insets(0, 0, 0, 0));
		this.setRight(imageView);

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

		backText.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				toMainMenu();
			}
		});
	}

	public void toMainMenu() {
		RenderableHolder.textClickedSound.play();
		MainMenu mai = new MainMenu();
		scene = new Scene(mai);
		Main.stage.setScene(scene);
	}
}
