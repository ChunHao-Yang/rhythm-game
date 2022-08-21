package threads;


import gamestates.Gamestate;
import main.Game;

public class GameThread extends Thread{
	Game game;
	private final int FPS_SET = 480;
	private final int UPS_SET = 480;
	
	public GameThread(Game game) {
		this.game = game;
	}
	
	public void update() {
		switch (Gamestate.state) {
			case HOME:
				game.getHome().update();
				break;
			case MENU:
				game.getMenu().update();
				break;
			case PLAYING:
				game.getPlaying().update();
				break;
			case SETTLE:
				game.getSettle().update();
				break;
			case QUIT:
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.exit(0);
				break;
			default:
				break;

		}
	}
	
	
	@Override
	public void run() {
		double timePerFrame = 1000000000.0 / FPS_SET;
		double timePerUpdate = 1000000000.0 / UPS_SET;
		long previousTime = System.nanoTime();
		
		int frames = 0;
		int updates = 0;
		long lastCheck = 0;
		
		double deltaU = 0;
		double deltaF = 0;
		
		while(true) {

			long currentTime = System.nanoTime();
			
			deltaU += (currentTime - previousTime) / timePerUpdate;
			deltaF += (currentTime - previousTime) / timePerFrame;
			previousTime = currentTime;
			
			if(deltaU >= 1) {
				update();
				updates++;
				deltaU--;
			}
			
			if(deltaF >= 1) {
				game.gamePanel.repaint();
				frames++;
				deltaF--;
			}
			
			
//			if(System.currentTimeMillis() - lastCheck >= 1000) {
//				lastCheck = System.currentTimeMillis();
//				System.out.println("FPS: " + frames + " | UPS: " + updates);
//				frames = 0;
//				updates = 0;
//			}
		}	
	}
}
