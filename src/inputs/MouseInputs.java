package inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.event.MouseInputListener;

import gamestates.Gamestate;
import main.GamePanel;

public class MouseInputs implements MouseInputListener, MouseMotionListener{
	GamePanel gamePanel;
	
	public MouseInputs(GamePanel gamePanel){
		this.gamePanel = gamePanel;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		switch (Gamestate.state) {
		case HOME:
			gamePanel.getGame().getHome().mouseClicked(e);
			break;
		case MENU:
			gamePanel.getGame().getMenu().mouseClicked(e);
			break;
		case PLAYING:
			gamePanel.getGame().getPlaying().mouseClicked(e);
			break;
		case SETTLE:
			gamePanel.getGame().getSettle().mouseClicked(e);
			break;
		default:
			break;

		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		switch (Gamestate.state) {
		case HOME:
			gamePanel.getGame().getHome().mousePressed(e);
			break;
//		case MENU:
//			gamePanel.getGame().getMenu().mousePressed(e);
//			break;
		case PLAYING:
			gamePanel.getGame().getPlaying().mousePressed(e);
			break;
		case SETTLE:
			gamePanel.getGame().getSettle().mousePressed(e);
			break;
		default:
			break;

		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		switch (Gamestate.state) {
		case HOME:
			gamePanel.getGame().getHome().mouseReleased(e);
			break;
//		case MENU:
//			gamePanel.getGame().getMenu().mouseReleased(e);
//			break;
		case PLAYING:
			gamePanel.getGame().getPlaying().mouseReleased(e);
			break;
		case SETTLE:
			gamePanel.getGame().getSettle().mouseReleased(e);
			break;
		default:
			break;

		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		switch (Gamestate.state) {
		case HOME:
			gamePanel.getGame().getHome().mouseMoved(e);
			break;
//		case MENU:
//			gamePanel.getGame().getMenu().mouseMoved(e);
//			break;
		case PLAYING:
			gamePanel.getGame().getPlaying().mouseMoved(e);
			break;
		case SETTLE:
			gamePanel.getGame().getSettle().mouseMoved(e);
			break;
		default:
			break;
		
		}
	}
}
