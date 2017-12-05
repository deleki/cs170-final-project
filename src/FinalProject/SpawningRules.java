package FinalProject;

import java.awt.Rectangle;
import java.util.Random;

public class SpawningRules {
	static Random random = new Random();
	
	// spawns basic enemies in a line
	public static void spawnEnemiesInLine() {
        random = new Random();
        int numEnemies = random.nextInt(3)+3; //spawns at least 1

        double x = random.nextInt(Main.SCREEN_WIDTH);
        double y = -random.nextInt(300);
        double dx = Math.random()*50+50;
        double dy = Math.random()*50+50;
        
        int health = 10;
        for (int i = 0; i < numEnemies; i++) {
            new BasicEnemy("basicEnemy.png", x-i*dx, y-i*dy, health);
        }
    }
	
	public static void spawnBoss() {
		int health = 1500;
		Main.main.boss = new Boss("boss.png", Main.SCREEN_WIDTH/2, -50, health);
	}
	
	public static void chooseRandomSpawningRule() {
        int ID = random.nextInt(5);
        switch(ID) {
        case 0: case 1:
            spawnRandomWave();
            break;
        case 2: case 3:
            spawnEnemiesInLine();
            break;
        case 4:
            spawnLongBoys();
            break;
        }
    }
    
    public static void spawnLongBoys() {

        double x = random.nextInt(Main.SCREEN_WIDTH-240)+180;
        double y = -random.nextInt(300);
        
        int width = random.nextInt(2)+1;
        int height = random.nextInt(3)+4;
        int dy = 60;
        int dx = 60+random.nextInt(30);
        
        int health = 5;
        for (int ix = 0; ix < width; ix++) {
            for(int iy = 0; iy < height; iy++) {
                new BasicEnemy("basicEnemy.png", x-ix*dx, y-iy*dy, health);
            }
        }
        
    }
    
    public static void spawnRandomWave() {
        int numEnemies = random.nextInt(3) + 3;
        
        int health = 10;
        for (int i = 0; i < numEnemies; i++) {
            double x = random.nextInt(Main.SCREEN_WIDTH);
            double y = -random.nextInt(300);
            new BasicEnemy("basicEnemy.png", x, y, health);
        }
    }
}
