package FinalProject;

import java.awt.Color;
import java.awt.Rectangle;

import acm.graphics.GCompound;
import acm.graphics.GImage;
import acm.graphics.GOval;

public class Projectile extends Entity{
	
	GOval projectile;
	
	public Projectile(double x, double y, double width, double height) {
		projectile = new GOval(x,y,width,height);
		projectile.setFillColor(Color.WHITE);
		projectile.setFilled(true);
	}
	
	public GOval shootProjectileOval() {
		
		return projectile;
	}
}
