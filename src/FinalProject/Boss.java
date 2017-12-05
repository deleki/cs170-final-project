package FinalProject;

import java.awt.Rectangle;
import java.util.Random;

import acm.graphics.GRectangle;

public class Boss extends Entity {

	int shootingDelay = 0;
	boolean isInScreen = false;
	Random random;
	public static final int HEALTH = 1750;
	// int shotType=0; //test to see if random shotType or structured shotType feels
	// better gameplay wise

	public Boss(String imageFile, double x, double y, int health) {
		super(imageFile, x, y, false, ENEMY);
		this.setHealth(health);
		this.setVelocity(0, 50);
		bossStatus = true;
	}

	public GRectangle hitbox() {
		GRectangle rect = new GRectangle(this.getX() - (Main.SCREEN_WIDTH / 2), this.getY(), Main.SCREEN_WIDTH, 25);
		return rect;
	}

	public void behavior(double delayMS) {
		if (this.hasDied()) {
			this.destroy();
			bossStatus = false;
		}

		if (this.getY() < 50.99999 && this.getY() > 50.00000001) {
			this.setVelocity(0, 0);
		}

		shootingDelay++;
		Random random = new Random();
		int shotType = random.nextInt(4);
		if (shootingDelay % 80 == 0) {
			switch (shotType) {
			case 0:
				this.spreadshot();
				break;
			case 1:
				this.cannonfire();
				break;
			case 2:
				this.shotgun();
				break;
			case 3:
				this.minionSpawn();
				break;
			}
		}
		/*
		 * shotType++; if (shotType >2) { shotType = 0; }
		 */

	}

	public boolean hasDied() {
		return this.getHealth() < 0.000001;
	}

	public boolean bossAlive() {
		return bossStatus;
	}

	public void spreadshot() {
		new Projectile("enemyBullet.png", this.getX(), this.getY() + this.getSize().getHeight() / 2 - 5, false, 2, 0,
				Main.SCREEN_HEIGHT / 2);
		new Projectile("enemyBullet.png", this.getX(), this.getY() + this.getSize().getHeight() / 2 - 5, false, 2,
				Main.SCREEN_WIDTH / 1.5, Main.SCREEN_HEIGHT / 2);
		new Projectile("enemyBullet.png", this.getX(), this.getY() + this.getSize().getHeight() / 2 - 5, false, 2,
				-Main.SCREEN_WIDTH / 1.5, Main.SCREEN_HEIGHT / 2);
		new Projectile("enemyBullet.png", this.getX(), this.getY() + this.getSize().getHeight() / 2 - 5, false, 2,
				Main.SCREEN_WIDTH / 3, Main.SCREEN_HEIGHT / 2);
		new Projectile("enemyBullet.png", this.getX(), this.getY() + this.getSize().getHeight() / 2 - 5, false, 2,
				-Main.SCREEN_WIDTH / 3, Main.SCREEN_HEIGHT / 2);
	}

	public void cannonfire() {
		new Projectile("cannonball.png", this.getX(), this.getY() + this.getSize().getHeight() / 2 - 75, false, 5,
				Math.random() * 150 - 75, 150 - Math.random() * 75);
	}

	public void shotgun() {
		random = new Random();
		double x = random.nextInt(Main.SCREEN_WIDTH - 100) + 100;
		for (int i = 0; i < 20; i++) {
			double lifetime = random.nextDouble() * 1.0 + 2.5;
			new Projectile("enemyBullet.png", x, this.getY() + this.getSize().getHeight() / 2 - 75, false, lifetime,
					Math.random() * 50 - 25, 200 - Math.random() * 100);
		}
	}

	public void minionSpawn() {
		SpawningRules.chooseRandomSpawningRule();
	}

}
