package drawing;

import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

public class Field implements IRenderable {

	private static int[][] field = new int[52][68];

	private void array(int[][] field) {
		Random ran = new Random();
		for (int x = 0; x < 52; x++) {
			for (int y = 0; y < 68; y++) {
				if (field[x][y] == 0) {
					int r = ran.nextInt(100);
					if (r > 35) {
						field[x][y] = 1;
						continue;
					}
					if (r > 2) {
						field[x][y] = 2;
						continue;
					} else
						field[x][y] = 3;
				}
			}
		}
	}

	public int getTerrain(int x, int y) {
		return field[y][x];
	}

	private Image getTileImg(int x, int y) {
		int terrain = getTerrain(x, y);
		if (terrain == 1)
			return RenderableHolder.tile1;
		if (terrain == 2)
			return RenderableHolder.tile2;
		if (terrain == 3)
			return RenderableHolder.tile3;
		return null;

	}

	@Override
	public int getZ() {
		return -9999;
	}

	@Override
	public void draw(GraphicsContext gc) {
		array(field);
		for (int x = 0; x < field[0].length; x++) {
			for (int y = 0; y < field.length; y++) {
				gc.drawImage(getTileImg(x, y), x * 15, y * 15);
			}
		}
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