package pat.game.MS;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ButtonImage {

	public static final String MINE_IMAGE = "src/main/java/img/mine.png";
	public static final String FLAG_IMAGE = "src/main/java/img/flag.png";
	public static final String OOO_IMAGE = "src/main/java/img/ooo.png";
	public static final String WIN_IMAGE = "src/main/java/img/win.png";
	public static final String HAHA_IMAGE = "src/main/java/img/haha.png";
	public static final String DEAD_IMAGE = "src/main/java/img/dead.png";

	private static BufferedImage mine, flag, ooo, win, haha, dead;

	private ButtonImage() {
	}

	public static BufferedImage getImage(ImageEnum image) {

		try {
			switch (image) {

			case MINE_IMAGE:
				if (mine == null)
					mine = ImageIO.read(new File(MINE_IMAGE));
				return mine;

			case FLAG_IMAGE:
				if (flag == null)
					flag = ImageIO.read(new File(FLAG_IMAGE));
				return flag;

			case OOO_IMAGE:
				if (ooo == null)
					ooo = ImageIO.read(new File(OOO_IMAGE));
				return ooo;

			case WIN_IMAGE:
				if (win == null)
					win = ImageIO.read(new File(WIN_IMAGE));
				return win;

			case HAHA_IMAGE:
				if (haha == null)
					haha = ImageIO.read(new File(HAHA_IMAGE));
				return haha;

			case DEAD_IMAGE:
				if (dead == null)
					dead = ImageIO.read(new File(DEAD_IMAGE));
				return dead;
				
			default:return null;

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;

	}

}
