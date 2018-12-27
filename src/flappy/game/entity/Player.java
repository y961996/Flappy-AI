package flappy.game.entity;

import java.awt.Color;
import java.awt.Graphics;

import flappy.game.input.KeyboardInput;

public class Player extends Entity{

	private float gravity = 0.1f;
	private KeyboardInput keyboard;
	
	public Player(KeyboardInput keyboard, float x, float y, int width, int height) {
		super(x, y, width, height);
		this.keyboard = keyboard;
	}

	@Override
	public void update() {
		y += gravity;
		if(keyboard.space) {
			bounce();
		}
		if(gravity < 5.0f) {
			gravity += 0.1f;
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect((int)x, (int)y, width, height);
	}

	public void bounce() {
		this.gravity = 0.1f;
		this.y -= 20;
	}
}
