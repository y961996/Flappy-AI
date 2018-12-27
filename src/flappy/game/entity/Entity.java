package flappy.game.entity;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Entity {

	protected float x;
	protected float y;
	protected int width;
	protected int height;
	protected boolean removable;
	protected Rectangle collisionBox;
	
	public abstract void update();
	public abstract void render(Graphics g);
	// Maybe add checkCollision method as an abstraction to the entity class
	
	public Entity(float x, float y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.collisionBox = new Rectangle((int)x, (int)y, width, height);
		this.removable = false;
	}
	public boolean isRemovable() {
		return removable;
	}
	public void setRemovable(boolean removable) {
		this.removable = removable;
	}
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public Rectangle getCollisionBox() {
		return collisionBox;
	}
	public void setCollisionBox(Rectangle collisionBox) {
		if(this.collisionBox == null || collisionBox == null) return;
		this.collisionBox = collisionBox;
	}
}
