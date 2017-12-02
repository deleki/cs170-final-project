package FinalProject;

import java.awt.Rectangle;

public class Projectile extends Entity {
	
	double lifespan;

	public Projectile(Rectangle bounds, String imageFile, double x, double y, double lifespan, double vx, double vy) {
		super(bounds, imageFile, x, y);
		this.setVelocity(vx, vy);
		this.lifespan = lifespan;
	}


	public void behavior(double delayMS) {
		lifespan -= delayMS;
		if (lifespan <= 0) {
			this.destroy();
		}
	}
	
}
