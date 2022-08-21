package main;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import utilz.Constants;
import utilz.LoadSave;

import static utilz.HelpMethods.getL;

public class ControlFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	private BufferedImage homePageLogo;
	private JButton applyButton;
	private JButton defaultButton;
	
	private JTextField[] textFields;
	private JLabel[] labels;
	private String[] CHs;
	private int state = 0;
	
	private Game game;
	
	public ControlFrame(Game game) {
		this.CHs = game.getPlaying().getCHs();
		this.game = game;
		
		initClasses();
		setButtons();
		setSetting();
		setPaint();
		
	}
	
	private void setPaint() {
		for(int i = 0; i < 4; i++) {
			this.add(textFields[i]);
			this.add(labels[i]);
		}
		this.add(applyButton);
		this.add(defaultButton);
		
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setLayout(null);
		this.getContentPane().setBackground(Color.white);
		this.setIconImage(homePageLogo);
		this.setTitle("Control");
		this.setLayout(new BorderLayout());
		this.setSize(getL(500), getL(500));
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
		        if(hasChange()) exitFrame();
		        else exitControl();
		     }
		});
		
	}

	private void setSetting() {
		for(int i = 0; i < 4; i++) {
			textFields[i].setBounds(getL(285),getL(100 + i * 50),getL(30),getL(30));
			textFields[i].setText(game.getPlaying().getCHs(i));
			labels[i].setBounds(getL(185),getL(100 + i * 50),getL(100),getL(30));
		}
		
	}

	private void setButtons() {
		applyButton.setBounds(getL(390),getL(400),getL(80),getL(30));
		applyButton.setFocusPainted(false);
		defaultButton.setBounds(getL(300),getL(400),getL(80),getL(30));
		
		applyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				apply();
			}
		});
		
		defaultButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i < 4; i++) {
					textFields[i].setText(Constants.Control.CONTROL[i]);
				}
			}
		});
		
	}

	private void initClasses() {
		homePageLogo = LoadSave.GetSpriteAtlas(LoadSave.Logo);
		textFields = new JTextField[4];
		labels = new JLabel[4];
		applyButton = new JButton("Apply");
		defaultButton = new JButton("Default");
		
		for(int i = 0; i < 4; i++) {
			textFields[i] = new JTextField();
			labels[i] = new JLabel("channel" + (i + 1));
		}
		
	}

	private boolean checkFormat(String[] s) {
		
		for(int i = 0; i < 4; i++) {
			if(s[i].length() != 1) return false;
			
			char sChar = s[i].charAt(0);
			if (sChar < 'A' && sChar > 'Z') return false;
		}
		return true;
	}
	private boolean checkSame(String[] s) {
		Set<String> set = new HashSet<String>();
		for (String element : s) {
		      set.add(element);
		   }
	   if (set.size() < 4) { 
		   System.out.println(set.size());
		   return true;
	   }
	   else return false;
	}
	private boolean hasChange() {
		for(int i = 0; i < 4; i++) {
			if(!textFields[i].getText().equalsIgnoreCase(CHs[i])) return true;
		}

		return false;
	}
	private void exitFrame() {
		Object[] options = {"No", "Yes"};
	    JOptionPane pane = new JOptionPane("Some values have changed. Do you want to apply it?", JOptionPane.QUESTION_MESSAGE,
	        JOptionPane.YES_NO_OPTION, null, options, options[1]);
	    JDialog dialog = pane.createDialog(this, "Warining!");
	    dialog.setVisible(true);
	    Object selectedValue = pane.getValue();
	    if (selectedValue == options[1]) {
	      apply();
	      if(state == 1) {
	    	  this.dispose();
	      }
	    } 
	    else if (selectedValue == options[0]) {
	    	this.dispose();
	    }
	    else if(selectedValue == null){
	    	this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	    }
	}
	private void apply() {
		state = 1;
		String[] newStrings = new String[4];
		for(int i = 0; i < 4; i++) {
				newStrings[i] = textFields[i].getText().toUpperCase();;
		}
		if(!checkFormat(newStrings)) {
			JOptionPane.showMessageDialog(null, "Please enter keys in alpha", "Warning", JOptionPane.WARNING_MESSAGE);
			state = 0;
		}
		
		if(checkSame(newStrings)) {
			JOptionPane.showMessageDialog(null, "Duplicate keys!", "Warning", JOptionPane.WARNING_MESSAGE);
			state = 0;
		}
		if(state == 1) {
			game.getPlaying().setCHs(newStrings);
			this.CHs = game.getPlaying().getCHs();
		}
	}
	
	private void exitControl() {
		this.dispose();
	}
}
