package flappy.game.utils;

import java.awt.image.BufferedImage;

public class StaticResourceLoader {

	public StaticResourceLoader() {}
	
	private static ImageUtils imageUtils = new ImageUtils();
	
	public static BufferedImage birdsImageSpriteSheet;
	public static BufferedImage[] birdsImages;
	
	public static BufferedImage topBlockImage;
	public static BufferedImage bottomBlockImage;
	
	public static BufferedImage gameSceneBackground;
	
	public static BufferedImage ground;
	
	static {
		birdsImageSpriteSheet = imageUtils.loadImage("/images/birds.png");
		birdsImages = new BufferedImage[3];
		for(int i = 0; i < birdsImages.length; i++) {
			birdsImages[i] = birdsImageSpriteSheet.getSubimage(i * 32, 0, 32, 32);
		}
		
		topBlockImage = imageUtils.loadImage("/images/topBlock.png");
		bottomBlockImage = imageUtils.loadImage("/images/bottomBlock.png");
		
		gameSceneBackground = imageUtils.loadImage("/images/background.png");
		
		ground = imageUtils.loadImage("/images/ground.png");
	}
}
