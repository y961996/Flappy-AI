package flappy.game.entity;

import java.awt.Graphics;

import flappy.game.Flappy;
import flappy.game.input.KeyboardInput;
import flappy.game.scenes.GameScene;
import flappy.game.utils.Animation;
import flappy.game.utils.StaticResourceLoader;

public class Player extends Entity{

	private float gravity = 1f;
	private KeyboardInput keyboard;
	private GameScene gameScene;
	private Animation birdAnimation;
	private boolean canBounce = true;
	private boolean isBouncing = false;
	
	public Player(GameScene gameScene, KeyboardInput keyboard, float x, float y, int width, int height) {
		super(x, y, width, height);
		this.gameScene = gameScene;
		this.keyboard = keyboard;
		
		birdAnimation = new Animation();
		birdAnimation.setFrames(StaticResourceLoader.birdsImages);
		birdAnimation.setDelayBetweenFrames(150);
	}

	@Override
	public void update() {
		birdAnimation.update();
		
		if(y < 0 || y > Flappy.HEIGHT) {
			gameScene.setGameOver(true);
		}
		
		y += gravity;
		if(keyboard.space) {
			if(canBounce) {
				bounce();
			}
		}
		if(!keyboard.space) {
			if(isBouncing) {
				isBouncing = false;
				canBounce = true;
			}
		}
		if(gravity < 9.8f) {
			gravity += 0.2f;
		}
		
		collisionBox.x = (int)x;
		collisionBox.y = (int)y;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(birdAnimation.getCurrentImage(), (int)x, (int)y, width, height, null);
	}

	public void bounce() {
		canBounce = false;
		isBouncing = true;
		this.gravity = 0.1f;
		this.y -= 65;
	}
}
