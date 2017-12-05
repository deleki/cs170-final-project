package FinalProject;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player extends Entity {
	public static final int UP = 38;
	public static final int RIGHT = 39;
	public static final int DOWN = 40;
	public static final int LEFT = 37;
	public static final int SHOOT = 32;

	private boolean upPressed = false;
	private boolean downPressed = false;
	private boolean rightPressed = false;
	private boolean leftPressed = false;
	private boolean shootPressed = false;

	private KeyListener keyListener;

	public Player(double x, double y) {
		super(new Rectangle(100, 100), "plane.png", x, y, true, PLAYER);
		this.setHealth(3);

		keyListener = new KeyListener() {

			public void keyTyped(KeyEvent e) {
			}

			public void keyPressed(KeyEvent e) {

				switch (e.getKeyCode()) {
				case UP:
					upPressed = true;
					break;
				case DOWN:
					downPressed = true;
					break;
				case RIGHT:
					rightPressed = true;
					break;
				case LEFT:
					leftPressed = true;
					break;
				case SHOOT:
					shootPressed = true;
					break;
				}
			}

			public void keyReleased(KeyEvent e) {

				switch (e.getKeyCode()) {
				case UP:
					upPressed = false;
					break;
				case DOWN:
					downPressed = false;
					break;
				case RIGHT:
					rightPressed = false;
					break;
				case LEFT:
					leftPressed = false;
					break;
				case SHOOT:
					shootPressed = false;
					break;
				}
			}
		};
		Main.main.addKeyListeners(keyListener);
	}

	public void behavior(double delayMS) {
		double vx_ = 0;
		double vy_ = 0;
		if (upPressed) {
			vy_ -= 1;
		}
		if (downPressed) {
			vy_ += 1;
		}
		if (rightPressed) {
			vx_ += 1;
		}
		if (leftPressed) {
			vx_ -= 1;
		}
		if (vx_ == 0 && vy_ == 0) {
			this.setVelocity(0, 0);
		} else {
			double c = Math.sqrt(vx_ * vx_ + vy_ * vy_);
			vx_ /= c;
			vy_ /= c;
			this.setVelocity(vx_ * 200, vy_ * 200);
		}
		if (shootPressed) {
			new Projectile(new Rectangle(8, 8), "bullet.png", this.getX(),
					this.getY() - this.getSize().getHeight() / 2 + 5, true, 1, Math.random() * 700 - 350,
					-600 - Math.random() * 300);
		}
		
		if (this.getHealth() <= 0) {
			this.destroy();
		}
	}

}
