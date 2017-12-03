package FinalProject;

import java.awt.Rectangle;
import java.util.Random;

public class Boss extends Entity {

	int shootingDelay = 0;

	public Boss(Rectangle bounds, String imageFile, double x, double y, double health) {
		super(bounds, imageFile, x, y, false, ENEMY);
		this.setHealth(health);
	}

	public void behavior(double delayMS) {
		if (this.hasDied()) {
			this.destroy();
		}
		shootingDelay++;
		Random random = new Random();
		int shotType = random.nextInt(3);
		if (shootingDelay % 350 == 0) {
			switch (shotType) {
			case 0:
				this.spreadshot();
				break;
			case 1:
				this.cannonfire();
				break;
			case 2:
				break;
			}
		}

	}

	public boolean hasDied() {
		return this.getHealth() < 0.000001;
	}

	public void spreadshot() {
		new Projectile(new Rectangle(8, 8), "enemyBullet.png", this.getX(),
				this.getY() + this.getSize().getHeight() / 2 - 5, false, 1, 0, Main.SCREEN_HEIGHT / 2);
		new Projectile(new Rectangle(8, 8), "enemyBullet.png", this.getX(),
				this.getY() + this.getSize().getHeight() / 2 - 5, false, 1, Main.SCREEN_WIDTH / 2,
				Main.SCREEN_HEIGHT / 2);
		new Projectile(new Rectangle(8, 8), "enemyBullet.png", this.getX(),
				this.getY() + this.getSize().getHeight() / 2 - 5, false, 1, -Main.SCREEN_WIDTH / 2,
				Main.SCREEN_HEIGHT / 2);
		new Projectile(new Rectangle(8, 8), "enemyBullet.png", this.getX(),
				this.getY() + this.getSize().getHeight() / 2 - 5, false, 1, Main.SCREEN_WIDTH / 4,
				Main.SCREEN_HEIGHT / 2);
		new Projectile(new Rectangle(8, 8), "enemyBullet.png", this.getX(),
				this.getY() + this.getSize().getHeight() / 2 - 5, false, 1, -Main.SCREEN_WIDTH / 4,
				Main.SCREEN_HEIGHT / 2);
	}
	
	public void cannonfire() {
		new Projectile(new Rectangle(200, 200), "cannonball.png", this.getX(),
				this.getY() + this.getSize().getHeight() / 2 -5, false, 1, Math.random() * 700 - 350,
				600 - Math.random() * 300);
	}

}
