package flappy.game.entity;

import java.awt.Graphics;
import java.util.ArrayList;

import flappy.ai.Individual;
import flappy.game.Flappy;
import flappy.game.scenes.AIGameScene;
import flappy.game.scenes.GameScene;

public class AIEntityController {

	private ArrayList<Entity> entities;
	private AIGameScene aiGameScene;
	private Individual[] individuals;
	
	public AIEntityController(Individual[] individuals, AIGameScene aiGameScene) {
		entities = new ArrayList<Entity>();
		this.individuals = individuals;
		this.aiGameScene = aiGameScene;
	}
	
	public void addEntity(Entity entity) {
		entities.add(entity);
	}
	
	public void removeEntity() {
		for(int i = entities.size() - 1; i >= 0; i--) {
			if(entities.get(i) != null) {
				if(entities.get(i).removable) {
					if(entities.get(i) instanceof Block) {
						 GameScene.blockCount--;
						 GameScene.createBlock = true;
					}
					entities.remove(i);
				}
			}
		}
	}
	
	public void update() {
		removeEntity();
		for(int i = 0; i < entities.size(); i++) {
			Entity tempEntity = entities.get(i);
			if(tempEntity.x < Flappy.WIDTH / 2) {
				for(int j = 0; j < individuals.length; j++) {
					if(tempEntity.collisionBox.intersects(individuals[j].collisionBox)) {
						
					}
				}
			}
			if(tempEntity != null) {
				if(tempEntity instanceof Block) {
					if(tempEntity.getX() + tempEntity.getWidth() < 0) {
						tempEntity.removable = true;
					}
				}
				if(!tempEntity.removable) {
					tempEntity.update();	
				}
			}
		}
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			if(e != null && !e.removable) {
				e.render(g);
			}
		}
	}
	
	public float getLastBlockX() {
		for(int i = entities.size() - 1; i >= 0; i--) {
			Entity e = entities.get(i);
			if(e instanceof Block && e.x > Flappy.WIDTH) {
				return e.getX();
			}
			else if(e instanceof Block && e.x + e.width > Flappy.WIDTH - 250) {
				return e.x + 250;
			} 
		}
		return Flappy.WIDTH;
	}
}
