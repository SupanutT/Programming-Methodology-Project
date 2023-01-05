package entity.zombie;

import javafx.scene.canvas.GraphicsContext;
import logic.GameLogic;
import sharedObject.RenderableHolder;

public class FastZombie extends Zombie {

	public FastZombie(GameLogic gameLogic) {
		super(gameLogic);
		setDamage(0.33f);
		setSpeed(1.2f);
	}

	@Override
	public void draw(GraphicsContext gc) {
		if (getAngle() <= 50 || getAngle() >= 355) {
			gc.drawImage(RenderableHolder.zombie2RightImg, x, y, 58, 58);
		} else if (getAngle() <= 95) {
			gc.drawImage(RenderableHolder.zombie2UpRightImg, x, y, 50, 50);
		} else if (getAngle() <= 140) {
			gc.drawImage(RenderableHolder.zombie2UpImg, x, y, 50, 50);
		} else if (getAngle() <= 185) {
			gc.drawImage(RenderableHolder.zombie2UpImg, x, y, 50, 50);
		} else if (getAngle() <= 230) {
			gc.drawImage(RenderableHolder.zombie2LeftImg, x, y, 58, 58);
		} else if (getAngle() <= 275) {
			gc.drawImage(RenderableHolder.zombie2DownLeftImg, x, y, 58, 58);
		} else if (getAngle() <= 320) {
			gc.drawImage(RenderableHolder.zombie2DownImg, x, y, 52, 52);
		} else {
			gc.drawImage(RenderableHolder.zombie2DownRightImg, x, y, 58, 58);
		}

	}

}
