package entity.base;

import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import logic.GameLogic;
import sharedObject.RenderableHolder;

public class Ammo extends Entity {
	/* FIELDS */
	private String name;
	private float damage;
	private float duration;
	private float angle;
	private float speed;
	private GameLogic gameLogic;
	private Random ran = new Random();
	private boolean isInWall;

	/* CONSTRUCTOR */
	public Ammo(String name, float damage, float speed, float X, float Y, float angle, int duration,
			GameLogic gameLogic) {
		super();
		setName(name);
		setDamage(damage);
		setSpeed(speed);
		setX(X);
		setY(Y);
		setAngle(angle);
		setDuration(duration);
		this.gameLogic = gameLogic;
		super.z = 11;
	}

	/* METHODS */
	public void update() {
		if (duration != 0) {
			isInWall = false;
			for (int i = gameLogic.getWalls().size() - 1; i >= 0; i--) {
				if (getX() < gameLogic.getWalls().get(i).getxRight() + 17
						&& getX() > gameLogic.getWalls().get(i).getxLeft() + 25) {
					if (getY() < gameLogic.getWalls().get(i).getyBottom() + 20
							&& getY() > gameLogic.getWalls().get(i).getyTop() + 20) {
						isInWall = true;
					}
				}
			}
			super.x += speed * Math.cos(Math.toRadians(angle));
			super.y += -speed * Math.sin(Math.toRadians(angle));
			duration--;
		} else {
			setDestroyed();
		}
	}


	public boolean isOutOfBound() {
		if (getX() < -100 || getX() > 1200 || getY() < -100 || getY() > 900) {
			return true;
		}
		return false;
	}

	@Override
	public void draw(GraphicsContext gc) {
		if (this.getName() == "bossAmmo") {
			int r = ran.nextInt(2) + 1;
			if (r == 1)
				gc.drawImage(RenderableHolder.bossAmmoImg2, super.x, super.y, 20, 20);
			else
				gc.drawImage(RenderableHolder.bossAmmoImg3, super.x, super.y, 20, 20);
		} else {
			if (angle <= 22.5f || angle >= 337.5f) {
				gc.drawImage(RenderableHolder.pistolAmmoImg0, super.x, super.y, 14, 6);
			} else if (angle <= 67.5) {
				gc.drawImage(RenderableHolder.pistolAmmoImg45, super.x, super.y, 10, 10);
			} else if (angle <= 112.5) {
				gc.drawImage(RenderableHolder.pistolAmmoImg90, super.x, super.y, 6, 14);
			} else if (angle <= 157.5) {
				gc.drawImage(RenderableHolder.pistolAmmoImg135, super.x, super.y, 10, 10);
			} else if (angle <= 202.5) {
				gc.drawImage(RenderableHolder.pistolAmmoImg180, super.x, super.y, 14, 6);
			} else if (angle <= 247.5) {
				gc.drawImage(RenderableHolder.pistolAmmoImg225, super.x, super.y, 10, 10);
			} else if (angle <= 292.5) {
				gc.drawImage(RenderableHolder.pistolAmmoImg270, super.x, super.y, 6, 14);
			} else if (angle <= 337.5) {
				gc.drawImage(RenderableHolder.pistolAmmoImg315, super.x, super.y, 10, 10);
			}
		}

	}

	/* GETTERS AND SETTERS */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getDamage() {
		return damage;
	}

	public void setDamage(float damage) {
		this.damage = (damage < 0) ? 0 : damage;
	}

	public float getDuration() {
		return duration;
	}

	public void setDuration(float duration) {
		this.duration = (duration < 0) ? 0 : duration;
	}

	public float getAngle() {
		return angle;
	}

	public void setAngle(float angle) {
		this.angle = angle;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public boolean isInWall() {
		return isInWall;
	}

	public void setInWall(boolean inWall) {
		this.isInWall = inWall;
	}

	public void Hit() {
		setDestroyed();
	}
}