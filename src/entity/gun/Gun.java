package entity.gun;

import entity.base.Ammo;
import entity.base.Entity;
import entity.base.Recoilable;
import entity.base.Shootable;
import entity.player.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import logic.GameLogic;
import sharedObject.RenderableHolder;
import utils.KeyUtils;

public class Gun extends Entity implements Shootable, Recoilable {

	private String name;
	private int ammo;
	private int pickedUpAmmo;
	private float damage;
	private float speed;
	private float cooldown;
	private float countDown;
	private float recoilAngle;
	private float recoilDistance;
	private boolean isPickedUp;

	private Player player;
	private GameLogic gameLogic;

	public Gun(GameLogic gameLogic) {
		setName("HeadGun");
		setDamage(20);
		setSpeed(15);
		setCooldown(20);
		setCountDown(0);
		setAmmo(10000);
		setRecoilAngle(2);
		setRecoilDistance(1);
		setPickedUp(false);
		super.z = 11;
		this.gameLogic = gameLogic;
		this.player = gameLogic.getPlayer();
	}

	@Override
	public void update() {
		if (countDown != 0) {
			countDown--;
			return;
		}
		if (KeyUtils.getKeyPressed(KeyCode.SPACE)) {
			RenderableHolder.pistolSound.play();
			shoot();
			countDown = cooldown;
		}
	}

	@Override
	public void shoot() {
		calculateRecoil();
		float posX = player.getX();
		float posY = player.getY();
		if (player.getAngle() == 0) {
			posX += 40;
			posY += 10;
		} else if (player.getAngle() == 45) {
			posX += 34;
			posY += 0;
		} else if (player.getAngle() == 90) {
			posX += 15;
			posY += 0;
		} else if (player.getAngle() == 135) {
			posX += 10;
			posY += 0;
		} else if (player.getAngle() == 180) {
			posX += 0;
			posY += 10;
		} else if (player.getAngle() == 225) {
			posX += 8;
			posY += 15;
		} else if (player.getAngle() == 270) {
			posX += 13;
			posY += 13;
		} else if (player.getAngle() == 315) {
			posX += 24;
			posY += 9;
		}
		Ammo ammo = new Ammo(name, damage, speed, posX, posY, twoWayRecoilCalculate(), 40, gameLogic);
		gameLogic.getAmmos().add(ammo);
		gameLogic.addNewObject(ammo);
	}

	public float twoWayRecoilCalculate() {
		float multiplier = 1;
		if (Math.random() <= 0.5) {
			multiplier = -1;
		}
		return player.getAngle() + (float) Math.random() * getRecoilAngle() * multiplier;
	}

	@Override
	public void calculateRecoil() {
		float playerAngle = player.getAngle();
		float distanceX = 0;
		float distanceY = 0;
		if (playerAngle == 0) {
			distanceX = -1;
		} else if (playerAngle == 45) {
			distanceX = -0.7f;
			distanceY = 0.7f;
		} else if (playerAngle == 90) {
			distanceY = 1;
		} else if (playerAngle == 135) {
			distanceX = 0.7f;
			distanceY = 0.7f;
		} else if (playerAngle == 180) {
			distanceX = 1;
		} else if (playerAngle == 225) {
			distanceX = 0.7f;
			distanceY = -0.7f;
		} else if (playerAngle == 270) {
			distanceY = -1;
		} else if (playerAngle == 315) {
			distanceX = -0.7f;
			distanceY = -0.7f;
		}
		player.setX(player.getX() + distanceX * recoilDistance);
		player.setY(player.getY() + distanceY * recoilDistance);

	}

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
		this.damage = damage;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO
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

	public int getAmmo() {
		return ammo;
	}

	public void setAmmo(int ammo) {
		this.ammo = ammo;
	}

	public float getRecoilAngle() {
		return recoilAngle;
	}

	public void setRecoilAngle(float recoilAngle) {
		this.recoilAngle = recoilAngle;
	}

	public float getRecoilDistance() {
		return recoilDistance;
	}

	public void setRecoilDistance(float recoilDistance) {
		this.recoilDistance = recoilDistance;
	}

	public boolean isPickedUp() {
		return isPickedUp;
	}

	public void setPickedUp(boolean isPickedUp) {
		this.isPickedUp = isPickedUp;
	}

	public int getPickedUpAmmo() {
		return pickedUpAmmo;
	}

	public void setPickedUpAmmo(int pickedUpAmmo) {
		this.pickedUpAmmo = pickedUpAmmo;
	}

}