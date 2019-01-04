package flappy.game.scenes;

import java.awt.Graphics;
import java.util.ArrayList;

public class AISceneController {
	
	private ArrayList<AIScene> aiScenes  = new ArrayList<AIScene>();
	private int currentScene = 0;
	
	public void addScene(AIScene scene) {
		aiScenes.add(scene);
	}
	public void setScene(int index) {
		currentScene = index;
	}
	
	public void update() {
		aiScenes.get(currentScene).update();
	}
	
	public void render(Graphics g) {
		aiScenes.get(currentScene).render(g);
	}
	
	public ArrayList<AIScene> getScenesList(){
		return aiScenes;
	}
	
	public int getNumberOfScenes() {
		return aiScenes.size();
	}
	
	public AIScene getCurrentScene() {
		return aiScenes.get(currentScene);
	}
}
