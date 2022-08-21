package threads;

import java.util.ArrayList;
import java.util.Arrays;

import audio.AudioPlayer;
import gamestates.Gamestate;
import main.Game;
import notes.Note;
import notes.Pipoint;

public class RhyGameThread extends Thread{
	Game game;
	
	private int i = 0;
	private long start;
	public boolean isPlay = true;
	ArrayList<Note> list = new ArrayList<Note>();
	ArrayList<Note> parallel = new ArrayList<Note>();
	private int score = 0;
	private String[] pointLvlStrs = {"Bad", "Good", "Great", "Perfect"};
	private int[] pointLvl = {0, 0, 0, 0}; 
	private int[] getPoint = {0, 4, 7, 10};
	private boolean[] isLvlPressed = {false, false, false, false};
	
	public RhyGameThread(Game game) {
		this.game = game;
	}
	
	@Override
	public void run() {
		isPlay = true;
		game.getAudioPlayer().playSong(AudioPlayer.MUSIC_2);
		start = System.nanoTime();
		
		long lastFrame = System.nanoTime();
		long now;
		while(i < Pipoint.sleep_nano.length && isPlay) {
			now = System.nanoTime();
			
			if(now - lastFrame >= Pipoint.sleep_nano[i]) {
				
				for(int j = 0; j < 4 && isPlay; j++) {
					if(Pipoint.beats[i][j] == 1) {
						Note note = new Note(j, game);
						parallel.add(note);
						list.add(note);
					}
				}
				for(Note n: parallel) {
					n.start();
				}
				parallel.clear();
				
				lastFrame = now;
				i++;
			}
		}
		
		if(isPlay) {
			try {
				Thread.sleep(16000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if(isPlay != false) {
				Gamestate.state = Gamestate.SETTLE;
				game.getAudioPlayer().stopSong();
				game.getAudioPlayer().playSong(AudioPlayer.MUSIC_1);
			}
		}
		
	}
	
	public void stopThread() {
		isPlay = false;
	}
	
	public void setIsPlay(boolean b) {
		isPlay = b;
	}
	
	public ArrayList<Note> getList(){
		return list;
	}
	
	public void judge(int input) {

		for(int i = 0; i < list.size();i++) {
			Note note = list.get(i);
			if(note.getchannel() == input) {
				note.judge();
				break;
			}
		}
	}
	public int getScore() {
		return score;
	}
	public void plusPointLvl(int i) {
		pointLvl[i]++;
	}
	public void plusScore(int s) {
		score += s;
	}


	public int getGetPoint(int lvl) {
		return getPoint[lvl];
	}
	public int getLvlPoint(int lvl) {
		return pointLvl[lvl];
	}
	
	public void resetBool() {
		for(int i = 0; i < 4; i++) {
			isLvlPressed[i] = false;
		}
	}
	public void setBool(int index, boolean b) {
		isLvlPressed[index] = b;
	}
	public boolean getIsLvlPressed(int i) {
		return isLvlPressed[i];
	}
	public String getPointLvlStrs(int i) {
		return pointLvlStrs[i];
	}
	public void getFirstBeat() {
		System.out.println(System.nanoTime() - start);
	}

	
	
}
