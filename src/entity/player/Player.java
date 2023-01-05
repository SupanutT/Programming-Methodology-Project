package entity.player;

import java.util.ArrayList;

import entity.base.Entity;
import entity.gun.Gun;
import entity.gun.Shotgun;
import entity.gun.Sniper;
import entity.gun.Uzi;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import logic.GameLogic;
import sharedObject.RenderableHolder;
import utils.KeyUtils;

public class Player extends Entity {

	private static final int PLAYERSIZE = 20;
	private static final int MAXHEALTH = 100;
	private float speed = 2;
	private float angle = 270;
	private String name;
	private boolean fTop, fDown, fLeft, fRight;
	private boolean isInTop, isInBottom, isInLeft, isInRight;
	private boolean isHolding1, isHolding2, isHolding3, isHolding4;
	private boolean isMoving;

	private GameLogic gameLogic;
	private Gun gun;
	private ArrayList<Gun> guns;

	public Player(float x, float y, float health, GameLogic gameLogic) {
		super();
		super.z = 10;
		setX(x);
		setY(y);
		setHealth(health);
		setName("player");
		setMoving(false);
		this.gameLogic = gameLogic;
		this.guns = gameLogic.getGuns();
		this.gun = gameLogic.getGun();
	}

	@Override
	public void update() {
		if (gun == null)
			gun = gameLogic.getGun();
		selectGun(-1);
		if (gun.getAmmo() <= 0) {
			if (getGun().isPickedUp()) {
				selectGun(0);
				gameLogic.setGun(gun);
			}
		}
		isInTop = false;
		isInBottom = false;
		isInLeft = false;
		isInRight = false;
		for (int i = gameLogic.getWalls().size() - 1; i >= 0; i--) {
			if (isInTop == false)
				setInTop(gameLogic.getWalls().get(i).isInTop(getX(), getY(), 30));
			if (isInBottom == false)
				setInBottom(gameLogic.getWalls().get(i).isInBottom(getX(), getY(), 30));
			if (isInLeft == false)
				setInLeft(gameLogic.getWalls().get(i).isInLeft(getX(), getY(), 30));
			if (isInRight == false)
				setInRight(gameLogic.getWalls().get(i).isInRight(getX(), getY(), 30));
		}
		move();
	}

	@Override
	public void draw(GraphicsContext gc) {
		if (isMoving || !isMoving) {
			if (fTop && fRight) {
				gc.drawImage(RenderableHolder.playerUpRightImg, x, y, 50, 50);
			} else if (fTop && fLeft) {
				gc.drawImage(RenderableHolder.playerUpImg, x, y, 50, 50);
			} else if (fDown && fRight) {
				gc.drawImage(RenderableHolder.playerDownRightImg, x, y, 50, 50);
			} else if (fDown && fLeft) {
				gc.drawImage(RenderableHolder.playerDownLeftImg, x, y, 50, 50);
			} else if (fTop) {
				gc.drawImage(RenderableHolder.playerUpImg, x, y, 50, 50);
			} else if (fLeft) {
				gc.drawImage(RenderableHolder.playerLeftImg, x, y, 50, 50);
			} else if (fDown) {
				gc.drawImage(RenderableHolder.playerDownImg, x, y, 50, 50);
			} else if (fRight) {
				gc.drawImage(RenderableHolder.playerRightImg, x, y, 50, 50);
			} else {
				gc.drawImage(RenderableHolder.playerNormalRightImg, x, y, 50, 50);
			}
		} else {
			if (fLeft)
				gc.drawImage(RenderableHolder.playerNormalLeftImg, x, y, 50, 50);
			else if (fRight)
				gc.drawImage(RenderableHolder.playerNormalRightImg, x, y, 50, 50);
			else
				gc.drawImage(RenderableHolder.playerNormalLeftImg, x, y, 50, 50);
		}
	}

	public void selectGun(int gunIndex) {
		if (KeyUtils.getKeyPressed(KeyCode.DIGIT1) || gunIndex == 0) {
			if (!isHolding1) {
				RenderableHolder.textClickedSound.play();
				setGun(guns.get(0));
				gameLogic.setGun(guns.get(0));
				guns.get(1).setPickedUp(false);
				guns.get(2).setPickedUp(false);
				guns.get(3).setPickedUp(false);
				isHolding1 = true;
				isHolding2 = false;
				isHolding3 = false;
				isHolding4 = false;
				guns.get(1).setVisible(false);
				guns.get(2).setVisible(false);
				guns.get(3).setVisible(false);

			}
		} else if (KeyUtils.getKeyPressed(KeyCode.DIGIT2) || gunIndex == 1) {
			if (!isHolding2) {
				if (!(getGun() instanceof Shotgun)) {
					RenderableHolder.textClickedSound.play();
					setGun(guns.get(1));
					gameLogic.setGun(guns.get(1));
					getGun().setPickedUp(false);
					isHolding1 = false;
					isHolding2 = true;
					isHolding3 = false;
					isHolding4 = false;
					guns.get(1).setVisible(true);
					guns.get(2).setVisible(false);
					guns.get(3).setVisible(false);
					gameLogic.addNewObject(guns.get(1));
				}
			}
		} else if (KeyUtils.getKeyPressed(KeyCode.DIGIT3) || gunIndex == 2) {
			if (!isHolding3) {
				if (!(getGun() instanceof Uzi)) {
					RenderableHolder.textClickedSound.play();
					setGun(guns.get(2));
					gameLogic.setGun(guns.get(2));
					getGun().setPickedUp(false);
					isHolding1 = false;
					isHolding2 = false;
					isHolding3 = true;
					isHolding4 = false;
					guns.get(1).setVisible(false);
					guns.get(2).setVisible(true);
					guns.get(3).setVisible(false);
					gameLogic.addNewObject(guns.get(2));
				}
			}
		} else if (KeyUtils.getKeyPressed(KeyCode.DIGIT4) || gunIndex == 3) {
			if (!isHolding4) {
				if (!(getGun() instanceof Sniper)) {
					RenderableHolder.textClickedSound.play();
					setGun(guns.get(3));
					gameLogic.setGun(guns.get(3));
					getGun().setPickedUp(false);
					isHolding1 = false;
					isHolding2 = false;
					isHolding3 = false;
					isHolding4 = true;
					guns.get(1).setVisible(false);
					guns.get(2).setVisible(false);
					guns.get(3).setVisible(true);
					gameLogic.addNewObject(guns.get(3));
				}
			}
		}
	}

	public void move() {
		if (!KeyUtils.getKeyPressed(KeyCode.W) && !KeyUtils.getKeyPressed(KeyCode.UP)
				&& !KeyUtils.getKeyPressed(KeyCode.D) && !KeyUtils.getKeyPressed(KeyCode.RIGHT)
				&& !KeyUtils.getKeyPressed(KeyCode.S) && !KeyUtils.getKeyPressed(KeyCode.DOWN)
				&& !KeyUtils.getKeyPressed(KeyCode.A) && !KeyUtils.getKeyPressed(KeyCode.LEFT)) {
			isMoving = false;
		} else {
			fTop = false;
			fRight = false;
			fDown = false;
			fLeft = false;
			isMoving = true;

			if (KeyUtils.getKeyPressed(KeyCode.W) || KeyUtils.getKeyPressed(KeyCode.UP)) {
				fTop = true;
			}
			if (KeyUtils.getKeyPressed(KeyCode.D) || KeyUtils.getKeyPressed(KeyCode.RIGHT)) {
				fRight = true;
			}
			if (KeyUtils.getKeyPressed(KeyCode.S) || KeyUtils.getKeyPressed(KeyCode.DOWN)) {
				fDown = true;
			}
			if (KeyUtils.getKeyPressed(KeyCode.A) || KeyUtils.getKeyPressed(KeyCode.LEFT)) {
				fLeft = true;
			}

			if (fTop && fRight) {
				moveUpRight();
				angle = 45;
			} else if (fTop && fLeft) {
				moveUpLeft();
				angle = 135;
			} else if (fDown && fRight) {
				moveDownRight();
				angle = 315;
			} else if (fDown && fLeft) {
				moveDownLeft();
				angle = 225;
			} else if (fTop) {
				moveUp();
				angle = 90;
			} else if (fLeft) {
				moveLeft();
				angle = 180;
			} else if (fDown) {
				moveDown();
				angle = 270;
			} else if (fRight) {
				moveRight();
				angle = 0;
			}
		}
	}

	// Basic movement controls with the use of WSAD
	public void moveLeft() {
		if (getX() - PLAYERSIZE / 2 > 0 && isInRight() == false) {
			setX(getX() - speed);
		}
		setInRight(false);
	}

	public void moveRight() {
		if (getX() + PLAYERSIZE / 2 < GameLogic.getWidth() - 30 && isInLeft() == false) {
			setX(getX() + speed);
		}
		setInLeft(false);
	}

	public void moveUp() {
		if (getY() - PLAYERSIZE / 2 > 120 && isInBottom() == false) {
			setY(getY() - speed);
		}
		setInBottom(false);
	}

	public void moveDown() {
		if (getY() + PLAYERSIZE / 2 < 750 && isInTop() == false) {
			setY(getY() + speed);
		}
		setInTop(false);
	}

	// Diagonally right up movement
	public void moveUpRight() {
		if (getY() - PLAYERSIZE / 2 > 120 && isInBottom() == false) {
			setY(getY() - (speed / 1.5f));
		}

		if (getX() + PLAYERSIZE / 2 < GameLogic.getWidth() - 30 && isInLeft() == false) {
			setX(getX() + (speed / 1.5f));
		}

		setInBottom(false);
		setInLeft(false);
	}

	// diagonally down right movement
	public void moveDownRight() {
		if (getY() + PLAYERSIZE / 2 < GameLogic.getHeight() - 30 && isInTop() == false) {
			setY(getY() + (speed / 1.5f));
		}

		if (getX() + PLAYERSIZE / 2 < GameLogic.getWidth() - 30 && isInLeft() == false) {
			setX(getX() + (speed / 1.5f));
		}

		setInTop(false);
		setInLeft(false);
	}

	// diagonally up left movement
	public void moveUpLeft() {
		if (getY() - PLAYERSIZE / 2 > 120 && isInBottom() == false) {
			setY(getY() - (speed / 1.5f));
		}

		if (getX() - PLAYERSIZE / 2 > 0 && isInRight() == false) {
			setX(getX() - (speed / 1.5f));
		}

		setInBottom(false);
		setInRight(false);
	}

	// diagonal down left movement
	public void moveDownLeft() {
		if (getY() + PLAYERSIZE / 2 < GameLogic.getHeight() - 30 && isInTop() == false) {
			setY(getY() + (speed / 1.5f));
		}

		if (getX() - PLAYERSIZE / 2 > 0 && isInRight() == false) {
			setX(getX() - (speed / 1.5f));
		}

		setInTop(false);
		setInRight(false);
	}

	public boolean isInTop() {
		return isInTop;
	}

	public void setInTop(boolean inTop) {
		this.isInTop = inTop;
	}

	public boolean isInBottom() {
		return isInBottom;
	}

	public void setInBottom(boolean inBottom) {
		this.isInBottom = inBottom;
	}

	public boolean isInLeft() {
		return isInLeft;
	}

	public void setInLeft(boolean inLeft) {
		this.isInLeft = inLeft;
	}

	public boolean isInRight() {
		return isInRight;
	}

	public void setInRight(boolean inRight) {
		this.isInRight = inRight;
	}

	public static int getPlayersize() {
		return PLAYERSIZE;
	}

	public float getAngle() {
		return angle;
	}

	public void setAngle(float angle) {
		this.angle = angle;
	}

	public Gun getGun() {
		return gun;
	}

	public void setGun(Gun gun) {
		this.gun = gun;
	}

	public boolean isMoving() {
		return isMoving;
	}

	public void setMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}

	public ArrayList<Gun> getGuns() {
		return guns;
	}

	public void setGuns(ArrayList<Gun> guns) {
		this.guns = guns;
	}

	public boolean isfTop() {
		return fTop;
	}

	public void setfTop(boolean fTop) {
		this.fTop = fTop;
	}

	public boolean isfDown() {
		return fDown;
	}

	public void setfDown(boolean fDown) {
		this.fDown = fDown;
	}

	public boolean isfLeft() {
		return fLeft;
	}

	public void setfLeft(boolean fLeft) {
		this.fLeft = fLeft;
	}

	public boolean isfRight() {
		return fRight;
	}

	public void setfRight(boolean fRight) {
		this.fRight = fRight;
	}

	@Override
	public void setHealth(float health) {
		if (health < 0) {
			super.health = 0;
			setDestroyed();
		} else if (health > MAXHEALTH) {
			super.health = MAXHEALTH;
		} else {
			super.health = health;
		}
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isHolding1() {
		return isHolding1;
	}

	public void setHolding1(boolean isHolding1) {
		this.isHolding1 = isHolding1;
	}

	public boolean isHolding2() {
		return isHolding2;
	}

	public void setHolding2(boolean isHolding2) {
		this.isHolding2 = isHolding2;
	}

	public boolean isHolding3() {
		return isHolding3;
	}

	public void setHolding3(boolean isHolding3) {
		this.isHolding3 = isHolding3;
	}

	public boolean isHolding4() {
		return isHolding4;
	}

	public void setHolding4(boolean isHolding4) {
		this.isHolding4 = isHolding4;
	}

}