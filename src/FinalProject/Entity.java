package FinalProject;

import java.awt.Rectangle;

import acm.graphics.GCompound;
import acm.graphics.GImage;
import acm.graphics.GRectangle;

public abstract class Entity extends GCompound {
	private double vx = 0;
	private double vy = 0;

	private double ax = 0;
	private double ay = 0;

	private GImage image;

	private int health;

	private boolean friendly;
	boolean bossStatus = false;

	public static final int PLAYER = 0, ENEMY = 1, PROJECTILE = 2;
	private int typeHint;

	public Entity(String imageFile, double x, double y, boolean friendly, int typeHint) {
		image = new GImage(imageFile);
		this.add(image, -image.getSize().getWidth() / 2, -image.getSize().getHeight() / 2);

		this.friendly = friendly;
		this.typeHint = typeHint;

		Main.main.addEntity(this);
		this.setLocation(x, y);
	}

	public boolean isFriendly() {
		return friendly;
	}

	public int getTypeHint() {
		return typeHint;
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

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public GRectangle hitbox() {
		// System.out.println(this.getBounds().getX() + " " + this.getBounds().getY() +
		// " " + this.getBounds().getWidth() + " " + this.getBounds().getHeight());
		return this.getBounds();
	}

	public void update(double delayMS) {
		this.move(vx * delayMS, vy * delayMS);
		vx += ax * delayMS;
		vy += ay * delayMS;
		this.behavior(delayMS);
	}

	public void destroy() {
		Main.main.removeEntity(this);
	}

	public abstract void behavior(double delayMS);

}
