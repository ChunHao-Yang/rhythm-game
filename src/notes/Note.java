package notes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


import main.Game;
import threads.RhyGameThread;

import static utilz.HelpMethods.getL;
import utilz.LoadSave;

public class Note extends Thread{
	public final static float NOTE_SPEED = 1f * Game.SCALE;
	private final int UPS_SET = 800;
	
	public final int[] CHxPos = {getL(158), getL(403), getL(643), getL(888)};
	
	private BufferedImage blockImg;
	private float xPos, yPos = -getL(30);
	private int NOTE_WIDTH, NOTE_HEIGHT;
	public boolean proceed = true;
	
	private int channel;
	private Game game;
	
	public Note(int channel, Game game) {
		loadBlockImg();
		xPos = CHxPos[channel];
		
		this.channel = channel;
		this.game = game;

	}

	private void loadBlockImg() {
		blockImg = LoadSave.GetSpriteAtlas(LoadSave.BLOCK);
		
		NOTE_WIDTH = getL(blockImg.getWidth());
		NOTE_HEIGHT = getL(blockImg.getHeight());
	}

	public void draw(Graphics2D g2DD) {
		if(inRange())
			g2DD.drawImage(blockImg, (int)xPos, (int)yPos, NOTE_WIDTH, NOTE_HEIGHT, null);
	}
	
	@Override
	public void run() {
		double timePerUpdate = 1000000000.0 / UPS_SET;
		long previousTime = System.nanoTime();
		double deltaU = 0;
		
		while(inRange()) {
			long currentTime = System.nanoTime();
			
			deltaU += (currentTime - previousTime) / timePerUpdate;
			previousTime = currentTime;
			
			if(deltaU >= 1) {
				updatePos();
				deltaU--;
			}
			
			if((int)yPos >= getL(700)) {
				if(proceed) {
					game.rhyGame.plusPointLvl(0);
					game.rhyGame.resetBool();
					game.rhyGame.setBool(0, true);
				}
				close();
			}
			
		}	
	}

	public void updatePos(){
		yPos += NOTE_SPEED;
	}
	
	
	public int getchannel() {
		return channel;
	}
	
	private void close() {
		proceed = false;
	}
	
	private boolean inRange() {
		if((int)yPos >= -getL(30) && (int)yPos <= getL(750)) return true;
		return false;
	}
	


	public void judge() {
		int lvl = -1;
	
		if(((int)yPos >= getL(521) && (int)yPos <= getL(556)) || ((int)yPos >= getL(595) && (int)yPos <= getL(631))) {
			lvl = 1;
		}
		else if(((int)yPos >= getL(557) && (int)yPos <= getL(572)) || ((int)yPos >= getL(579) && (int)yPos <= getL(594))) {
			lvl = 2;
		}
		else if((int)yPos >= getL(573) && (int)yPos <= getL(578)) {
			lvl = 3;
		}
		else if((int)yPos >= getL(632)){
			lvl = 0;
		}
		
		if(lvl != -1) {
			game.rhyGame.plusPointLvl(lvl);
			game.rhyGame.resetBool();
			game.rhyGame.setBool(lvl, true);
			close();
			game.rhyGame.plusScore(game.rhyGame.getGetPoint(lvl));
		}
	}
}
