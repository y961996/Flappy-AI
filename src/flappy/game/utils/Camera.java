package flappy.game.utils;

public class Camera {
	
	private float cameraX;
	private float cameraY;
	private int cameraViewportX;
	private int cameraViewportY;
	
	public Camera(float cameraX, float cameraY, int cameraViewportX, int cameraViewportY) {
		this.cameraX = cameraX;
		this.cameraY = cameraY;
		this.cameraViewportX = cameraViewportX;
		this.cameraViewportY = cameraViewportY;
	}

	public float getCameraX() {
		return cameraX;
	}

	public void setCameraX(float cameraX) {
		this.cameraX = cameraX;
	}

	public float getCameraY() {
		return cameraY;
	}

	public void setCameraY(float cameraY) {
		this.cameraY = cameraY;
	}

	public int getCameraViewportX() {
		return cameraViewportX;
	}

	public void setCameraViewportX(int cameraViewportX) {
		this.cameraViewportX = cameraViewportX;
	}

	public int getCameraViewportY() {
		return cameraViewportY;
	}

	public void setCameraViewportY(int cameraViewportY) {
		this.cameraViewportY = cameraViewportY;
	}
}
