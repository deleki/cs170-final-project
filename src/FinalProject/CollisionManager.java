package FinalProject;

import java.util.ArrayList;

public class CollisionManager {

	ArrayList<Entity> friendlyEntities = new ArrayList<Entity>();
	ArrayList<Entity> unfriendlyEntities = new ArrayList<Entity>();

	public void handleCollisions() {
		for (Entity e1 : friendlyEntities) {
			for (Entity e2 : unfriendlyEntities) {

				if (e1.hitbox().intersects(e2.hitbox())) {
					if (e1.getTypeHint() == Entity.PLAYER && e2.getTypeHint() == Entity.ENEMY) {
						// enemy does damage to player and enemy explodes, or something.
						
					} else if (e1.getTypeHint() == Entity.PLAYER && e2.getTypeHint() == Entity.PROJECTILE) {
						// the projectile does damage to the player and despawns, calling e2.damage(e1)
						e2.destroy();
						e1.setHealth(e1.getHealth()-1);
					} else if (e1.getTypeHint() == Entity.PROJECTILE && e2.getTypeHint() == Entity.ENEMY) {
						// the projectile does damage to the enemy, calling e1.damage(e2)
						e1.destroy();
						e2.setHealth(e2.getHealth()-1);
					}
				}
			}
		}
	}

}