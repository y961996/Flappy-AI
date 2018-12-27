package flappy.game.scenes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.Random;

import flappy.game.Flappy;
import flappy.game.entity.Block;
import flappy.game.entity.EntityController;
import flappy.game.events.Event;
import flappy.game.events.EventDispatcher;
import flappy.game.events.eventTypes.MouseMovedEvent;
import flappy.game.events.eventTypes.MousePressedEvent;
import flappy.game.events.eventTypes.MouseReleasedEvent;

public class GameScene extends Scene{

	public static final int MAX_BLOCK = 6;
	public static boolean createBlock = false;
	
	public EntityController entityController;
	private Random random;
	public static int blockCount;
	
	public GameScene(Flappy flappy, SceneController sceneController) {
		super(flappy, sceneController);
		entityController = new EntityController();
		random = new Random();
		
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
		entityController.update();
		if(createBlock) {
			createBlock();
		}
	}

	public void addBlocks() {
		Block block;
		int height;
		
		for(int i = 0; i < MAX_BLOCK; i++) {
			height = random.nextInt(300) + 200;
			
			// Ceiling blocks
			block = new Block(400 + i * 250, 0, 75, height);
			entityController.addEntity(block);
			// Floor block
			block = new Block(400 + i * 250, height + 75, 75, Flappy.HEIGHT - height - 75);
			entityController.addEntity(block);
			
			blockCount++;
		}
	}
	
	public void createBlock() {
		Block block;
		int height = random.nextInt(300) + 200;
		float x = entityController.getLastBlockX();
		
		// Ceiling blocks
		block = new Block(x, 0, 75, height);
		entityController.addEntity(block);
		
		// Floor block
		block = new Block(x, height + 75, 75, Flappy.HEIGHT - height - 75);
		entityController.addEntity(block);
		
		blockCount++;
		createBlock = false;
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(Color.CYAN);
		g.fillRect(0, 0, Flappy.WIDTH, Flappy.HEIGHT);
		
		entityController.render(g);
	}
	
	public boolean onMousePressed(MousePressedEvent e) {
		return false;
	}
	
	public boolean onMouseReleased(MouseReleasedEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) {
			return true;
		}
		return false;
	}
	
	public boolean onMouseMoved(MouseMovedEvent e) {
		return false;
	}

}
