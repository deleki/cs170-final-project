package FinalProject;

import java.awt.Rectangle;

public class Projectile extends Entity {
	
	double lifespan;

	public Projectile(Rectangle bounds, String imageFile, double lifespan, double vx, double vy) {
		super(bounds, imageFile);
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
