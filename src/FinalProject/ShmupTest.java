package FinalProject;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.Timer;

import acm.program.GraphicsProgram;

public class ShmupTest extends GraphicsProgram {

	KeyListener keyListener;
	ArrayList<Entity> projectiles = new ArrayList<Entity>();
	Timer timer;
	int delay = 3;
	PlayerPlane player;

	public void init() {
		this.setSize(600, 600);
		this.setBackground(Color.BLACK);
		player = new PlayerPlane(new Rectangle(600, 600));
		player.setLocation(300 - player.getWidth() / 2, 600 - player.getHeight());
		player.setDelay(2);
		this.add(player);
		this.addKeyListeners(player.getKeyListener());
	}

	public void run() {
		
		this.getGCanvas().requestFocus();
	}
}
