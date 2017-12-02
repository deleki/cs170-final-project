package FinalProject;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

import acm.program.GraphicsProgram;

public class Main extends GraphicsProgram{
	
	ArrayList<Entity> entities = new ArrayList<Entity>();
	
	public static Main main;
	double delayMS = 1/60.0;
	private Timer timer;
	Player player;
	
	public void init() {
		main = this;
		this.setSize(600,600);
		this.setBackground(Color.BLACK);
		player = new Player();
		entities.add(player);
		player.setLocation(300 - player.getWidth() / 2, 600 - player.getHeight());
		this.add(player);
		timer = new Timer((int)(delayMS*1000), new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onTick();
			}
		});
		timer.start();	
	}
	
	public void onTick() {
		for (Entity e: entities) {
			e.update(delayMS);
		}
	}
	
	public void run() {
		this.getGCanvas().requestFocus();
	}
}
