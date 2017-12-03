package FinalProject;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

import acm.program.GraphicsProgram;

public class Main extends GraphicsProgram{
	
	ArrayList<Entity> entities = new ArrayList<Entity>();
	ArrayList<Entity> entitiesToAdd = new ArrayList<Entity>();
	ArrayList<Entity> entitiesToRemove = new ArrayList<Entity>();
	
	public static Main main;
	double delayMS = 1/60.0;
	private Timer timer;
	Player player;
	public static final int SCREEN_WIDTH = 600;
	public static final int SCREEN_HEIGHT = 600;
	
	SpawningRules spawn;
	int time = 0;
	
	
	public void init() {
		main = this;
		this.setSize(SCREEN_WIDTH,SCREEN_HEIGHT);
		this.setBackground(Color.BLACK);
		player = new Player(300,600);
		timer = new Timer((int)(delayMS*1000), new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onTick();
			}
		});
		timer.start();	
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
		}
		entitiesToAdd.clear();
		
		for (Entity e : entitiesToRemove) {
			entities.remove(e);
			this.remove(e);
		}
		entitiesToRemove.clear();
		
		for (Entity e: entities) {
			e.update(delayMS);
		}
		
		//System.out.println(time);
		time++;
		
		if (time%50 == 0) {
			SpawningRules.spawnBasicEnemies();
		}
		
		if (time > 1000000) {
			time = 0;
		}
	}
	
	public void run() {
		this.getGCanvas().requestFocus();
	}
}
