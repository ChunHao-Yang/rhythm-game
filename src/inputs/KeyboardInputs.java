package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import gamestates.Gamestate;
import main.GamePanel;

public class KeyboardInputs implements KeyListener{

	GamePanel gamePanel;
	
	public KeyboardInputs(GamePanel gamePanel){
		this.gamePanel = gamePanel;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		switch (Gamestate.state) {
		case HOME:
			gamePanel.getGame().getHome().keyPressed(e);
			break;
		case MENU:
			gamePanel.getGame().getMenu().keyPressed(e);
			break;
		case PLAYING:
			gamePanel.getGame().getPlaying().keyPressed(e);
			break;
		case SETTLE:
			gamePanel.getGame().getSettle().keyPressed(e);
			break;
		default:
			break;

		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		switch (Gamestate.state) {
		case HOME:
			gamePanel.getGame().getHome().keyReleased(e);
			break;
		case MENU:
			gamePanel.getGame().getMenu().keyReleased(e);
			break;
		case PLAYING:
			gamePanel.getGame().getPlaying().keyReleased(e);
			break;
		case SETTLE:
			gamePanel.getGame().getSettle().keyReleased(e);
			break;
		default:
			break;

		}
	}

}
