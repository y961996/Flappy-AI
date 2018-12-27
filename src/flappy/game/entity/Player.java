package flappy.game.entity;

import java.awt.Color;
import java.awt.Graphics;

import flappy.game.input.KeyboardInput;

public class Player extends Entity{

	private float gravity = 1f;
	private KeyboardInput keyboard;
	private boolean canBounce = true;
	private boolean isBouncing = false;
	
	public Player(KeyboardInput keyboard, float x, float y, int width, int height) {
		super(x, y, width, height);
		this.keyboard = keyboard;
	}

	@Override
	public void update() {
		collisionBox.x = (int)x;
		collisionBox.y = (int)y;
		
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
			gravity += 0.1f;
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect((int)x, (int)y, width, height);
	}

	public void bounce() {
		canBounce = false;
		isBouncing = true;
		this.gravity = 0.1f;
		this.y -= 65;
	}
}
