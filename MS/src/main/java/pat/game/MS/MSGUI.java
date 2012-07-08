package pat.game.MS;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class MSGUI {
	private Game game;
	private JFrame frame;
	private JPanel panel;

	public MSGUI() {
		frame = new JFrame("Mine Sweeper");
		frame.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});

		JMenuBar mb = new JMenuBar();
		JMenu menu = new JMenu("Options");
		JMenuItem newGameMI = new JMenuItem("New Game");
		JMenuItem retryMI = new JMenuItem("Retry");

		retryMI.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				game.retry();
			}
		});
		panel = new JPanel();

		/* dialog input */

		final JSpinner spinnerY = new JSpinner(new SpinnerNumberModel(20, 10,
				100, 1));
		final JSpinner spinnerX = new JSpinner(new SpinnerNumberModel(30, 10,
				100, 1));
		final JSpinner spinnerMines = new JSpinner(new SpinnerNumberModel(50,
				1, 999, 1));

		final JPanel inputs = new JPanel();

		inputs.add(new JLabel("Columns: "));
		inputs.add(spinnerX);
		inputs.add(new JLabel("Rows: "));
		inputs.add(spinnerY);
		inputs.add(new JLabel("Mines: "));
		inputs.add(spinnerMines);

		newGameMI.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, inputs, "New Game",
						JOptionPane.PLAIN_MESSAGE);

				newGame((Integer) (spinnerX.getValue()),
						(Integer) (spinnerY.getValue()),
						(Integer) (spinnerMines.getValue()));

			}
		});

		menu.add(newGameMI);
		menu.add(retryMI);
		mb.add(menu);
		frame.setJMenuBar(mb);
		newGame(30, 20, 50);

		// Get the size of the screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

		// Determine the new location of the window
		int w = frame.getSize().width;
		int h = frame.getSize().height;
		int x = (dim.width - w) / 2;
		int y = (dim.height - h) / 2;

		// Move the window
		frame.setLocation(x, y);
		BufferedImage image;

		try {
			image = ImageIO.read(new File(ImageButton.MINE_IMAGE));
			frame.setIconImage(image);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		frame.setVisible(true);
		frame.setResizable(false);

	}

	private void newGame(final int x, final int y, final int bomb) {
		frame.remove(panel);
		panel = new JPanel();
		game = new Game(x, y, bomb, panel);
		frame.add(panel);
		frame.setSize(panel.getSize());
		frame.repaint();
	}

}
