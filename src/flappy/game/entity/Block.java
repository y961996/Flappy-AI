package flappy.game.entity;

import java.awt.Color;
import java.awt.Graphics;

public class Block extends Entity{
	
	public Block(float x, float y, int width, int height) {
		super(x, y, width, height);
	}

	@Override
	public void update() {
		x--;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.GREEN.brighter());
		g.fillRect((int)x, (int)y, width, height);
	}

}
