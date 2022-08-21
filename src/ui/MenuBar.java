package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import main.ControlFrame;
import main.Game;
import main.VolumeFrame;

import static utilz.HelpMethods.getL;

public class MenuBar extends JMenuBar{
	private static final long serialVersionUID = 1L;
	
	Game game;
	
	JMenu settingMenu;
	JMenuItem volumeItem;
	JMenuItem controlItem;
	Font f = new Font("Arial", Font.BOLD, getL(15));

	public MenuBar(Game game) {
		this.game = game;
		
		initClasses();
		
		setItems();
		setMenu();
		setMenuBar();
	}

	private void setMenuBar() {
		this.add(settingMenu);
		this.setPreferredSize(new Dimension(getL(1280), getL(25)));
		this.setBackground(Color.LIGHT_GRAY);
		this.setBorderPainted(false);
	}

	private void setMenu() {
		settingMenu.setForeground(Color.black);
		settingMenu.add(volumeItem);
		settingMenu.add(controlItem);
		settingMenu.setFont(f);
	}

	private void setItems() {
		volumeItem.addActionListener(new ActionListener() {
		      @Override
		      public void actionPerformed(ActionEvent e) {
		      	if(e.getSource() == volumeItem) {
		      		new VolumeFrame(game);
		      	}
		      }
		  });
		volumeItem.setFont(f);
		controlItem.addActionListener(new ActionListener() {
		      @Override
		      public void actionPerformed(ActionEvent e) {
		      	if(e.getSource() == controlItem) {
		      		new ControlFrame(game);
		      	}
		      }
		  });
		controlItem.setFont(f);
	}

	private void initClasses() {
		settingMenu = new JMenu("Setting");
		volumeItem = new JMenuItem("Volume");
		controlItem = new JMenuItem("Control");
	}

}
