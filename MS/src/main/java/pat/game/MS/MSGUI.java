package pat.game.MS;

import java.awt.event.*;

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

		/* dialog input */
		final JTextField tFRows = new JTextField();
		final JTextField tFColumns = new JTextField();
		final JTextField tFMines = new JTextField();
		panel = new JPanel();

		final JComponent[] inputs = new JComponent[] { new JLabel("Columns"),
				tFColumns, new JLabel("Rows"), tFRows, new JLabel("Mines"),
				tFMines };

		newGameMI.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, inputs, "New Game",
						JOptionPane.PLAIN_MESSAGE);

				newGame(Integer.parseInt(tFColumns.getText()),
						Integer.parseInt(tFRows.getText()),
						Integer.parseInt(tFMines.getText()));

			}
		});

		menu.add(newGameMI);
		menu.add(retryMI);
		mb.add(menu);
		frame.setJMenuBar(mb);
		newGame(30, 20, 20);

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
