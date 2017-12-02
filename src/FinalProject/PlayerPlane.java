package FinalProject;

import java.awt.Rectangle;

import acm.graphics.GImage;

public class PlayerPlane extends PlayerUnit{
	
	GImage plane;
	
	public PlayerPlane(Rectangle bounds) {
		super(bounds);
		plane = new GImage("plane.png");
		this.add(plane);
	}
	
	public void dies(){
		
	}
}
