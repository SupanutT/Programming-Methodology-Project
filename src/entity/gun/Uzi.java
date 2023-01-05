package entity.gun;

import entity.base.Ammo;
import entity.player.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import logic.GameLogic;
import sharedObject.RenderableHolder;
import utils.KeyUtils;

public class Uzi extends Gun {
	private Player player;
	private GameLogic gameLogic;

	public Uzi(int ammo, boolean isPickedUp, GameLogic gameLogic) {
		// speed = 20
		super(gameLogic);
		setName("UZI");
		setDamage(10);
		setSpeed(50);
		setCooldown(5);
		setAmmo(ammo);
		setPickedUp(isPickedUp);
		setPickedUpAmmo(ammo);
		setRecoilDistance(2);
		setRecoilAngle(1.5f);
		this.gameLogic = gameLogic;
		this.player = gameLogic.getPlayer();
	}

	@Override
	public void shoot() {
		calculateRecoil();
		float posX = player.getX();
		float posY = player.getY();
		if (player.getAngle() == 0) {
			posX += 43;
			posY += 26;
		} else if (player.getAngle() == 45) {
			posX += 50;
			posY += 5;
		} else if (player.getAngle() == 90) {
			posX += 5;
			posY -= 20;
		} else if (player.getAngle() == 135) {
			posX -= 10;
			posY -= 5;
		} else if (player.getAngle() == 180) {
			posX -= 15;
			posY += 22;
		} else if (player.getAngle() == 225) {
			posX += 18;
			posY += 38;
		} else if (player.getAngle() == 270) {
			posX += 5;
			posY += 55;
		} else if (player.getAngle() == 315) {
			posX += 32;
			posY += 42;
		}

		Ammo ammo = new Ammo(getName(), getDamage(), getSpeed(), posX, posY, twoWayRecoilCalculate(), 100000,
				gameLogic);
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
			if (getAmmo() <= 0)
				return;
			RenderableHolder.uziSound.play();
			shoot();
			setCountDown(getCooldown());
		}
	}

	@Override
	public void draw(GraphicsContext gc) {
		if (player.getAngle() == 0) {
			super.z = 11;
			gc.drawImage(RenderableHolder.uziRight, player.getX() + 22, player.getY() + 23, 35, 17);
		} else if (player.getAngle() == 45) {
			super.z = 9;
			gc.drawImage(RenderableHolder.uziRightUp, player.getX() + 15, player.getY() + 8, 55, 30);
		} // not same
		else if (player.getAngle() == 90) {
			super.z = 9;
			gc.drawImage(RenderableHolder.uziUp, player.getX() + 6, player.getY() - 10, 17, 35);
		} else if (player.getAngle() == 135) {
			super.z = 9;
			gc.drawImage(RenderableHolder.uziLeftUp, player.getX() - 10, player.getY() - 3, 30, 40);
		} else if (player.getAngle() == 180) {
			super.z = 11;
			gc.drawImage(RenderableHolder.uziLeft, player.getX() - 7, player.getY() + 23, 35, 17);
		} else if (player.getAngle() == 225) {
			super.z = 11;
			gc.drawImage(RenderableHolder.uziLeftDown, player.getX() + 18, player.getY() + 12, 30, 40);
		} else if (player.getAngle() == 270) {
			super.z = 11;
			gc.drawImage(RenderableHolder.uziDown, player.getX() + 4, player.getY() + 25, 17, 35);
		} else if (player.getAngle() == 315) {
			super.z = 11;
			gc.drawImage(RenderableHolder.uziRightDown, player.getX() + 4, player.getY() + 11, 32, 42);
		}
	}
}
