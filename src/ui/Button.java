package ui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import gamestates.Gamestate;
import utilz.HelpMethods;

public abstract class Button {
	protected int xPos, yPos, rowIndex;
	public int index;
	protected int B_WIDTH, B_HEIGHT;
	protected int xOffsetCenter;
	protected Gamestate state;
	protected BufferedImage[] imgs;
	protected boolean mouseOver, mousePressed;
	protected Rectangle bounds;
	
	public abstract void loadImgs();
	public abstract void update();
	
	public Button(int xPos, int yPos, Gamestate state) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.state = state;
		
	}
	
	protected void initBounds() {
		bounds = new Rectangle(xPos - xOffsetCenter, yPos, B_WIDTH, B_HEIGHT);

	}

	public void draw(Graphics2D g) {
		g.drawImage(imgs[index], xPos - xOffsetCenter, yPos, B_WIDTH, B_HEIGHT, null);
	}

	public boolean isMouseOver() {
		return mouseOver;
	}

	public void setMouseOver(boolean mouseOver) {
		this.mouseOver = mouseOver;
	}

	public boolean isMousePressed() {
		return mousePressed;
	}

	public void setMousePressed(boolean mousePressed) {
		this.mousePressed = mousePressed;
	}

	public Rectangle getBounds() {
		return bounds;
	}
 
	public void applyGamestate() {
		Gamestate.state = state;
	}

	public void resetBools() {
		mouseOver = false;
		mousePressed = false;
	}
	public Gamestate getState() {
		return state;
	}

}
