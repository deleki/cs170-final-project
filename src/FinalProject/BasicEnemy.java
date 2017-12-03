package FinalProject;

import java.awt.Rectangle;

public class BasicEnemy extends Entity {
	
	int shootingDelay=0;

	public BasicEnemy(Rectangle bounds, String imageFile, double x, double y, double health) {
        super(bounds, imageFile, x, y, false, ENEMY);
        this.setHealth(health);
        this.setVelocity(Math.random()*100-50, 100);
    }

	public void behavior(double delayMS) {
		if (this.hasDied()) {
			this.destroy();
		}
		shootingDelay++;
		
		if (shootingDelay%200 == 0) {
			new Projectile(new Rectangle(8, 8), "enemyBullet.png", this.getX(),
					this.getY() + this.getSize().getHeight() / 2 -5, false, 1, Math.random() * 700 - 350,
					600 - Math.random() * 300);
		}
		
	}
	
	public boolean hasDied(){
		return this.getHealth() < 0.000001;
	}


}
