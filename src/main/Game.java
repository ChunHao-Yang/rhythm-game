package main;

import audio.AudioPlayer;
import gamestates.*;
import threads.GameThread;
import threads.RhyGameThread;
import ui.MenuBar;


public class Game{
	public GameWindow gameWindow;
	public GamePanel gamePanel;
	private MenuBar menuBar;
	private GameThread gameThread;
	public RhyGameThread rhyGame;
	public final static float SCALE = 0.75f;
	

	private Home home;
	private Menu menu;
	private Playing playing;
	private Settle settle;
	private AudioPlayer audioPlayer;
	
	
	public Game() {
		initClasses();
		
		gamePanel = new GamePanel(this);
		menuBar = new MenuBar(this);
		gameWindow = new GameWindow(gamePanel, menuBar);
		
		gamePanel.setFocusable(true);
		gamePanel.requestFocus();
		
		startGameLoop();
	}
	private void initClasses() {
		rhyGame = new RhyGameThread(this);
		home = new Home(this);
		menu = new Menu(this);
		playing = new Playing(this);
		settle = new Settle(this);
		audioPlayer = new AudioPlayer();
	}

	private void startGameLoop() {
		gameThread = new GameThread(this);
		gameThread.start();
	}
	
	public void startRhyGame() {
		rhyGame = new RhyGameThread(this);
		rhyGame.start();
	}
	
	public Home getHome() {
		return home;
	}
	public Menu getMenu() {
		return menu;
	}
	public Playing getPlaying() {
		return playing;
	}
	public Settle getSettle() {
		return settle;
	}
	public AudioPlayer getAudioPlayer() {
		return audioPlayer;
	}

	
}
