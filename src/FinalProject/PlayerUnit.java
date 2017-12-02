package FinalProject;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.Timer;

import acm.graphics.GCompound;
import acm.graphics.GRect;

public class PlayerUnit extends GCompound{
	public static final int UP = 38;
    public static final int RIGHT = 39;
    public static final int DOWN = 40;
    public static final int LEFT = 37;
    public static final int STOPPED = -1;
    public static final int FIRE = 32;
    
    ArrayList<Entity> projectiles = new ArrayList<Entity>();
    PlayerPlane player;
    
    private GRect rect;
    private int direction;
    private KeyListener keyListener;
    private Timer timer;
    private int delay = 10;
    private int step = 1;
    private Rectangle bounds;
    
    public KeyListener getKeyListener() {
        return keyListener;
    }
    
    public int getDelay() {
        return delay;
    }
    
    public int getStep() {
        return step;
    }
    
    public void setDelay(int delay) {
        this.delay = delay;
    }
    
    public void setStep(int step) {
        this.step = step;
    }
    
    public void setBounds(int minX, int maxX, int minY, int maxY) {
        bounds = new Rectangle(maxX-minX, maxY-minY);
        bounds.setLocation(minX, minY);
    }
    
    public boolean inBounds() {
        return (bounds.contains(this.getX(), this.getY()) &&
                bounds.contains(this.getX() + this.getWidth(), 
                                this.getY() + this.getHeight()));
    }
    
    public PlayerUnit(Rectangle bounds) {
        this.bounds = bounds;
        
        keyListener = new KeyListener() {

            public void keyPressed(KeyEvent e) {
                direction = e.getKeyCode();
                PlayerUnit.this.timer.start();
            }

            public void keyReleased(KeyEvent arg0) {
            	PlayerUnit.this.timer.stop();
            }
            public void keyTyped(KeyEvent arg0) {}};
        
        timer = new Timer(delay, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                PlayerUnit.this.timer.start();
                switch (direction) {
                case LEFT : 
                    PlayerUnit.this.move(-step,0);
                    if (! inBounds()) {
                        PlayerUnit.this.move(step,0);
                    }
                    break;
                case UP : 
                    PlayerUnit.this.move(0,-step);
                    if (! inBounds()) {
                        PlayerUnit.this.move(0,step);
                    }
                    break;
                case RIGHT : 
                    PlayerUnit.this.move(step,0);
                    if (! inBounds()) {
                        PlayerUnit.this.move(-step,0);
                    }
                    break;
                case DOWN : 
                    PlayerUnit.this.move(0,step);
                    if (! inBounds()) {
                        PlayerUnit.this.move(0,-step);
                    }
                    break;
                case FIRE: 
                	projectiles.add(new Projectile(5, 10, player.getX(), player.getY()));
    					for (int i = 0; i < projectiles.size(); i++) {
    						System.out.println("SPACE");
    						add(((Projectile) projectiles.get(i)).shootProjectileOval());
    					}
                		break;
                case STOPPED :
                    break;
                }
            }});
    }
}
