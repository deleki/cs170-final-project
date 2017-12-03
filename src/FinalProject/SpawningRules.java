package FinalProject;

import java.awt.Rectangle;
import java.util.Random;

public class SpawningRules {
	double x;
	double y;
	double dx;
	double dy;
	int numEnemies;
	Random random;
	BasicEnemy basicEnemies = new BasicEnemy(new Rectangle (50,50), "basicEnemy.png", Math.random(), Math.random()+Main.SCREEN_HEIGHT
			, Math.random(), Math.random(), 10);

	public void spawnBasicEnemies() {
	random = new Random();
	numEnemies = random.nextInt(6);
	 for(int i = 0; i < numEnemies; i++) {
		 	Main.main.addEntity(basicEnemies);
	 	}
	}
}
