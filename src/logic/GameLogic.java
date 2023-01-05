package logic;

import java.util.ArrayList;
import java.util.List;

import drawing.Field;
import drawing.Header;
import entity.base.Ammo;
import entity.base.Entity;
import entity.gun.Gun;
import entity.gun.Shotgun;
import entity.gun.Sniper;
import entity.gun.Uzi;
import entity.lootBox.LootBox;
import entity.player.Player;
import entity.wall.Wall;
import entity.zombie.BossZombie;
import entity.zombie.FastZombie;
import entity.zombie.Zombie;
import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import sharedObject.RenderableHolder;
import utils.KeyUtils;

public class GameLogic {

	private final static GameLogic instance = new GameLogic();

	private static List<Entity> gameObjectContainer;
	private static ArrayList<Gun> guns;
	private static ArrayList<Ammo> ammos;
	private static ArrayList<Ammo> bossAmmos;
	private static ArrayList<Zombie> zombies;
	private static ArrayList<Wall> walls;

	private static Gun gun;
	private static Shotgun shotgun;
	private static Uzi uzi;
	private static Sniper sniper;
	private static Player player;
	private static Zombie zombie;
	private static FastZombie fastZombie;
	private static BossZombie bossZombie;
	private static Wall wall;
	private static LootBox lootBox;

	private static boolean isOver;
	private static boolean isPressing;
	private static boolean isPausing;

	private static int roundCount = 1;
	private static int score;
	private static int zombieLeft;
	private static int ammoCount = 0;
	private static boolean isLootboxOnGround;

	private final static int width = 1020;
	private final static int height = 780;

	public GameLogic() {
		gameObjectContainer = new ArrayList<>();

		Header header = new Header();
		RenderableHolder.getInstance().add(header);

		Field field = new Field();
		RenderableHolder.getInstance().add(field);

		/* Initialize ArrayList */
		walls = new ArrayList<>();
		zombies = new ArrayList<>();
		ammos = new ArrayList<>();
		bossAmmos = new ArrayList<>();
		guns = new ArrayList<>();

		/* Instantiate Player and Gun(default) */
		player = new Player(385, 285, 100, this);
		gun = new Gun(this);
		shotgun = new Shotgun(0, false, this);
		uzi = new Uzi(0, false, this);
		sniper = new Sniper(0, false, this);

		guns.add(gun);
		guns.add(shotgun);
		guns.add(uzi);
		guns.add(sniper);

		/* Generate Walls */
		if (walls.size() != 24) {
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 6; j++) {
					wall = new Wall(105 + 150 * j, 190 + 150 * i);
					walls.add(wall);
					addNewObject(wall);
				}
			}
		}

		zombie = new Zombie(this);
		fastZombie = new FastZombie(this);
		bossZombie = new BossZombie(this);

		/* addObjects */
		addNewObject(player);
		addNewObject(gun);

		addNewObject(zombie);
		addNewObject(fastZombie);

		zombies.add(zombie);
		// zombies.add(bossZombie);
		zombies.add(fastZombie);
		lootBox = new LootBox(new Shotgun(12, true, this), this);
		lootBox.setDestroyed();
		addNewObject(lootBox);
		zombieLeft = zombies.size();

		isOver = false;
		;
	}

	public void gameStart() {
		gameObjectContainer.clear();

		Header header = new Header();
		RenderableHolder.getInstance().add(header);

		Field field = new Field();
		RenderableHolder.getInstance().add(field);

		/* Initialize ArrayList */
		walls.clear();
		zombies.clear();
		ammos.clear();
		bossAmmos.clear();
		guns.clear();

		/* Instantiate Player and Gun(default) */
		roundCount = 1;
		score = 0;
		zombieLeft = 0;
		isLootboxOnGround = false;

		player = new Player(385, 285, 100, this);
		gun = new Gun(this);
		shotgun = new Shotgun(0, false, this);
		uzi = new Uzi(0, false, this);
		sniper = new Sniper(0, false, this);

		guns.add(gun);
		guns.add(shotgun);
		guns.add(uzi);
		guns.add(sniper);
		player.selectGun(0);

		/* Generate Walls */
		if (walls.size() != 24) {
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 6; j++) {
					wall = new Wall(105 + 150 * j, 190 + 150 * i);
					walls.add(wall);
					addNewObject(wall);
				}
			}
		}

		zombie = new Zombie(this);
		fastZombie = new FastZombie(this);
		bossZombie = new BossZombie(this);

		/* addObjects */
		addNewObject(player);
		addNewObject(gun);

		addNewObject(zombie);
		addNewObject(fastZombie);

		zombies.add(zombie);
		zombies.add(fastZombie);
		lootBox = new LootBox(new Shotgun(12, true, this), this);
		lootBox.setDestroyed();
		addNewObject(lootBox);

		zombieLeft = zombies.size();

		isOver = false;
	}

	public static GameLogic getInstance() {
		return instance;
	}

	public void logicUpdate() {

		/* Update Boss Ammo */
		for (int i = bossAmmos.size() - 1; i >= 0; i--) {
			if (bossAmmos.get(i) != null) {
				bossAmmos.get(i).update();
				if (bossAmmos.get(i).isInWall()) {
					bossAmmos.get(i).Hit();
					bossAmmos.remove(i);
				} else if (isHit(bossAmmos.get(i), player)) {
					bossAmmos.remove(i);
				}
			}
		}

		/* Update Ammo */
		for (int j = ammos.size() - 1; j >= 0; j--) {
			if (ammos.get(j) != null) {
				ammos.get(j).update();
				if (ammos.get(j).isOutOfBound()) {
					ammos.get(j).setDestroyed();
					ammos.remove(j);
				} else if (ammos.get(j).isInWall()) {
					ammos.get(j).setDestroyed();
					ammos.get(j).Hit();
					ammos.remove(j);
				}
			}
		}

		/* Update Player, Gun, and Random spawn lootBox */
		player.update();
		randomSpawnLootBox();
		lootBox.update();
		player.getGun().update();

		/* Update Zombies */
		for (int i = zombies.size() - 1; i >= 0; i--) {
			if (zombies.get(i) != null) {
				if (zombies.get(i).getHealth() != 0) {
					zombies.get(i).update();
					for (int j = ammos.size() - 1; j >= 0; j--) {
						if (ammos.get(j) != null) {
							if (isHit(ammos.get(j), zombies.get(i))) {
								ammos.get(j).setDestroyed();
								ammos.remove(j);
							}
						}
					}

					if (zombies.get(i).getHealth() == 0) {
						if (zombies.get(i) instanceof BossZombie) {
							player.setHealth(player.getHealth() + 10);
							score += 100;
						} else if (zombies.get(i) instanceof Zombie) {
							player.setHealth(player.getHealth() + 5);
							score += 25;
						}

						zombies.get(i).setDestroyed();
						zombies.remove(i);
						zombieLeft--;
					}
				}
			}
		}
	}

	public boolean isPause() {
		if (KeyUtils.getKeyPressed(KeyCode.P)) {
			if (!isPressing) {
				isPressing = true;
				if (isPausing) {
					isPausing = false;
					return false;
				} else {
					isPausing = true;
					return true;
				}
			}
		} else {
			isPressing = false;
			if (isPausing) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	public boolean isHit(Ammo ammo, Entity target) {
		/*
		 * Return true if ammo hit target and reduce target's health by Ammo's damage
		 */
		if (ammo.getX() > target.getX() - 30 && ammo.getX() < target.getX() + 50 && ammo.getY() > target.getY() - 10
				&& ammo.getY() < target.getY() + 60) {
			ammo.Hit();
			target.setHealth(target.getHealth() - ammo.getDamage());
			return true;
		}
		return false;
	}

	public void gameUpdate() {
		checkGameOver();
		if (isOver) {
			return;
		}
		if (isPause()) {
			return;
		}
		if (isRoundOver())
			roundBegin();
		else
			logicUpdate();

	}

	public void checkGameOver() {
		if (player.getHealth() <= 0) {
			setOver(true);
		}
	}

	public void roundBegin() {
		roundCount++;
		/* Spawn Zombies */
		for (int i = 0; i < roundCount + roundCount / 3; i++) {
			float randZombie = (float) (Math.random() * 1.5);
			if (randZombie <= 1) {
				Zombie zombie = new Zombie(this);
				addNewObject(zombie);
				zombies.add(zombie);
			} else {
				FastZombie fastZombie = new FastZombie(this);
				addNewObject(fastZombie);
				zombies.add(fastZombie);
			}
		}

		/* Spawn Boss */
		if (roundCount >= 5) {
			int bossNum = (roundCount - 3) / 2;
			for (int i = 0; i < bossNum; i++) {
				BossZombie bossZombie = new BossZombie(this);
				addNewObject(bossZombie);
				zombies.add(bossZombie);
			}

		}

		/* Spawn Lootbox */
		if (roundCount >= 4) {
			spawnLootBox();
			isLootboxOnGround = true;
		}

		/* Change value in some variables */
		zombieLeft = zombies.size();
	}

	public void spawnShotgunLootBox(int randAmmo) {
		lootBox = new LootBox(new Shotgun(randAmmo * 3, true, this), this);
		lootBox.randomSpawn();
		addNewObject(lootBox);
	}

	public void spawnUziLootBox(int randAmmo) {
		lootBox = new LootBox(new Uzi(randAmmo * 20, true, this), this);
		lootBox.randomSpawn();
		addNewObject(lootBox);
	}

	public void spawnSniperLootBox(int randAmmo) {
		lootBox = new LootBox(new Sniper(randAmmo, true, this), this);
		lootBox.randomSpawn();
		addNewObject(lootBox);
	}

	public void spawnLootBox() {
		if (!isLootboxOnGround) {
			if (roundCount < 4)
				return;
			float randGun = (float) (Math.random() * 3);
			int randAmmo = 10 + (int) (Math.random() * roundCount);
			if (roundCount == 4) {
				spawnShotgunLootBox(randAmmo);
			} else if (roundCount == 7) {
				spawnUziLootBox(randAmmo);
			} else if (roundCount == 10) {
				spawnSniperLootBox(randAmmo);

			} else if (roundCount <= 6) {
				spawnShotgunLootBox(randAmmo);
			} else if (roundCount <= 9) {
				if (randGun <= 1.5f) {
					spawnShotgunLootBox(randAmmo);
				} else {
					spawnUziLootBox(randAmmo);
				}
			} else if (roundCount > 10) {
				if (randGun <= 1) {
					spawnShotgunLootBox(randAmmo);
				} else if (randGun <= 2) {
					spawnUziLootBox(randAmmo);
				} else {
					spawnSniperLootBox(randAmmo);
				}
			}
			isLootboxOnGround = true;
		}
	}

	public void randomSpawnLootBox() {
		if (Math.random() * 1000 < 1 && Math.random() * roundCount > 3) {
			spawnLootBox();
		}
	}

	public boolean isRoundOver() {
		if (zombieLeft == 0)
			return true;
		return false;
	}

	public void addNewObject(Entity entity) {
		gameObjectContainer.add(entity);
		RenderableHolder.getInstance().add(entity);
	}

	// change score 123 into 000123
	public static String intToString(int num, int digits) {
		String output = Integer.toString(num);
		if (output.length() < digits)
			output = "0".repeat(digits - output.length()) + output;
		return output;
	}

	/* GETTERS AND SETTERS */
	public ArrayList<Ammo> getAmmos() {
		return ammos;
	}

	public void setAmmos(ArrayList<Ammo> ammos) {
		this.ammos = ammos;
	}

	public ArrayList<Zombie> getZombies() {
		return zombies;
	}

	public void setZombies(ArrayList<Zombie> zombies) {
		this.zombies = zombies;
	}

	public boolean isOver() {
		return isOver;
	}

	public void setOver(boolean isOver) {
		this.isOver = isOver;
	}

	public void setAmmoCount(int ammoCount) {
		this.ammoCount = ammoCount;
	}

	public int getAmmoCount() {
		return ammoCount;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public static int getWidth() {
		return width;
	}

	public static int getHeight() {
		return height;
	}

	public static Gun getGun() {
		return gun;
	}

	public void setGun(Gun gun) {
		this.gun = gun;
	}

	public ArrayList<Wall> getWalls() {
		return walls;
	}

	public void setWalls(ArrayList<Wall> walls) {
		this.walls = walls;
	}

	public ArrayList<Ammo> getBossAmmos() {
		return bossAmmos;
	}

	public void setBossAmmos(ArrayList<Ammo> bossAmmos) {
		this.bossAmmos = bossAmmos;
	}

	public ArrayList<Gun> getGuns() {
		return guns;
	}

	public void setGuns(ArrayList<Gun> guns) {
		this.guns = guns;
	}

	public boolean isLootboxOnGround() {
		return isLootboxOnGround;
	}

	public void setLootboxOnGround(boolean isLootboxOnGround) {
		this.isLootboxOnGround = isLootboxOnGround;
	}

	public static int getRoundCount() {
		return roundCount;
	}

	public void setRoundCount(int roundCount) {
		GameLogic.roundCount = roundCount;
	}

	public static int getScore() {
		return score;
	}

	public void setScore(int score) {
		GameLogic.score = score;
	}

	public static int getZombieLeft() {
		return zombieLeft;
	}

	public void setZombieLeft(int zombieLeft) {
		GameLogic.zombieLeft = zombieLeft;
	}

	public static boolean isPausing() {
		return isPausing;
	}

	public static void setPausing(boolean isPausing) {
		GameLogic.isPausing = isPausing;
	}

	public boolean isPressing() {
		return isPressing;
	}

	public void setPressing(boolean isPressing) {
		this.isPressing = isPressing;
	}

}