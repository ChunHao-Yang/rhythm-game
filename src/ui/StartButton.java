package ui;

import java.awt.image.BufferedImage;

import gamestates.Gamestate;
import static utilz.HelpMethods.getL;
import utilz.LoadSave;

public class StartButton extends Button{

	public StartButton(int xPos, int yPos, Gamestate state) {
		super(xPos, yPos, state);
		
		loadImgs();
		initBounds();
	}

	@Override
	public void loadImgs() {
		imgs = new BufferedImage[2];
		imgs[0] = LoadSave.GetSpriteAtlas(LoadSave.START_BUTTON_ENTER);
		imgs[1] = LoadSave.GetSpriteAtlas(LoadSave.START_BUTTON_FOCUS);
		
		B_WIDTH = getL(imgs[0].getWidth());
		B_HEIGHT = getL(imgs[0].getHeight());
		xOffsetCenter = getL(B_WIDTH / 2);
	}

	@Override
	public void update() {
		index = 0;
		
		if (mouseOver || mousePressed)
			index = 1;

	}
	
}
