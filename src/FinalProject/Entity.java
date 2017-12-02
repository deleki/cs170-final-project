package FinalProject;

import acm.graphics.GCompound;
import acm.graphics.GRectangle;

public class Entity extends GCompound{
	private double vx;
	private double vy;

	private double ax;
	private double ay;

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
	
}
