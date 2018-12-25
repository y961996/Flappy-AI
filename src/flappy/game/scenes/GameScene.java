package flappy.game.scenes;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

import flappy.game.Flappy;
import flappy.game.events.Event;
import flappy.game.events.EventDispatcher;
import flappy.game.events.eventTypes.MouseMovedEvent;
import flappy.game.events.eventTypes.MousePressedEvent;
import flappy.game.events.eventTypes.MouseReleasedEvent;

public class GameScene extends Scene{

	public GameScene(Flappy flappy, SceneController sceneController) {
		super(flappy, sceneController);
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
		
	}

	@Override
	public void render(Graphics g) {
		
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
