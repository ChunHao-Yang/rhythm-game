package ui;

import static utilz.HelpMethods.getL;

import java.awt.image.BufferedImage;

import gamestates.Gamestate;
import utilz.LoadSave;

public class PauseButton extends Button{
	public int currIndex = index, nextIndex = (index + 1) % 2;
	
	public PauseButton(int xPos, int yPos, Gamestate state) {
		super(xPos, yPos, state);
		
		loadImgs();
		initBounds();
	}

	@Override
	public void loadImgs() {
		BufferedImage tmp = LoadSave.GetSpriteAtlas(LoadSave.PAUSE_BUTTON);
		imgs = new BufferedImage[2];
		
		B_WIDTH = getL(tmp.getWidth()/2);
		B_HEIGHT = getL(tmp.getHeight());
		xOffsetCenter = getL(B_WIDTH / 2);
		imgs[0] = tmp.getSubimage(0 * tmp.getWidth() / 2, 0, tmp.getWidth() / 2, tmp.getHeight());
		imgs[1] = tmp.getSubimage(1 * (tmp.getWidth() / 2), 0, tmp.getWidth() / 2, tmp.getHeight());
	}

	@Override
	public void update() {
		index = currIndex;
		System.out.println(currIndex + "  " + nextIndex);
		
		if (mousePressed) {
			int temp = currIndex;
			currIndex = nextIndex;
			nextIndex = temp;
		}
		
			
	}

}
