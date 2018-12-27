package flappy.game.entity;

import java.awt.Color;
import java.awt.Graphics;

public class Block extends Entity{
	
	public Block(float x, float y, int width, int height) {
		super(x, y, width, height);
	}

	@Override
	public void update() {
		collisionBox.x = (int)x;
		collisionBox.y = (int)y;
		x-=2.5f;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.GREEN.brighter());
		g.fillRect((int)x, (int)y, width, height);
	}

}
