package FinalProject;

import java.awt.Rectangle;

public class Enemy extends Entity {

	public Enemy(Rectangle bounds, String imageFile, double x, double y, double lifespan, double vx, double vy,
			double health) {
		super(bounds, imageFile, x, y);
	}

	public void behavior(double delayMS) {

	}

}
