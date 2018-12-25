package flappy.game.scenes;

import java.awt.Graphics;

import flappy.game.Flappy;
import flappy.game.events.EventListener;

public abstract class Scene implements EventListener{

	public SceneController sceneController;
	public Flappy flappy;
	
	public Scene(Flappy flappy, SceneController sceneController) {
		this.flappy = flappy;
		this.sceneController = sceneController;
	}
	
	public abstract void update();
	public abstract void render(Graphics g);
}
