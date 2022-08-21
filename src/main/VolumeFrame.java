package main;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import audio.AudioPlayer;
import utilz.LoadSave;
import static utilz.HelpMethods.getL;

public class VolumeFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	BufferedImage homePageLogo;
	JPanel panel;
	JLabel musicLabel;
	JLabel soundLabel;
	JSlider musicSlider;
	JSlider soundSlider;
	
	AudioPlayer ap;
	Game game;
	
	
	public VolumeFrame(Game game) {
		this.game = game;
		initClasses();
		
		loadImg();
		setUI();
		setSetting();
		setPaint();
    	
	}

	private void loadImg() {
		homePageLogo = LoadSave.GetSpriteAtlas(LoadSave.Logo);
	}

	private void setSetting() {
		musicSlider.setPreferredSize(new Dimension(getL(300), getL(100)));
    	musicSlider.setBounds(getL(100),getL(100),getL(300),getL(100));
    	musicSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				game.getAudioPlayer().setSongVolume((float)((float)musicSlider.getValue() / 100));
			}
		});
    	musicSlider.setBackground(Color.white);
    	
    	soundSlider.setPreferredSize(new Dimension(getL(300), getL(100)));
    	soundSlider.setBounds(getL(100),getL(300),getL(300),getL(80));
    	soundSlider.addChangeListener(new ChangeListener() {
    		@Override
			public void stateChanged(ChangeEvent e) {
    			game.getAudioPlayer().setSoundVolume((float)((float)soundSlider.getValue() / 100));
			}
		});
    	soundSlider.setBackground(Color.white);
    	
    	musicLabel.setFont(new Font("Arial",Font.PLAIN,getL(20)));
    	musicLabel.setBounds(getL(100), getL(50), getL(100), getL(50));
    	musicLabel.setText("music: ");
    	
    	soundLabel.setFont(new Font("Arial",Font.PLAIN,getL(20)));
    	soundLabel.setBounds(getL(100),getL(250),getL(100), getL(50));
    	soundLabel.setText("sound: ");
    	
    	panel.add(musicSlider);
    	panel.add(musicLabel);
    	panel.add(soundLabel);
    	panel.add(soundSlider);
    	panel.setBackground(Color.white);
    	panel.setBounds(0,0,getL(500), getL(500));
    	panel.setLayout(new BorderLayout());
		
	}

	private void setPaint() {
		
		this.add(panel);
    	this.getContentPane().setBackground(Color.white);
    	this.setIconImage(homePageLogo);
    	this.setTitle("Volume");
    	this.setLayout(new BorderLayout());
    	this.setSize(getL(500), getL(500));
    	this.setResizable(false);
    	this.setLocationRelativeTo(null);
    	this.setVisible(true);
    	
	}

	private void setUI() {
		musicSlider.setUI(new  javax.swing.plaf.metal.MetalSliderUI(){
    		
			@Override
	    	public void paintThumb(Graphics g) {
	    		Graphics2D g2d = (Graphics2D) g;
	    		g2d.setColor(Color.white);
	    		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    		g2d.fillOval(getL(thumbRect.x), getL(thumbRect.y), getL(thumbRect.width),getL(thumbRect.height));

	    	}
	    	public void paintTrack(Graphics g) {
	    		int cy,cw;
	    		Rectangle trackBounds = trackRect;

    			Graphics2D g2D = (Graphics2D) g;
    			g2D.setPaint(Color.lightGray);
    			cy = (trackBounds.height/2) - 2;
    			cw = trackBounds.width;
    			
    			g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
    			g2D.translate(getL(trackBounds.x),getL(trackBounds.y + cy));
    			g2D.fillRect(0, -getL(cy + 5), getL(cw), getL(cy));
    			
    			int trackLeft = 0;
    			int trackRight = 0;
    			trackRight = trackRect.width - 1;

    			int middleOfThumb = 0;
    			int fillLeft = 0;
    			int fillRight = 0;

    			middleOfThumb = thumbRect.x + (thumbRect.width / 2);
    			middleOfThumb -= trackRect.x;
    			
    			if (!drawInverted()) {
    				fillLeft = !slider.isEnabled() ? trackLeft : trackLeft + 1;
    				fillRight = middleOfThumb;
    				} else {
    				fillLeft = middleOfThumb;
    				fillRight = !slider.isEnabled() ? trackRight - 1: trackRight - 2;
    			}

    			g2D.setPaint(new GradientPaint(0, 0, Color.red, cw, 0,Color.white, true));
    			g2D.fillRect(0, -getL(cy + 5), getL(fillRight - fillLeft), getL(cy));
    					
    			g2D.translate(-getL(trackBounds.x), -getL(trackBounds.y + cy));   					
	    	}
	
	    });
    	soundSlider.setUI(new  javax.swing.plaf.metal.MetalSliderUI(){
    		
	    	@Override
	    	public void paintThumb(Graphics g) {
	    		Graphics2D g2d = (Graphics2D) g;
	    		g2d.setColor(Color.white);
	    		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    		g2d.fillOval(getL(thumbRect.x), getL(thumbRect.y), getL(thumbRect.width),getL(thumbRect.height));

	    	}
	    	public void paintTrack(Graphics g) {
	    		int cy,cw;
	    		Rectangle trackBounds = trackRect;

    			Graphics2D g2D = (Graphics2D) g;
    			g2D.setPaint(Color.lightGray);
    			cy = (trackBounds.height/2) - 2;
    			cw = trackBounds.width;
    			
    			g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
    			g2D.translate(getL(trackBounds.x),getL(trackBounds.y + cy));
    			g2D.fillRect(0, -getL(cy + 5), getL(cw), getL(cy));
    			
    			int trackLeft = 0;
    			int trackRight = 0;
    			trackRight = trackRect.width - 1;

    			int middleOfThumb = 0;
    			int fillLeft = 0;
    			int fillRight = 0;

    			middleOfThumb = thumbRect.x + (thumbRect.width / 2);
    			middleOfThumb -= trackRect.x;
    			
    			if (!drawInverted()) {
    				fillLeft = !slider.isEnabled() ? trackLeft : trackLeft + 1;
    				fillRight = middleOfThumb;
    				} else {
    				fillLeft = middleOfThumb;
    				fillRight = !slider.isEnabled() ? trackRight - 1: trackRight - 2;
    			}

    			g2D.setPaint(new GradientPaint(0, 0, Color.red, cw, 0,Color.white, true));
    			g2D.fillRect(0, -getL(cy + 5), getL(fillRight - fillLeft), getL(cy));
    					
    			g2D.translate(-getL(trackBounds.x), -getL(trackBounds.y + cy));   					
	    	}
	
	    });
		
	}

	private void initClasses() {
		ap = game.getAudioPlayer();
		panel = new JPanel();
		musicLabel = new JLabel();
		soundLabel = new JLabel();
    	musicSlider = new JSlider(0, 100, (int)(game.getAudioPlayer().getSongVolume() * 100));
    	soundSlider = new JSlider(0, 100, (int)(game.getAudioPlayer().getSoundVolume() * 100));
		
	}
}
