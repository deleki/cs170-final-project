package FinalProject;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Timer;

import acm.graphics.GLabel;
import acm.program.GraphicsProgram;

public class Main extends GraphicsProgram{
	
	ArrayList<Entity> entities = new ArrayList<Entity>();
	ArrayList<Entity> entitiesToAdd = new ArrayList<Entity>();
	ArrayList<Entity> entitiesToRemove = new ArrayList<Entity>();
	
	public static final int SCREEN_WIDTH = 600;
	public static final int SCREEN_HEIGHT = 600;
	public static final int THRESHOLD = 20;
	
	public static Main main;
	double delayMS = 1/60.0;
	private Timer timer;
	Player player;
	GLabel gameOver;
	GLabel victory;
	GLabel playerHealth;
	GLabel defeatedEnemyCount;
	int playerHealthCopy = 3;
	
	SpawningRules spawn;
	int time = 0;
	int enemiesDefeated = 0;
	boolean bossSpawned = false;
	Boss boss;
	
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
		
		playerHealth = new GLabel(""+player.getHealth(),SCREEN_WIDTH - 100,SCREEN_HEIGHT - 100);
        playerHealth.setColor(Color.WHITE);
        this.add(playerHealth);
        
        defeatedEnemyCount = new GLabel("Boss spawns in: "+(THRESHOLD-enemiesDefeated),50,SCREEN_HEIGHT-100);
		defeatedEnemyCount.setColor(Color.WHITE);
		this.add(defeatedEnemyCount);
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
                collisionManager.friendlyEntities.add(e); //add to array in collisionManager
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
                if (e.getTypeHint() == 1) {
                		enemiesDefeated++;
                }
            }
        }
        entitiesToRemove.clear();
        
        for (Entity e: entities) {
            e.update(delayMS);
        }
        collisionManager.handleCollisions();
        
        //System.out.println(time);
        //System.out.println(enemiesDefeated);
        time++;
        
        if ((time%50 == 0 && enemiesDefeated < THRESHOLD) || (bossSpawned == true && boss.getHealth() < (boss.HEALTH/2) && time%100 == 0)) {
            SpawningRules.chooseRandomSpawningRule();
        } 
        
        if (enemiesDefeated >= THRESHOLD && bossSpawned == false) {
        		SpawningRules.spawnBoss();
        		bossSpawned = true;
        }
        
        main.trackPlayerHealth();
        main.trackEnemiesDefeated();
        
        if (player.getHealth()<=0) {
        		gameOver = new GLabel("Game Over!", SCREEN_WIDTH - 200, SCREEN_HEIGHT - 200);
        		gameOver.setColor(Color.WHITE);
        		this.add(gameOver);
        }
        
        if (bossSpawned == true && boss.bossAlive() == false) {
        		victory = new GLabel("YOU WIN!!", SCREEN_WIDTH/2, SCREEN_HEIGHT/2);
        		victory.setColor(Color.WHITE);
        		this.add(victory);
        		this.remove(playerHealth);
        		this.remove(defeatedEnemyCount);
        		player.setHealth(100); //makes sure you won't die after victory
        }
        

    }
	
	public void run() {
		this.getGCanvas().requestFocus();
	}
	
	public void trackPlayerHealth() {
		playerHealth.setLabel(""+player.getHealth());
	}
	
	public void trackEnemiesDefeated() {
		defeatedEnemyCount.setLabel("Boss spawns in: "+(THRESHOLD-enemiesDefeated));
		if (enemiesDefeated > THRESHOLD) {
			this.remove(defeatedEnemyCount);
		}
	}
	
}
