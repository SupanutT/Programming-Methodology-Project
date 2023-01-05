package entity.zombie;

import entity.base.Attackable;
import entity.base.Entity;
import entity.base.Moveable;
import entity.player.Player;
import javafx.scene.canvas.GraphicsContext;
import logic.GameLogic;
import sharedObject.RenderableHolder;

public class Zombie extends Entity implements Attackable, Moveable {

	protected Player player;
	private float speed;
	private float angle = 270;
	private float damage;
	private float distX, distY;
	private boolean isInTop, isInBottom, isInLeft, isInRight;
	private GameLogic gameLogic;

	public Zombie(GameLogic gameLogic) {
		super();
		super.z = 10;
		setHealth(100);
		setDamage(0.4f);

		/* Height = 780 , Width = 1020 */
		int side = (int) (1 + Math.random() * 4);
		float randX = -50;
		float randY = 40;
		if (side == 1) { // Left Border
			randY = (float) (30 + Math.random() * 700);
		}
		if (side == 2) { // Bottom
			randX = (float) (Math.random() * 1020);
			randY = 830;
		}
		if (side == 3) { // Right
			randX = 1070;
			randY = (float) (30 + Math.random() * 700);
		}
		if (side == 4) { // Top
			randX = (float) (Math.random() * 1020);
		}

		setX(randX);
		setY(randY);
		setSpeed(0.7f);
		this.gameLogic = gameLogic;
		this.player = gameLogic.getPlayer();
	}

	@Override
	public void attack() {
		if (Math.min(player.getX() + 15, getX() + 15) > Math.max(player.getX() - 15, getX() - 15)) {
			if (Math.min(player.getY() + 15, getY() + 15) > Math.max(player.getY() - 15, getY() - 15)) {
				player.setHealth(player.getHealth() - damage);
			}
		}
	}

	@Override
	public void draw(GraphicsContext gc) {
		if (angle <= 50 || angle >= 355) {
			gc.drawImage(RenderableHolder.zombie1RightImg, x, y, 58, 58);
		} else if (angle <= 95) {
			gc.drawImage(RenderableHolder.zombie1UpRightImg, x, y, 50, 50);
		} else if (angle <= 140) {
			gc.drawImage(RenderableHolder.zombie1UpImg, x, y, 50, 50);
		} else if (angle <= 185) {
			gc.drawImage(RenderableHolder.zombie1UpImg, x, y, 50, 50);
		} else if (angle <= 230) {
			gc.drawImage(RenderableHolder.zombie1LeftImg, x, y, 58, 58);
		} else if (angle <= 275) {
			gc.drawImage(RenderableHolder.zombie1DownLeftImg, x, y, 58, 58);
		} else if (angle <= 320) {
			gc.drawImage(RenderableHolder.zombie1DownImg, x, y, 52, 52);
		} else {
			gc.drawImage(RenderableHolder.zombie1DownRightImg, x, y, 58, 58);
		}

	}

	@Override
	public void update() {
		if (player == null)
			return;
		isInTop = false;
		isInBottom = false;
		isInLeft = false;
		isInRight = false;
		for (int i = gameLogic.getWalls().size() - 1; i >= 0; i--) {
			if (isInTop == false)
				setInTop(gameLogic.getWalls().get(i).isInTop(getX(), getY(), 35));
			if (isInBottom == false)
				setInBottom(gameLogic.getWalls().get(i).isInBottom(getX(), getY(), 33));
			if (isInLeft == false)
				setInLeft(gameLogic.getWalls().get(i).isInLeft(getX(), getY(), 33));
			if (isInRight == false)
				setInRight(gameLogic.getWalls().get(i).isInRight(getX(), getY(), 33));
		}
		move();
		attack();
	}

	@Override
	public void move() {
		if (player == null)
			return;
		distX = player.getX() - getX();
		distY = player.getY() - getY();
		float sum = (float) Math.sqrt(distX * distX + distY * distY);
		float changeX = distX / sum * speed;
		float changeY = distY / sum * speed;
		angle = (float) ((Math.atan2(-changeY, changeX) * 180 / Math.PI + 360) % 360);
		if (sum > 30) {
			if (isInTop == false && isInLeft == false && isInRight == false && isInBottom == false) {
				setX(getX() + changeX);
				setY(getY() + changeY);

				// Acts if the top wall is the current face
			} else if (isInTop == true) {
				if (changeX < 0) {
					if (changeX > -0.5f) {
						setX(getX() - 0.5f);
					} else
						setX(getX() + changeX);
				} else {
					if (changeX < 0.5f) {
						setX(getX() + 0.5f);
					} else
						setX(getX() + changeX);
				}
				if (changeY < 0) {
					setY(getY() + changeY);
				}

				// Acts if the bottom wall is the current face
			} else if (isInBottom == true) {
				if (changeX < 0) {
					if (changeX > -0.5f) {
						setX(getX() - 0.5f);
					} else
						setX(getX() + changeX);
				} else {
					if (changeX < 0.5f) {
						setX(getX() + 0.5f);
					} else
						setX(getX() + changeX);
				}
				if (changeY > 0) {
					setY(getY() + changeY);
				}

				// Acts if the Left wall is current face
			} else if (isInLeft == true) {
				if (changeY < 0) {
					if (changeY > -0.8f) {
						setY(getY() - 0.8f);
					} else
						setY(getY() + changeY);
				} else {
					if (changeY < 0.8f) {
						setY(getY() + 0.8f);
					} else
						setY(getY() + changeY);
				}
				if (changeX < 0) {
					setX(getX() + changeX);
				}

				// Acts if the Right wall is the current face
			} else if (isInRight == true) {
				if (changeY < 0) {
					if (changeY > -0.8f) {
						setY(getY() - 0.8f);
					} else
						setY(getY() + changeY);
				} else {
					if (changeY < 0.8f) {
						setY(getY() + 0.8f);
					} else
						setY(getY() + changeY);
				}
				if (changeX > 0) {
					setX(getX() + changeX);
				}
			}
		}
	}

	@Override
	public void setDestroyed() {
		super.destroyed = true;
		RenderableHolder.boom.play();
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

	public float getAngle() {
		return angle;
	}

	public void setAngle(float angle) {
		this.angle = angle;
	}

	public GameLogic getGameLogic() {
		return gameLogic;
	}

	public void setGameLogic(GameLogic gameLogic) {
		this.gameLogic = gameLogic;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public float getDamage() {
		return damage;
	}

	public void setDamage(float damage) {
		this.damage = damage;
	}

}