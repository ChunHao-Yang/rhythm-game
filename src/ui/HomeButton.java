package ui;

import static utilz.HelpMethods.getL;

import java.awt.image.BufferedImage;

import gamestates.Gamestate;
import utilz.LoadSave;

public class HomeButton extends Button{
	public HomeButton(int xPos, int yPos, Gamestate state) {
		super(xPos, yPos, state);
		
		loadImgs();
		initBounds();
	}

	@Override
	public void loadImgs() {
		imgs = new BufferedImage[1];
		imgs[0] = LoadSave.GetSpriteAtlas(LoadSave.HOME_BUTTON);
		
		B_WIDTH = getL(imgs[0].getWidth());
		B_HEIGHT = getL(imgs[0].getHeight());
		xOffsetCenter = getL(B_WIDTH / 2);
		
	}

	@Override
	public void update() {
		index = 0;
	}
}
