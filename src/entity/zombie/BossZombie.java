package entity.zombie;

import entity.base.Ammo;
import entity.base.Shootable;
import javafx.scene.canvas.GraphicsContext;
import logic.GameLogic;
import sharedObject.RenderableHolder;

public class BossZombie extends Zombie implements Shootable {

	private float cooldown;
	private float countDown;
	private float bulletLeft;
	private float ammoSpeed;
	private float onHoldDuration;
	private float holdDuration; // reload time

	private GameLogic gameLogic;

	public BossZombie(GameLogic gameLogic) {
		super(gameLogic);
		super.z = 12;
		setHealth(200);
		setDamage(1);
		setCooldown(50);
		setCountDown(0);
		setAmmoSpeed(3);
		setBulletLeft(3);
		setHoldDuration(100);
		setOnHoldDuration(0);
		this.gameLogic = gameLogic;

	}

	@Override
	public void attack() {
		if (Math.min(player.getX() + 10, getX() + 10) > Math.max(player.getX() - 10, getX() - 10)) {
			if (Math.min(player.getY() + 10, getY() + 10) > Math.max(player.getY() - 10, getY() - 10)) {
				player.setHealth(player.getHealth() - getDamage());
			}
		}
		if (countDown != 0) {
			countDown--;
			return;
		} else if (bulletLeft != 0) {
			shoot();
			countDown = cooldown;
			bulletLeft--;
		} else { // bulletLeft == 0
			if (onHoldDuration == holdDuration) {
				bulletLeft = 3;
				onHoldDuration = 0;
			} else {
				onHoldDuration++;
			}
		}
	}

	@Override
	public void update() {
		if (player == null)
			return;
		setInTop(false);
		;
		setInBottom(false);
		setInLeft(false);
		setInRight(false);
		for (int i = gameLogic.getWalls().size() - 1; i >= 0; i--) {
			if (isInTop() == false)
				setInTop(gameLogic.getWalls().get(i).isInTop(getX(), getY(), 35));
			if (isInBottom() == false)
				setInBottom(gameLogic.getWalls().get(i).isInBottom(getX(), getY(), 45));
			if (isInLeft() == false)
				setInLeft(gameLogic.getWalls().get(i).isInLeft(getX(), getY(), 70));
			if (isInRight() == false)
				setInRight(gameLogic.getWalls().get(i).isInRight(getX(), getY(), 33));
		}
		move();
		attack();
	}

	@Override
	public void draw(GraphicsContext gc) {
		if (getAngle() <= 50 || getAngle() >= 355) {
			gc.drawImage(RenderableHolder.bossRightImg, x, y, 70, 60);
		} else if (getAngle() <= 95) {
			gc.drawImage(RenderableHolder.bossUpRightImg, x, y, 60, 60);
		} else if (getAngle() <= 140) {
			gc.drawImage(RenderableHolder.bossUpImg, x, y, 60, 60);
		} else if (getAngle() <= 185) {
			gc.drawImage(RenderableHolder.bossUpImg, x, y, 60, 60);
		} else if (getAngle() <= 230) {
			gc.drawImage(RenderableHolder.bossLeftImg, x, y, 70, 60);
		} else if (getAngle() <= 275) {
			gc.drawImage(RenderableHolder.bossDownLeftImg, x, y, 80, 60);
		} else if (getAngle() <= 320) {
			gc.drawImage(RenderableHolder.bossDownImg, x, y, 60, 60);
		} else {
			gc.drawImage(RenderableHolder.bossDownRightImg, x, y, 80, 60);
		}

	}

	@Override
	public void shoot() {
		Ammo ammo = new Ammo("bossAmmo", getDamage() * 20, ammoSpeed, x + 30, y + 30, getAngle(), 1000000,
				getGameLogic());
		getGameLogic().getBossAmmos().add(ammo);
		getGameLogic().addNewObject(ammo);
	}

	@Override
	public void setDestroyed() {
		super.destroyed = true;
		RenderableHolder.bossBoom.play();
	}

	public float getCooldown() {
		return cooldown;
	}

	public void setCooldown(float cooldown) {
		this.cooldown = cooldown;
	}

	public float getCountDown() {
		return countDown;
	}

	public void setCountDown(float countDown) {
		this.countDown = countDown;
	}

	public float getBulletLeft() {
		return bulletLeft;
	}

	public void setBulletLeft(float bulletLeft) {
		this.bulletLeft = bulletLeft;
	}

	public float getOnHoldDuration() {
		return onHoldDuration;
	}

	public void setOnHoldDuration(float onHoldDuration) {
		this.onHoldDuration = onHoldDuration;
	}

	public float getAmmoSpeed() {
		return ammoSpeed;
	}

	public void setAmmoSpeed(float ammoSpeed) {
		this.ammoSpeed = ammoSpeed;
	}

	public float getHoldDuration() {
		return holdDuration;
	}

	public void setHoldDuration(float holdDuration) {
		this.holdDuration = holdDuration;
	}

}