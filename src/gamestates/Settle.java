package gamestates;

import static utilz.Constants.WindowDimension.GAME_HEIGHT;
import static utilz.Constants.WindowDimension.GAME_WIDTH;
import static utilz.HelpMethods.getL;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import audio.AudioPlayer;
import main.Game;
import ui.BackButton;
import ui.Button;
import ui.HomeButton;
import ui.QuitButton1;
import ui.QuitButton2;
import ui.RestartButon;
import ui.StartButton;
import utilz.LoadSave;

public class Settle extends State implements StateMethods{
	private BufferedImage backgroundImg;
	private BufferedImage[] resultLvlImg = new BufferedImage[4];
	private Button[] buttons = new Button[3];
	
	private int resultLvl = -1;
	private int LvlWidth, LvlHeight;
	private int score = game.rhyGame.getScore();
	
	
	public Settle(Game game) {
		super(game);
		
		loadButtons();
		loadImg();
	}
	
	private void loadImg() {
		backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.SETTLE_BACKGROUND);
		
		resultLvlImg[0] = LoadSave.GetSpriteAtlas(LoadSave.A);
		resultLvlImg[1] = LoadSave.GetSpriteAtlas(LoadSave.B);
		resultLvlImg[2] = LoadSave.GetSpriteAtlas(LoadSave.C);
		resultLvlImg[3] = LoadSave.GetSpriteAtlas(LoadSave.S);
		
		LvlWidth = getL(resultLvlImg[0].getWidth());
		LvlHeight = getL(resultLvlImg[0].getHeight());
		
	}
	
	private void loadButtons() {
		buttons[0] = new RestartButon(getL(800), getL(600), Gamestate.PLAYING);
		buttons[1] = new QuitButton2(getL(970), getL(600), Gamestate.QUIT);
		buttons[2] = new HomeButton(getL(30), getL(30), Gamestate.HOME);
	}

	@Override
	public void update() {
		score = game.rhyGame.getScore();
		
		if(score > 3500) resultLvl = 3;
		else if(score >= 2500) resultLvl = 0;
		else if(score >= 1500) resultLvl = 1;
		else resultLvl = 2;
		
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(backgroundImg, 0, 0, GAME_WIDTH, GAME_HEIGHT, null);
		g.drawImage(resultLvlImg[resultLvl], 0, getL(10), LvlWidth, LvlHeight, null);
		
		for (Button mb : buttons)
			mb.draw(g);
		
		g.setColor(Color.black);
		g.setFont(new Font("Arial", Font.BOLD, getL(60)));
		
		g.drawString(String.format("%05d", score), getL(560), getL(200));
		
		for(int i = 0; i < 4; i++) {
			g.drawString(String.format("%d", game.rhyGame.getLvlPoint(i)), getL(490), getL(302 + i*80));
		}
		
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
				if (mb.getState() == Gamestate.HOME)
					game.getAudioPlayer().playSong(AudioPlayer.MUSIC_1);
				break;
			}
		}

		resetButtons();
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
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
	
	private void setResultLvl(int r) {
		resultLvl = r;
	}
	
	private void reset() {
		score = 0;
		resultLvl = 0;
	}
}
