package entity.gun;

import entity.base.Ammo;
import entity.player.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import logic.GameLogic;
import sharedObject.RenderableHolder;
import utils.KeyUtils;

public class Sniper extends Gun {
	private Player player;
	private GameLogic gameLogic;
	private boolean isHolding;

	public Sniper(int ammo, boolean isPickedUp, GameLogic gameLogic) {
		super(gameLogic);
		setName("Buster Rifle");
		setDamage(100);
		setSpeed(70);
		setCooldown(120);
		setAmmo(ammo);
		setRecoilDistance(10);
		setPickedUp(isPickedUp);
		setPickedUpAmmo(ammo);
		this.gameLogic = gameLogic;
		this.player = gameLogic.getPlayer();
	}

	@Override
	public void shoot() {
		calculateRecoil();
		float posX = player.getX();
		float posY = player.getY();
		if (player.getAngle() == 0) {
			posX += 60;
			posY += 20;
		} else if (player.getAngle() == 45) {
			posX += 60;
			posY -= 15;
		} else if (player.getAngle() == 90) {
			posX += 11;
			posY -= 24;
		} else if (player.getAngle() == 135) {
			posX -= 10;
			posY -= 5;
		} else if (player.getAngle() == 180) {
			posX -= 19;
			posY += 21;
		} else if (player.getAngle() == 225) {
			posX -= 1;
			posY += 50;
		} else if (player.getAngle() == 270) {
			posY += 55;
			posX += 5;
		} else if (player.getAngle() == 315) {
			posX += 39;
			posY += 45;
		}
		Ammo ammo = new Ammo(getName(), getDamage(), getSpeed(), posX, posY, player.getAngle(), 100000, gameLogic);
		gameLogic.getAmmos().add(ammo);
		gameLogic.addNewObject(ammo);
		setAmmo(getAmmo() - 1);
	}

	@Override
	public void update() {
		if (getCountDown() != 0) {
			setCountDown(getCountDown() - 1);
			return;
		}
		if (KeyUtils.getKeyPressed(KeyCode.SPACE)) {
			if (!isHolding()) {
				if (getAmmo() <= 0)
					return;
				RenderableHolder.railgunSound.play();
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
			gc.drawImage(RenderableHolder.busterRifleRight, player.getX() + 5, player.getY() + 15, 60, 17);
		} else if (player.getAngle() == 45) {
			super.z = 9;
			gc.drawImage(RenderableHolder.busterRifleRightUp, player.getX() + 15, player.getY() - 13, 52, 52);
		} else if (player.getAngle() == 90) {
			super.z = 9;
			gc.drawImage(RenderableHolder.busterRifleUp, player.getX() + 5, player.getY() - 15, 17, 60);
		} else if (player.getAngle() == 135) {
			super.z = 9;
			gc.drawImage(RenderableHolder.busterRifleLeftUp, player.getX() - 14, player.getY() - 10, 52, 52);
		} else if (player.getAngle() == 180) {
			super.z = 11;
			gc.drawImage(RenderableHolder.busterRifleLeft, player.getX() - 14, player.getY() + 15, 60, 17);
		} else if (player.getAngle() == 225) {
			super.z = 11;
			gc.drawImage(RenderableHolder.busterRifleLeftDown, player.getX(), player.getY() + 4, 52, 52);
		} else if (player.getAngle() == 270) {
			super.z = 11;
			gc.drawImage(RenderableHolder.busterRifleDown, player.getX(), player.getY(), 17, 60);
		} else if (player.getAngle() == 315) {
			super.z = 11;
			gc.drawImage(RenderableHolder.busterRifleRightDown, player.getX(), player.getY() + 4, 52, 52);
		}
	}

	public boolean isHolding() {
		return isHolding;
	}

	public void setHolding(boolean isHolding) {
		this.isHolding = isHolding;
	}

}
