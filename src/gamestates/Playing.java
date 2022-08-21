package gamestates;

import static utilz.Constants.WindowDimension.GAME_HEIGHT;
import static utilz.Constants.WindowDimension.GAME_WIDTH;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

import audio.AudioPlayer;
import main.Game;
import notes.Note;
import ui.BackButton;
import ui.Button;
import ui.PauseButton;

import static utilz.HelpMethods.getL;
import utilz.LoadSave;

public class Playing extends State implements StateMethods{
	private BufferedImage backgroundImg;
	private BufferedImage lightupImg;
	
	private int lightWidth, lightHeight;
	
	public String[] CHs = {"C", "V", "N", "M"};
	public boolean[] isCHpressed = {false, false, false, false};
	ArrayList<Note> list = game.rhyGame.getList();
	public int[] CHXpos = {253, 498, 740, 985};
	private int lightupOffset = -769;
	private Button button;
	
	public Playing(Game game) {
		super(game);
		
		loadButtons();
		loadImg();
	}
	
	private void loadImg() {
		backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.PLAYING_BACKGROUND);
		lightupImg = LoadSave.GetSpriteAtlas(LoadSave.LIGHT_CH);
		
		lightWidth = getL(lightupImg.getWidth());
		lightHeight = getL(lightupImg.getHeight());
		
	}
	
	private void loadButtons() {
		button = new BackButton(getL(42), getL(42), Gamestate.HOME);
	}

	@Override
	public void update() {
		list = game.rhyGame.getList();
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(backgroundImg, 0, 0, GAME_WIDTH, GAME_HEIGHT, null);
		button.draw(g);
		
		g.setColor(Color.pink);
		g.setFont(new Font("Arial", Font.BOLD,getL(40)));
		for(int i = 0; i < 4; i++) {
			g.drawString(CHs[i], getL(CHXpos[i]), getL(614));
			if(isCHpressed[i]) g.drawImage(lightupImg, getL(lightupOffset + CHXpos[i]), 0, lightWidth, lightHeight, null);
		}
		
		g.drawString(String.format("%05d", game.rhyGame.getScore()), getL(1150), getL(50));
		
		
		for(int i = 0; i < 4; i++) {
			if(game.rhyGame.getIsLvlPressed(i)) {
				g.drawString(game.rhyGame.getPointLvlStrs(i), getL(1150), getL(100));
				break;
			}
		}
		
		for(int i = 0; i<list.size();i++) {
			Note note = list.get(i);
			if(!note.proceed) {
				list.remove(i);
				i--;
			}
			else {
				note.draw(g);
			}
		}
		
		
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (isIn(e, button)) {
			button.setMousePressed(true);
			game.getAudioPlayer().playEffect(AudioPlayer.SOUND_1);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (isIn(e, button)) {
			if (button.isMousePressed())
				button.applyGamestate();
			if (button.getState() == Gamestate.HOME)
				game.getAudioPlayer().playSong(AudioPlayer.MUSIC_1);
				game.rhyGame.stopThread();
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
	}

	@SuppressWarnings("static-access")
	@Override
	public void keyPressed(KeyEvent e) {
		String input = e.getKeyText(e.getKeyCode()).toUpperCase();
		
		for(int i = 0; i < 4; i++) {
			if(input.equals(CHs[i])) {
				game.getAudioPlayer().playEffect(AudioPlayer.SOUND_2);
				isCHpressed[i] = true;
				game.rhyGame.judge(i);
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			game.rhyGame.getFirstBeat();
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		for(int i = 0; i < 4; i++) {
			isCHpressed[i] = false;
		}
		
	}
	
	public void setChannels(String[] s) {
		CHs = s;
	}
	public String[] getCHs() {
		return CHs;
	}
	public String getCHs(int i) {
		return CHs[i];
	}
	public void setCHs(String[] s) {
		for(int i = 0; i < 4; i++) {
			CHs[i] = s[i];
		}
	}
	

}
