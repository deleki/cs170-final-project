package FinalProject;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

import acm.graphics.GLabel;
import acm.program.GraphicsProgram;

public class Main extends GraphicsProgram{
	
	ArrayList<Entity> entities = new ArrayList<Entity>();
	ArrayList<Entity> entitiesToAdd = new ArrayList<Entity>();
	ArrayList<Entity> entitiesToRemove = new ArrayList<Entity>();
	
	public static Main main;
	double delayMS = 1/60.0;
	private Timer timer;
	Player player;
	GLabel gameOver;
	GLabel playerHealth;
	public static final int SCREEN_WIDTH = 600;
	public static final int SCREEN_HEIGHT = 600;
	
	SpawningRules spawn;
	int time = 0;
	
	CollisionManager collisionManager;
	
	
	public void init() {
		main = this;
		this.setTitle("Shoot em up!");
        this.setSize(SCREEN_WIDTH,SCREEN_HEIGHT);
        this.setBackground(Color.BLACK);
        collisionManager = new CollisionManager();
        player = new Player(SCREEN_WIDTH/2,SCREEN_HEIGHT-100);
        timer = new Timer((int)(delayMS*1000), new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onTick();
            }
        });
        timer.start();
		
		collisionManager = new CollisionManager();
	}
	
	public void addEntity(Entity e) {
        entitiesToAdd.add(e);
    }
    
    public void removeEntity(Entity e) {
        entitiesToRemove.add(e);
    }
	
    public void onTick() {
        
        for (Entity e : entitiesToAdd) {
            entities.add(e); //add to array
            this.add(e); //add to screen

            if(e.isFriendly()) {
                collisionManager.friendlyEntities.add(e);
            } else {
                collisionManager.unfriendlyEntities.add(e);
            }
        }
        entitiesToAdd.clear();
        
        for (Entity e : entitiesToRemove) {
            entities.remove(e);
            this.remove(e);
            
            if(e.isFriendly()) {
                collisionManager.friendlyEntities.remove(e);
            } else {
                collisionManager.unfriendlyEntities.remove(e);
            }
        }
        entitiesToRemove.clear();
        
        for (Entity e: entities) {
            e.update(delayMS);
        }
        collisionManager.handleCollisions();
        
        //System.out.println(time);
        time++;
        
        if (time%50 == 0) {
            SpawningRules.spawnEnemiesInLine();
        }
        
        if (time > 1000000) {
            time = 0;
        }
        
        playerHealth = new GLabel(""+player.getHealth(),SCREEN_WIDTH - 100,SCREEN_HEIGHT - 100);
        playerHealth.setColor(Color.WHITE);
        this.add(playerHealth);
        
        if (player.getHealth()==0) {
        		gameOver = new GLabel("Game Over!", SCREEN_WIDTH - 200, SCREEN_HEIGHT - 200);
        		gameOver.setColor(Color.WHITE);
        		this.add(gameOver);
        }
    }
	
	public void run() {
		this.getGCanvas().requestFocus();
	}
	
	
}
