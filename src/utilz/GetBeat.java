package utilz;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

import audio.AudioPlayer;
import static utilz.HelpMethods.getL;
public class GetBeat implements KeyListener
{

	static long startTime = System.nanoTime();
	static long now = System.nanoTime();
	ArrayList<Long> a = new ArrayList<Long>();
	@Override
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			now = System.nanoTime();
			a.add(now - startTime);
			startTime = now;
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			System.out.println(a);
		}
		
	}
	public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException
	{
		AudioPlayer ap = new AudioPlayer();
		ap.playSong(AudioPlayer.MUSIC_2);
		JFrame f = new JFrame("Demo");
		
		f.setSize(getL(500), getL(500));
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.setFocusable(true);
		GetBeat k = new GetBeat();
		f.addKeyListener(k);
	}
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		
	}
}
