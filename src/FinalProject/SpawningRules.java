package FinalProject;

import java.awt.Rectangle;
import java.util.Random;

public class SpawningRules {
	static double x;
	static double y;
	static double dx;
	static double dy;
	static double health = 10;
	static int numEnemies;
	static Random random;
	static BasicEnemy basicEnemies;

	public static void spawnBasicEnemies() {
	random = new Random();
	numEnemies = random.nextInt(6);
	
	x = random.nextInt(Main.SCREEN_WIDTH);
	y = random.nextInt(Main.SCREEN_HEIGHT);
	dx = Math.random();
	dy = Math.random();
	 for(int i = 0; i < numEnemies; i++) {
		basicEnemies = new BasicEnemy(new Rectangle (50,50), "basicEnemy.png", x, y, dx,dy, health);
		 	Main.main.addEntity(basicEnemies);  
	 	}
	}
}
