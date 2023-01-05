package entity.wall;

import entity.base.Entity;
import javafx.scene.canvas.GraphicsContext;
import sharedObject.RenderableHolder;

public class Wall extends Entity {
	private float xLeft, xRight, yTop, yBottom;
	private static final int wallSize = 69;

	public Wall(float x, float y) {
		super();
		super.z = 20;
		super.x = x;
		super.y = y;
		xLeft = x;
		xRight = x + wallSize;
		yTop = y;
		yBottom = y + wallSize;
	}

	public boolean isInTop(float x, float y, float size) {
		if (x >= this.xLeft - size * 0.5f && x <= this.xRight + size * 0.5f && y >= this.yTop - size * 0.5f
				&& y <= this.yTop) {
			return true;
		} else
			return false;
	}

	public boolean isInBottom(float x, float y, float size) {
		if (x >= this.xLeft - size * 0.5f && x <= this.xRight + size * 0.5f && y <= this.yBottom + size * 0.5f
				&& y >= this.yBottom) {
			return true;
		} else
			return false;
	}

	public boolean isInLeft(float x, float y, float size) {
		if (x >= this.xLeft - size * 0.5f && x <= this.xLeft && y >= this.yTop - size * 0.5f
				&& y <= this.yBottom + size * 0.5f) {
			return true;
		} else
			return false;
	}

	public boolean isInRight(float x, float y, int size) {
		if (x <= this.xRight + size * 0.5f && x >= this.xRight && y >= this.yTop - size * 0.5f
				&& y <= this.yBottom + size * 0.5f) {
			return true;
		} else
			return false;
	}

	@Override
	public void draw(GraphicsContext gc) {
		float shiftedX = x + 30;
		float shiftedY = y + 30;
		gc.drawImage(RenderableHolder.wallImg0004, shiftedX, shiftedY, 15, 15);
		gc.drawImage(RenderableHolder.wallImg0026, shiftedX + 15, shiftedY, 15, 15);
		gc.drawImage(RenderableHolder.wallImg0026, shiftedX + 30, shiftedY, 15, 15);
		gc.drawImage(RenderableHolder.wallImg0005, shiftedX + 45, shiftedY, 15, 15);
		gc.drawImage(RenderableHolder.wallImg0015, shiftedX, shiftedY + 15, 15, 15);
		gc.drawImage(RenderableHolder.wallImg0000, shiftedX + 15, shiftedY + 15, 15, 15);
		gc.drawImage(RenderableHolder.wallImg0000, shiftedX + 30, shiftedY + 15, 15, 15);
		gc.drawImage(RenderableHolder.wallImg0013, shiftedX + 45, shiftedY + 15, 15, 15);
		gc.drawImage(RenderableHolder.wallImg0016, shiftedX, shiftedY + 30, 15, 15);
		gc.drawImage(RenderableHolder.wallImg0002, shiftedX + 15, shiftedY + 30, 15, 15);
		gc.drawImage(RenderableHolder.wallImg0002, shiftedX + 30, shiftedY + 30, 15, 15);
		gc.drawImage(RenderableHolder.wallImg0017, shiftedX + 45, shiftedY + 30, 15, 15);
		gc.drawImage(RenderableHolder.wallImg0057, shiftedX, shiftedY + 45, 15, 15);
		gc.drawImage(RenderableHolder.wallImg0040, shiftedX + 15, shiftedY + 45, 15, 15);
		gc.drawImage(RenderableHolder.wallImg0040, shiftedX + 30, shiftedY + 45, 15, 15);
		gc.drawImage(RenderableHolder.wallImg0059, shiftedX + 45, shiftedY + 45, 15, 15);
	}

	@Override
	public void update() {
		// TODO
	}

	public float getxLeft() {
		return xLeft;
	}

	public void setxLeft(float xLeft) {
		this.xLeft = xLeft;
	}

	public float getxRight() {
		return xRight;
	}

	public void setxRight(float xRight) {
		this.xRight = xRight;
	}

	public float getyTop() {
		return yTop;
	}

	public void setyTop(float yTop) {
		this.yTop = yTop;
	}

	public float getyBottom() {
		return yBottom;
	}

	public void setyBottom(float yBottom) {
		this.yBottom = yBottom;
	}

}
