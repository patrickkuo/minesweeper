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

	private BufferedImage img;
	private BufferedImage img1;

	public ImageButton(BufferedImage img) {
		this.img = img;
		this.img1 = null;
	}

	public ImageButton(BufferedImage img, BufferedImage img1) {
		this.img = img;
		this.img1 = img1;
	}

	public ImageButton() {
		this.img = null;
	}

	public void setImg(BufferedImage img) {
		this.img = img;
		this.img1 = null;
	}

	public void setImg(BufferedImage img, BufferedImage img1) {
		this.img = img;
		this.img1 = img1;
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);

		if (img != null) {

				if (img1 != null) {
					g.drawImage(img1, 2, 2, null);
				}
				
				//BufferedImage image = ImageIO.read(new File(img));
				g.drawImage(img, 2, 2, null);

		}

	}

	public void doMouseEvent(MouseEvent e) {
		processMouseEvent(e);

	}
}
