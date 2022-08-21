package utilz;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;


public class LoadSave {
	public static final String HOME_BACKGROUND = "homebackground"; 	//	http://wallpaperswide.com/cool_rest-wallpapers.html
	public static final String PLAYING_BACKGROUND = "gamepicture";
	public static final String SETTLE_BACKGROUND = "settlepage";
	public static final String Logo = "HomePageLogo";				//	https://www.freepik.com/vectors/piano-icon'>Piano icon vector created by rawpixel.com - www.freepik.com
	public static final String NAME = "Name";
	public static final String START_BUTTON_ENTER = "startenter";
	public static final String START_BUTTON_FOCUS = "startfocus";
	public static final String QUIT_BUTTON1_ENTER = "exitenter";
	public static final String QUIT_BUTTON1_FOCUS = "exitfocus";
	public static final String QUIT_BUTTON2 = "quit";
	public static final String BACK_BUTTON = "back";
	public static final String RESTART_BUTTON = "restart";
	public static final String PAUSE_BUTTON = "pause";
	public static final String HOME_BUTTON = "home";
	public static final String BLOCK = "block";
	public static final String LIGHT_CH = "lightupC";
	public static final String A = "A";
	public static final String B = "B";
	public static final String C = "C";
	public static final String S = "S";

	

	
	public static BufferedImage GetSpriteAtlas(String filename) {
		BufferedImage img = null;
		InputStream is = LoadSave.class.getResourceAsStream("/pic/" + filename + ".png");
		try {
			img = ImageIO.read(is);
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return img;
	}
	
}