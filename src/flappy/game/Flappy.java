package flappy.game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JFrame;

import flappy.game.events.Event;
import flappy.game.events.EventListener;
import flappy.game.input.KeyboardInput;
import flappy.game.input.MouseInput;
import flappy.game.scenes.GameScene;
import flappy.game.scenes.MenuScene;
import flappy.game.scenes.SceneController;
import flappy.game.utils.SoundUtils;

public class Flappy extends Canvas implements Runnable, EventListener{
	private static final long serialVersionUID = 1L;

	public static Rectangle mouseRectangle =  new Rectangle(MouseInput.getX(), MouseInput.getY(), 1, 1);
	public static final boolean DEBUG = false;
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 640;
	public static final String TITLE = "Flappy AI";
	
	private boolean running = false;
	private int menuSceneIndex;
	private int gameSceneIndex;
	
	private Thread thread;
	private JFrame frame;
	private KeyboardInput keyboardInput;
	private MouseInput mouseInput;
	private SceneController sceneController;
	private MenuScene menuScene;
	private GameScene gameScene;
	private SoundUtils soundUtils;
	
	public Flappy() {}
	
	private void update() {
		keyboardInput.update();
		sceneController.update();
		mouseRectangle.x = MouseInput.getX();
		mouseRectangle.y = MouseInput.getY();
	}
	
	private void render() {
		BufferStrategy bs = getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		// Clear the screen
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		// Draw the game
		sceneController.render(g);
		
		g.dispose();
		bs.show();
	}
	
	@Override
	public void onEvent(Event event) {
		sceneController.getCurrentScene().onEvent(event);
	}
	
	@Override
	public void run() {
		init();
		
		requestFocus();
		
		long lastTime = System.nanoTime();
		double amountOfUpdates = 60.0;
		double ns = 1000000000 / amountOfUpdates;
		double delta = 0;
		long timer = System.currentTimeMillis();
		@SuppressWarnings("unused")
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				update();
				delta--;
				frames++;
			}
			render();
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				if(DEBUG) System.out.println("FPS : " + frames);
				frames = 0;
			}
		}
		stop();
	}
	
	public void start() {
		if(running) {
			return;
		}
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	private void stop() {
		if(!running) {
			return;
		}
		
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void init() {
		initWindow();
		
		sceneController = new SceneController();
		
		keyboardInput = new KeyboardInput();
		mouseInput = new MouseInput(this);
		
		menuScene = new MenuScene(this, sceneController);
		menuSceneIndex = sceneController.getNumberOfScenes();
		sceneController.addScene(menuScene);
		gameScene = new GameScene(this, sceneController, keyboardInput);
		gameSceneIndex = sceneController.getNumberOfScenes();
		sceneController.addScene(gameScene);
		
		sceneController.setScene(gameSceneIndex);
		
		addKeyListener(keyboardInput);
		addMouseListener(mouseInput);
		addMouseMotionListener(mouseInput);
		
		soundUtils = new SoundUtils();
		//soundUtils.startMusic("res/sounds/sound.wav");
		//soundUtils.setVolume(0.5f);
	}
	
	private void initWindow() {
		initCursor();
		
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/flappyIcon.png")));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JFrame.setDefaultLookAndFeelDecorated(true);
		frame.add(this);
		frame.pack();
		frame.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
	private void initCursor() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image cursor = toolkit.getImage("res/images/cursor.png");
		Point cursorHotSpot = new Point(0, 0);
		Cursor customCursor = toolkit.createCustomCursor(cursor, cursorHotSpot, "cursor");
		
		frame = new JFrame(TITLE);
		
		if(cursor != null) {
			frame.setCursor(customCursor);
		}
	}
	
	public static String getCurrentDateAndTime() {
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		return dateFormat.format(now);
	}
	
	public int getMenuSceneIndex() {
		return menuSceneIndex;
	}
	
	public int getGameSceneIndex() {
		return gameSceneIndex;
	}
	
	public void setScene(int index) {
		this.sceneController.setScene(index);
	}

	public SoundUtils getMenuSoundUtils() {
		return this.soundUtils;
	}
}
