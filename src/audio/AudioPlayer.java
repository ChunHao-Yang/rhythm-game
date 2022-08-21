package audio;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioPlayer {
	public static int MUSIC_1 = 0;
	public static int MUSIC_2 = 1;
	
	public static int SOUND_1 = 0;
	public static int SOUND_2 = 1;
	
	private Clip[] songs, effects;
	private int currentSongId = 0;
	private boolean songMute, effectMute;
	private float songVolume = 0.5f;
	private float soundVolume = 0.5f;
	
	String[] names = {"home", "rhyMusic"};
	String[] effectNames = {"sound", "keyPress"};
	
	public AudioPlayer(){
		loadSongs();
		loadEffects();
		playSong(MUSIC_1);
		loop();
	}
	
	private void loadSongs() {
		
		songs = new Clip[names.length];
		for (int i = 0; i < songs.length; i++)
			songs[i] = getClip(names[i]);
	}

	private void loadEffects() {
		
		effects = new Clip[effectNames.length];
		for (int i = 0; i < effects.length; i++)
			effects[i] = getClip(effectNames[i]);

		updateEffectsVolume();

	}

	private Clip getClip(String name) {
		URL url = getClass().getResource("/audio/" + name + ".wav");
		AudioInputStream audio;

		try {
			audio = AudioSystem.getAudioInputStream(url);
			Clip c = AudioSystem.getClip();
			c.open(audio);
			return c;

		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {

			e.printStackTrace();
		}

		return null;

	}

	public void playEffect(int effect) {
		
		getClip(effectNames[effect]).start();
	}

	public void playSong(int song) {
		stopSong();

		currentSongId = song;
		updateSongsVolume();
		songs[currentSongId].setMicrosecondPosition(0);
		songs[currentSongId].start();
	}
	
	public void restart() {
		if (songs[currentSongId].isActive())
			songs[currentSongId].setMicrosecondPosition(0);
	}
	
	@SuppressWarnings("static-access")
	public void loop() {
			songs[0].loop(songs[currentSongId].LOOP_CONTINUOUSLY);
	}

	public void stopSong() {
		if (songs[currentSongId].isOpen()) {
			songs[currentSongId].stop();
		}
	}
	
	public void toggleSongMute() {
		this.songMute = !songMute;
		for (Clip c : songs) {
			BooleanControl booleanControl = (BooleanControl) c.getControl(BooleanControl.Type.MUTE);
			booleanControl.setValue(songMute);
		}
	}

	public void toggleEffectMute() {
		this.effectMute = !effectMute;
		for (Clip effect : effects) {
			BooleanControl booleanControl = (BooleanControl) effect.getControl(BooleanControl.Type.MUTE);
			booleanControl.setValue(effectMute);
		}
	}
	private void updateSongsVolume() {
		for (Clip song : songs) {
			FloatControl floatControl = (FloatControl) song.getControl(FloatControl.Type.MASTER_GAIN);
			float gain = (float) (Math.log(songVolume) / Math.log(10.0) * 20.0);
			floatControl.setValue(gain);
		}

	}

	private void updateEffectsVolume() {
		for (Clip c : effects) {
			FloatControl floatControl = (FloatControl) c.getControl(FloatControl.Type.MASTER_GAIN);
			float gain = (float) (Math.log(soundVolume) / Math.log(10.0) * 20.0);
			floatControl.setValue(gain);
		}
	}
	
	public float getSongVolume() {
		return songVolume;
	}
	
	public float getSoundVolume() {
		return soundVolume;
	}

	
	public void setSongVolume(float volume) {
		songVolume = volume;
		updateSongsVolume();
	}
	
	public void setSoundVolume(float volume) {
		soundVolume = volume;
		updateEffectsVolume();
	}
}
