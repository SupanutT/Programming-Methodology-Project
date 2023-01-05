package entity.base;

import sharedObject.IRenderable;

public abstract class Entity implements IRenderable {
	protected float x, y;
	protected int z;
	protected boolean visible, destroyed;
	protected float health;

	protected Entity() {
		visible = true;
		destroyed = false;
	}

	public abstract void update();

	@Override
	public int getZ() {
		return z;
	}

	public void setDestroyed() {
		destroyed = true;
		visible = false;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	@Override
	public boolean isDestroyed() {
		return destroyed;
	}

	@Override
	public boolean isVisible() {
		return visible;
	}

	/* GETTERS AND SETTERS */
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getHealth() {
		return health;
	}

	public void setHealth(float health) {
		if (health < 0) {
			this.health = 0;
			setDestroyed();
		} else {
			this.health = health;
		}
	}

}
