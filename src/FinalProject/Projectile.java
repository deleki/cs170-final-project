package FinalProject;

import java.awt.Rectangle;

public class Projectile extends Entity {

	double lifespan;

	public Projectile(String imageFile, double x, double y, boolean friendly, double lifespan, double vx, double vy) {
		super(imageFile, x, y, friendly, PROJECTILE);
		this.setVelocity(vx, vy);
		this.lifespan = lifespan;
	}

	public void behavior(double delayMS) {
		lifespan -= delayMS;
		if (lifespan <= 0) {
			this.destroy();
		}
		if (this.getX() > Main.SCREEN_WIDTH || this.getX() < 0 || this.getY() > Main.SCREEN_HEIGHT || this.getY() < 0) {
			this.destroy();
		}
	}

}
