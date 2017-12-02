package FinalProject;

import java.awt.Rectangle;

import acm.graphics.GCompound;
import acm.graphics.GImage;
import acm.graphics.GRectangle;

public abstract class Entity extends GCompound{
	private double vx=0;
	private double vy=0;

	private double ax=0;
	private double ay=0;
	
	private GImage image;
	
	public Entity(Rectangle bounds, String imageFile) {
		image = new GImage(imageFile);
		this.add(image);
	}
	public double getXVelocity() {
		return vx;
	}

	public double getYVelocity() {
		return vy;
	}

	public double getXAcceleration() {
		return ax;
	}

	public double getYAcceleration() {
		return ay;
	}

	public void setVelocity(double vx, double vy) {
		this.vx = vx;
		this.vy = vy;
	}

	public void setAcceleration(double ax, double ay) {
		this.ax = ax;
		this.ay = ay;
	}
	
	public GRectangle hitbox() {
		return this.getBounds();
	}
	
	public void update(double delayMS) {
		this.move(vx*delayMS, vy*delayMS);
		vx += ax*delayMS;
		vy += ay*delayMS;
		this.behavior();
		System.out.println(vx);
	}
	
	public abstract void behavior();
	
}
