package main;


import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import ui.MenuBar;
import utilz.LoadSave;

public class GameWindow {
	private JFrame jframe;
	private BufferedImage homePageLogo = LoadSave.GetSpriteAtlas(LoadSave.Logo);
	
	public GameWindow(GamePanel gamePanel, MenuBar menuBar) {
		jframe = new JFrame();

		jframe.setIconImage(homePageLogo);
		jframe.setTitle("Rhythm");
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setJMenuBar(menuBar);
		jframe.add(gamePanel);
		jframe.pack();
		jframe.setResizable(false);
		jframe.setLocationRelativeTo(null);
		jframe.setVisible(true);
		
		
	}
}
