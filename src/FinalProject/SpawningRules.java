package FinalProject;

import java.awt.Rectangle;
import java.util.Random;

public class SpawningRules {
	static Random random = new Random();
	
	// spawns basic enemies in a line
	public static void spawnEnemiesInLine() {
        random = new Random();
        int numEnemies = random.nextInt(6);

        double x = random.nextInt(Main.SCREEN_WIDTH);
        double y = -random.nextInt(300);
        double dx = Math.random()*50+50;
        double dy = Math.random()*50+50;
        
        double health = 10;
        for (int i = 0; i < numEnemies; i++) {
            new BasicEnemy(new Rectangle(50, 50), "basicEnemy.png", x-i*dx, y-i*dy, health);
        }
    }
	
	
}
