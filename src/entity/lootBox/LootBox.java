package entity.lootBox;

import entity.base.Entity;
import entity.gun.Gun;
import entity.player.Player;
import javafx.scene.canvas.GraphicsContext;
import logic.GameLogic;
import sharedObject.RenderableHolder;
import entity.gun.Shotgun;
import entity.gun.Sniper;
import entity.gun.Uzi;

public class LootBox extends Entity {
	private Gun gun;
	private Player player;
	private GameLogic gameLogic;

	public LootBox(Gun gun, GameLogic gameLogic) {
		super();
		super.z = 1;
		this.gun = gun;
		this.gameLogic = gameLogic;
		this.player = gameLogic.getPlayer();
	}

	public void randomSpawn() {
		setX((float) (50 + Math.random() * 920));
		setY((float) (130 + Math.random() * 500));
		while (isInWall()) {
			setX((float) (50 + Math.random() * 920));
			setY((float) (130 + Math.random() * 500));
		}
	}

	public boolean isInWall() {
		for (int i = 0; i < gameLogic.getWalls().size(); i++) {
			if (getX() < gameLogic.getWalls().get(i).getxRight() + 35
					&& getX() > gameLogic.getWalls().get(i).getxLeft()) {
				if (getY() < gameLogic.getWalls().get(i).getyBottom() + 35
						&& getY() > gameLogic.getWalls().get(i).getyTop()) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.drawImage(RenderableHolder.box, x, y, 30, 30);
	}

	@Override
	public void update() {
		/* Check if collected */
		if (!this.isDestroyed()) {
			if (player.getX() > getX() - 40 && player.getX() < getX() + 20 && player.getY() > getY() - 40
					&& player.getY() < getY() + 20) {
				collected();
			}
		}
	}

	public void collected() {
		RenderableHolder.lootSound.play();
		this.destroyed = true;
		gameLogic.setLootboxOnGround(false);
		gameLogic.setGun(gun);
		player.selectGun(0);
		if (gun instanceof Shotgun) {
			player.getGuns().get(1).setPickedUp(true);
			player.getGuns().get(1).setAmmo(gun.getAmmo());
			player.getGuns().get(1).setPickedUpAmmo(gun.getAmmo());
			player.selectGun(1);
		}
		if (gun instanceof Uzi) {
			player.getGuns().get(2).setPickedUp(true);
			player.getGuns().get(2).setAmmo(gun.getAmmo());
			player.getGuns().get(2).setPickedUpAmmo(gun.getAmmo());
			player.selectGun(2);
		}
		if (gun instanceof Sniper) {
			player.getGuns().get(3).setPickedUp(true);
			player.getGuns().get(3).setAmmo(gun.getAmmo());
			player.getGuns().get(3).setPickedUpAmmo(gun.getAmmo());
			player.selectGun(3);
		}
		player.getGun().setPickedUp(true);
	}

}
