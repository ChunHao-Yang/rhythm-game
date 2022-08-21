package gamestates;

import static utilz.Constants.WindowDimension.GAME_HEIGHT;
import static utilz.Constants.WindowDimension.GAME_WIDTH;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import audio.AudioPlayer;
import main.Game;
import static utilz.HelpMethods.getL;
import ui.Button;
import ui.QuitButton1;
import ui.StartButton;
import utilz.LoadSave;

public class Home extends State implements StateMethods{

	private BufferedImage backgroundImg;
	private BufferedImage nameImg;
	int nameWidth, nameHeight;
	private Button[] buttons = new Button[2];
	
	public Home(Game game) {
		super(game);
		
		loadButtons();
		loadBackground();
	}

	private void loadBackground() {
		backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.HOME_BACKGROUND);
		nameImg = LoadSave.GetSpriteAtlas(LoadSave.NAME);
		
		nameWidth = getL(nameImg.getWidth());
		nameHeight = getL(nameImg.getHeight());
		
	}
	
	private void loadButtons() {
		buttons[0] = new StartButton(getL(200), getL(250), Gamestate.PLAYING);
		buttons[1] = new QuitButton1(getL(200), getL(400), Gamestate.QUIT);
	}

	@Override
	public void update() {
		for (Button mb : buttons)
			mb.update();
		
	}

	@Override
	public void draw(Graphics2D g) {
		
		g.drawImage(backgroundImg, 0, 0, GAME_WIDTH, GAME_HEIGHT,null);
		g.drawImage(nameImg, 50, 50, nameWidth, nameHeight, null);
		
		for (Button mb : buttons)
			mb.draw(g);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		for (Button mb : buttons) {
			if (isIn(e, mb)) {
				mb.setMousePressed(true);
				game.getAudioPlayer().playEffect(AudioPlayer.SOUND_1);
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		for (Button mb : buttons) {
			if (isIn(e, mb)) {
				if (mb.isMousePressed())
					mb.applyGamestate();
				
				if (mb.getState() == Gamestate.PLAYING)
					game.startRhyGame();
				break;
			}
		}

		resetButtons();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		for (Button mb : buttons)
			mb.setMouseOver(false);

		for (Button mb : buttons)
			if (isIn(e, mb)) {
				mb.setMouseOver(true);
				break;
			}

	}

	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	
	private void resetButtons() {
		for (Button mb : buttons)
			mb.resetBools();

	}

}
