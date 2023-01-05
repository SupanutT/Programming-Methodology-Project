package drawing;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logic.GameLogic;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

public class Header implements IRenderable {

	private static int[][] field = new int[52][68];

	private void array(int[][] field) {
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 68; y++) {
				if (x <= 4) {
					if (field[x][y] == 0) {
						if (y > 0 && y < 67 && x != 0) {
							field[x][y] = 132;
						} else {
							field[x][y] = 1;
						}
					}
				}
				if (x == 5) {
					if (field[x][y] == 0) {
						field[x][y] = 2;
					}
				}
				if (x == 6) {
					if (field[x][y] == 0) {
						field[x][y] = 14;
					}
				}
				if (x == 7) {
					if (field[x][y] == 0) {
						if (y % 2 == 0)
							field[x][y] = 10;
						if (y % 2 == 1)
							field[x][y] = 11;
					}
				}
			}
		}
	}

	public int getTerrain(int x, int y) {
		return field[y][x];
	}

	private Image getTileImg(int x, int y) {
		int terrain = getTerrain(x, y);
		if (terrain == 10)
			return RenderableHolder.wallImg0010;
		if (terrain == 11)
			return RenderableHolder.wallImg0011;
		if (terrain == 14)
			return RenderableHolder.wallImg0014;
		if (terrain == 2)
			return RenderableHolder.wallImg0002;
		if (terrain == 1)
			return RenderableHolder.wallImg0000;
		if (terrain == 132)
			return RenderableHolder.wallImg0132;
		return null;

	}

	@Override
	public int getZ() {
		return 9999;
	}

	@Override
	public void draw(GraphicsContext gc) {
		array(field);
		for (int x = 0; x < field[0].length; x++) {
			for (int y = 0; y < field.length; y++) {
				gc.drawImage(getTileImg(x, y), x * 15, y * 15);
			}
		}

		// drawBottomRight
		Font theFont = Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 14);

		gc.setFont(theFont);
		gc.setFill(Color.BLACK);
		gc.fillText("1 = HeadGun", 900, 137);
		gc.fillText("2 = Shotgun", 900, 155);
		gc.fillText("3 = UZI", 900, 173);
		gc.fillText("4 = Buster Rifle", 900, 191);
		gc.fillText("P = Pause/Resume", 900, 214);

		// drawWave, Enemy
		Font waveFont = Font.font("Cochin", FontWeight.EXTRA_BOLD, 22);

		// drawWave, Enemy
		gc.setFont(waveFont);
		gc.setFill(Color.BLACK);
		gc.fillText("WAVE  " + Integer.toString(GameLogic.getRoundCount()), 21, 39);
		gc.fillText("ENEMY : " + Integer.toString(GameLogic.getZombieLeft()), 21, 67);

		// drawField, Gun / Score, Ammo
		Font fieldFont = Font.font("Cochin", FontWeight.EXTRA_BOLD, 18);
		Font scoreFont = Font.font("Cochin", FontWeight.EXTRA_BOLD, 26);

		gc.setFont(fieldFont);
		gc.setFill(Color.BLACK);
		gc.fillText("FIELD : The Lost Colony", 480, 37);
		gc.fillText("GUN : " + GameLogic.getGun().getName(), 740, 37);

		gc.setFont(scoreFont);
		gc.setFill(Color.BLACK);
		gc.fillText("SCORE : " + GameLogic.intToString(GameLogic.getScore(), 8), 478, 67);
		if (GameLogic.getGun().getName() == "HeadGun") {
			gc.fillText("AMMO : " + "Inf." + " / " + "Inf.", 740, 67);
		} else {
			if (GameLogic.getGun().getAmmo() == 0) {
				gc.setFill(Color.CRIMSON);
				gc.fillText("Out of Ammo!", 740, 67);
			} else {
				gc.fillText("AMMO : " + GameLogic.getGun().getAmmo() + " / " + GameLogic.getGun().getPickedUpAmmo(),
						740, 67);
			}
		}

		// energy
		Font enerFont = Font.font("Cochin", FontWeight.EXTRA_BOLD, 22);
		gc.setFont(enerFont);
		gc.setFill(Color.BLACK);
		gc.fillText("ENERGY", 195, 39); // 167,37
		// left
		Font leftFont = Font.font("Cochin", FontWeight.EXTRA_BOLD, 34);
		gc.setFont(leftFont);
		gc.setFill(Color.BLACK);
		gc.fillText("LEFT", 195, 67); // 167,37

		// create Pause symbol
		if (GameLogic.getInstance().isPause()) {
			gc.drawImage(RenderableHolder.pauseImg, 5, 120, 200, 115);
		}

		// draw Energy
		Font energyFont = Font.font("Impact", FontWeight.BOLD, 53);
		gc.setFont(energyFont);
		if (Math.ceil(GameLogic.getInstance().getPlayer().getHealth()) >= 70) {
			gc.setFill(Color.FORESTGREEN);
			gc.setStroke(Color.FORESTGREEN);
		} else if (Math.ceil(GameLogic.getInstance().getPlayer().getHealth()) >= 30) {
			gc.setFill(Color.DODGERBLUE);
			gc.setStroke(Color.DODGERBLUE);
		} else {
			gc.setFill(Color.CRIMSON);
			gc.setStroke(Color.CRIMSON);
		}
		gc.fillText((int) Math.ceil(GameLogic.getInstance().getPlayer().getHealth()) + "%", 300, 67);

	}

	@Override
	public boolean isDestroyed() {
		return false;
	}

	@Override
	public boolean isVisible() {
		return true;
	}
}