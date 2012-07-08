package pat.game.MS;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JToggleButton;

public class ImageButton extends JToggleButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String MINE_IMAGE = "src/main/java/img/mine.png";
	public static final String FLAG_IMAGE = "src/main/java/img/flag.png";
	public static final String OOO_IMAGE = "src/main/java/img/ooo.png";
	public static final String WIN_IMAGE = "src/main/java/img/win.png";
	public static final String HAHA_IMAGE = "src/main/java/img/haha.png";
	public static final String DEAD_IMAGE = "src/main/java/img/dead.png";

	private String img;
	private String img1;

	public ImageButton(String img) {
		this.img = img;
		this.img1 = null;
	}

	public ImageButton(String img, String img1) {
		this.img = img;
		this.img1 = img1;
	}

	public ImageButton() {
		this.img = null;
	}

	public void setImg(String img) {
		this.img = img;
		this.img1 = null;
	}

	public void setImg(String img, String img1) {
		this.img = img;
		this.img1 = img1;
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);

		if (img != null) {
			try {

				if (img1 != null) {
					BufferedImage image1 = ImageIO.read(new File(img1));
					g.drawImage(image1, 2, 2, null);
				}

				BufferedImage image = ImageIO.read(new File(img));
				g.drawImage(image, 2, 2, null);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void doMouseEvent(MouseEvent e) {
		processMouseEvent(e);

	}
}
