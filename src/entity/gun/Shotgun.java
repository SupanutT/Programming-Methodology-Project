package entity.gun;

import entity.base.Ammo;
import entity.player.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import logic.GameLogic;
import sharedObject.RenderableHolder;
import utils.KeyUtils;

public class Shotgun extends Gun {

	private boolean isHolding;

	private Player player;
	private GameLogic gameLogic;

	public Shotgun(int ammo, boolean isPickedUp, GameLogic gameLogic) {
		super(gameLogic);
		setName("Shotgun");
		setDamage(40);
		setSpeed(30);
		setCooldown(40);
		setAmmo(ammo);
		setPickedUp(isPickedUp);
		setPickedUpAmmo(ammo);
		setRecoilDistance(10);
		this.gameLogic = gameLogic;
		this.player = gameLogic.getPlayer();
	}

	@Override
	public void shoot() {
		calculateRecoil();
		float posX = player.getX();
		float posY = player.getY();
		if (player.getAngle() == 0) {
			posX += 40;
			posY += 20;
		} else if (player.getAngle() == 45) {
			posX += 57;
			posY += 8;
		} else if (player.getAngle() == 90) {
			posX += 5;
			posY -= 20;
		} else if (player.getAngle() == 135) {
			posX -= 10;
			posY -= 5;
		} else if (player.getAngle() == 180) {
			posX -= 15;
			posY += 20;
		} else if (player.getAngle() == 225) {
			posX -= 10;
			posY += 35;
		} else if (player.getAngle() == 270) {
			posY += 55;
		} else if (player.getAngle() == 315) {
			posX += 35;
			posY += 45;
		}
		Ammo ammoLeft = new Ammo(getName(), getDamage(), getSpeed(), posX, posY, (player.getAngle() + 10) % 360, 10,
				gameLogic);
		Ammo ammoMid = new Ammo(getName(), getDamage(), getSpeed(), posX, posY, player.getAngle(), 10, gameLogic);
		Ammo ammoRight = new Ammo(getName(), getDamage(), getSpeed(), posX, posY, (player.getAngle() + 350) % 360, 10,
				gameLogic);
		gameLogic.getAmmos().add(ammoLeft);
		gameLogic.getAmmos().add(ammoMid);
		gameLogic.getAmmos().add(ammoRight);
		gameLogic.addNewObject(ammoLeft);
		gameLogic.addNewObject(ammoMid);
		gameLogic.addNewObject(ammoRight);
		setAmmo(getAmmo() - 3);
	}

	@Override
	public void update() {
		if (getCountDown() != 0) {
			setCountDown(getCountDown() - 1);
			return;
		}
		if (KeyUtils.getKeyPressed(KeyCode.SPACE)) {
			if (getAmmo() <= 0)
				return;
			if (!isHolding()) {
				RenderableHolder.shotgunSound.play();
				shoot();
				setCountDown(getCooldown());
				setHolding(true);
			}
		} else {
			setHolding(false);
		}
	}

	@Override
	public void draw(GraphicsContext gc) {
		if (player.getAngle() == 0) {
			super.z = 11;
			gc.drawImage(RenderableHolder.shotgunRight, player.getX() + 17, player.getY() + 20, 40, 20);
		} else if (player.getAngle() == 45) {
			super.z = 9;
			gc.drawImage(RenderableHolder.shotgunRightUp, player.getX() + 15, player.getY() + 5, 60, 40);
		} else if (player.getAngle() == 90) {
			super.z = 9;
			gc.drawImage(RenderableHolder.shotgunUp, player.getX() + 3, player.getY() - 11, 20, 40);
		} else if (player.getAngle() == 135) {
			super.z = 9;
			gc.drawImage(RenderableHolder.shotgunLeftUp, player.getX() - 20, player.getY(), 55, 40);
		} else if (player.getAngle() == 180) {
			super.z = 11;
			gc.drawImage(RenderableHolder.shotgunLeft, player.getX() - 6, player.getY() + 20, 40, 20);
		} else if (player.getAngle() == 225) {
			super.z = 11;
			gc.drawImage(RenderableHolder.shotgunLeftDown, player.getX() - 5, player.getY() + 8, 60, 40);
		} else if (player.getAngle() == 270) {
			super.z = 11;
			gc.drawImage(RenderableHolder.shotgunDown, player.getX(), player.getY() + 20, 20, 40);
		} else if (player.getAngle() == 315) {
			super.z = 11;
			gc.drawImage(RenderableHolder.shotgunRightDown, player.getX() + 3, player.getY() + 3, 40, 55);
		}
	}

	public boolean isHolding() {
		return isHolding;
	}

	public void setHolding(boolean isHolding) {
		this.isHolding = isHolding;
	}

}
