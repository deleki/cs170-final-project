package FinalProject;

import java.awt.Rectangle;

public class BasicEnemy extends Entity {
	
	int shootingDelay=0;

	public BasicEnemy(String imageFile, double x, double y, int health) {
        super(imageFile, x, y, false, ENEMY);
        this.setHealth(health);
        this.setVelocity(Math.random()*100-50, 100);
    }

	public void behavior(double delayMS) {
		if (this.hasDied()) {
			this.destroy();
		}
		if (this.getY() > Main.SCREEN_HEIGHT) {
			this.destroy();
		}
		
		shootingDelay++;
		
		if (shootingDelay%200 == 0) {
			new Projectile("enemyBullet.png", this.getX(),
					this.getY() + this.getSize().getHeight() / 2 -5, false, 3, Math.random() * 350 - 125,
					300 - Math.random() * 150);
		}
		
	}
	
	public boolean hasDied(){
		return this.getHealth() < 0.000001;
	}


}
