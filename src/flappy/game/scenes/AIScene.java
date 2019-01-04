package flappy.game.scenes;

import java.awt.Graphics;

import flappy.game.FlappyAI;
import flappy.game.events.EventListener;

public abstract class AIScene implements EventListener{

	public AISceneController sceneController;
	public FlappyAI flappyAI;
	
	public AIScene(FlappyAI flappyAI, AISceneController sceneController) {
		this.flappyAI = flappyAI;
		this.sceneController = sceneController;
	}
	
	public abstract void update();
	public abstract void render(Graphics g);
}
