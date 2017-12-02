package FinalProject;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player extends Entity {
	public static final int UP = 38;
	public static final int RIGHT = 39;
	public static final int DOWN = 40;
	public static final int LEFT = 37;
	private boolean upPressed = false;
	private boolean downPressed = false;
	private boolean rightPressed = false;
	private boolean leftPressed = false;

	private KeyListener keyListener;

	public Player() {
		super(new Rectangle(200, 200), "plane.png");

		keyListener = new KeyListener() {

			public void keyTyped(KeyEvent e) {
			}

			public void keyPressed(KeyEvent e) {

				switch (e.getKeyCode()) {
				case UP:
					upPressed = true; break;
				case DOWN:
					downPressed = true; break;
				case RIGHT:
					rightPressed = true; break;
				case LEFT:
					leftPressed = true; break;
				}
			}

			public void keyReleased(KeyEvent e) {

				switch (e.getKeyCode()) {
				case UP:
					upPressed = false; break;
				case DOWN:
					downPressed = false; break;
				case RIGHT:
					rightPressed = false; break;
				case LEFT:
					leftPressed = false; break;
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
			this.setVelocity(vx_ * 100, vy_ * 100);
		}
		Projectile p = new Projectile(new Rectangle (8,8), "bullet.png", 1, Math.random()*50-25, -150-Math.random()*90);
		p.setLocation(this.getX(), this.getY());
	}

}
