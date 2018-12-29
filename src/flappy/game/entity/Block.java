package flappy.game.entity;

import java.awt.Graphics;

import flappy.game.utils.StaticResourceLoader;

public class Block extends Entity{
	
	private boolean isTop;
	
	public Block(float x, float y, int width, int height, boolean top) {
		super(x, y, width, height);
		isTop = top;
	}

	@Override
	public void update() {
		collisionBox.x = (int)x;
		collisionBox.y = (int)y;
		x -= 2.5f;
	}

	@Override
	public void render(Graphics g) {
		if(isTop) g.drawImage(StaticResourceLoader.topBlockImage, (int)x, (int)y, width, height, null);
		else g.drawImage(StaticResourceLoader.bottomBlockImage, (int)x, (int)y, width, height, null);
	}

}
