package main;

import static utilz.Constants.WindowDimension.GAME_HEIGHT;
import static utilz.Constants.WindowDimension.GAME_WIDTH;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import gamestates.Gamestate;
import inputs.MouseInputs;
import inputs.KeyboardInputs;

public class GamePanel extends JPanel{
	
	private MouseInputs mouseInputs;
	private Game game;
	private Graphics g;
	
	public GamePanel(Game game) {
		mouseInputs = new MouseInputs(this);
		this.game = game;
		
		setPanelSize();
		addKeyListener(new KeyboardInputs(this));
		addMouseListener(mouseInputs);
		addMouseMotionListener(mouseInputs);
		
	}
	
	private void setPanelSize() {
		Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
		setPreferredSize(size);
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D)g;
		
		switch (Gamestate.state) {
		case HOME:
			game.getHome().draw(g2D);
			break;
		case MENU:
			game.getMenu().draw(g2D);
			break;
		case PLAYING:
			game.getPlaying().draw(g2D);
			break;
		case SETTLE:
			game.getSettle().draw(g2D);
			break;
		default:
			break;

		}
	}
	public Game getGame() {
		return game;
	}
}