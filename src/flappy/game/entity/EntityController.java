package flappy.game.entity;

import java.awt.Graphics;
import java.util.ArrayList;

public class EntityController {

	private ArrayList<Entity> entities;
	
	public EntityController() {
		entities = new ArrayList<Entity>();
	}
	
	public void addEntity(Entity entity) {
		entities.add(entity);
	}
	
	public void removeEntity() {
		for(int i = entities.size() - 1; i >= 0; i--) {
			if(entities.get(i) != null) {
				if(entities.get(i).removable) {
					entities.remove(i);
				}
			}
		}
	}
	
	public void update() {
		removeEntity();
		for(int i = 0; i < entities.size(); i++) {
			Entity tempEntity = entities.get(i);
			if(tempEntity != null) tempEntity.update();
		}
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			if(e != null) {
				e.render(g);
			}
		}
	}
}
