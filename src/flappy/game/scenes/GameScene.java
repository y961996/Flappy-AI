package flappy.game.scenes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import flappy.game.Flappy;
import flappy.game.entity.Block;
import flappy.game.entity.EntityController;
import flappy.game.entity.Player;
import flappy.game.events.Event;
import flappy.game.events.EventDispatcher;
import flappy.game.events.eventTypes.MouseMovedEvent;
import flappy.game.events.eventTypes.MousePressedEvent;
import flappy.game.events.eventTypes.MouseReleasedEvent;
import flappy.game.input.KeyboardInput;
import flappy.game.utils.StaticResourceLoader;

public class GameScene extends Scene{

	public static final int MAX_BLOCK = 6;
	public static final int BLOCK_SPACING = 125;
	public static boolean createBlock = false;
	public static int blockCount;
	
	private EntityController entityController;
	private Random random;
	private Player player;
	private KeyboardInput keyboard;
	private float score;
	private boolean gameOver;
	private int groundX;
	
	public GameScene(Flappy flappy, SceneController sceneController, KeyboardInput keyboard) {
		super(flappy, sceneController);
		random = new Random();
		this.keyboard = keyboard;
		player = new Player(this, keyboard, 100, 100, 32, 32);
		entityController = new EntityController(player, this);
		score = 0;
		gameOver = false;
		
		addBlocks();
	}

	@Override
	public void onEvent(Event event) {
		EventDispatcher dispatcher = new EventDispatcher(event);
		dispatcher.dispatch(Event.Type.MOUSE_PRESSED, (Event e) -> onMousePressed((MousePressedEvent) e));
		dispatcher.dispatch(Event.Type.MOUSE_RELEASED, (Event e) -> onMouseReleased((MouseReleasedEvent) e));
		dispatcher.dispatch(Event.Type.MOUSE_MOVED, (Event e) -> onMouseMoved((MouseMovedEvent) e));
	}

	@Override
	public void update() {
		if(!gameOver) {
			player.update();
			entityController.update();
			if(createBlock) {
				createBlock();
			}
			score += 0.01;
		}else {
			String textToWrite = "[" + Flappy.getCurrentDateAndTime() + "] " + "Game finished with score: " + (int)score + "(" + score + ")";
			writePointToFile(textToWrite);
			restart(keyboard);
		}
		
		groundX = (groundX - 1) % Flappy.WIDTH;
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(StaticResourceLoader.gameSceneBackground, 0, 0, Flappy.WIDTH, Flappy.HEIGHT, null);
		
		entityController.render(g);
		player.render(g);
		
		g.setFont(new Font("Verdana", Font.BOLD, 24));
		g.setColor(Color.BLACK);
		g.drawString((int)score+"", 1200, 50);
		
		g.drawImage(StaticResourceLoader.ground, groundX, Flappy.HEIGHT - StaticResourceLoader.ground.getHeight() / 2, Flappy.WIDTH, StaticResourceLoader.ground.getHeight(), null);
		g.drawImage(StaticResourceLoader.ground, groundX + Flappy.WIDTH, Flappy.HEIGHT - StaticResourceLoader.ground.getHeight() / 2, Flappy.WIDTH, StaticResourceLoader.ground.getHeight(), null);
	}

	public void addBlocks() {
		Block block;
		int height;
		
		for(int i = 0; i < MAX_BLOCK; i++) {
			height = random.nextInt(400) + 100;
			
			// Ceiling blocks
			block = new Block(400 + i * 250, 0, 75, height, true);
			entityController.addEntity(block);
			// Floor block
			block = new Block(400 + i * 250, height + BLOCK_SPACING, 75, Flappy.HEIGHT - height - BLOCK_SPACING, false);
			entityController.addEntity(block);
			
			blockCount++;
		}
	}
	
	public void createBlock() {
		Block block;
		int height = random.nextInt(400) + 100;
		float x = entityController.getLastBlockX();
		
		// Ceiling blocks
		block = new Block(x, 0, 75, height, true);
		entityController.addEntity(block);
		
		// Floor block
		block = new Block(x, height + BLOCK_SPACING, 75, Flappy.HEIGHT - height - BLOCK_SPACING, false);
		entityController.addEntity(block);
		
		blockCount++;
		createBlock = false;
	}
	
	private void restart(KeyboardInput keyboard) {
		player = new Player(this, keyboard, 100, 100, 32, 32);
		entityController = new EntityController(player, this);
		score = 0;
		gameOver = false;
		
		addBlocks();
	}
	
	private void writePointToFile(String text) {
		File file = new File("res/points.txt");
		
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			FileWriter writer = new FileWriter(file.getAbsolutePath(), true);
			BufferedWriter bw = new BufferedWriter(writer);
			
			bw.write(text + "\r\n");
			
			bw.close();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public boolean onMousePressed(MousePressedEvent e) {
		return false;
	}
	
	public boolean onMouseReleased(MouseReleasedEvent e) {
		return false;
	}
	
	public boolean onMouseMoved(MouseMovedEvent e) {
		return false;
	}
	
	public void incrementScore() {
		score++;
	}
	
	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

}
