package FinalProject;

import java.awt.Rectangle;

public class BasicEnemy extends Entity {

	public BasicEnemy(Rectangle bounds, String imageFile, double x, double y, double vx, double vy, double health) {
		super(bounds, imageFile, x, y);
	}

	public void behavior(double delayMS) {
		//randy 
		if (this.hasDied()) {
			this.destroy();
		}
		
	}
	
	public boolean hasDied(){
		if (this.getHealth() < 0.00001) {
			return true;
		}
		else return false;
	}


}
