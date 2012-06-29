package com.fdm.MS;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class MSGUI {

	private Field[][] mGrid;
	private int y, x, numOfBomb;
	private Panel panel;
	private Frame frame;
	private boolean lose;
	private Menu bombCounter;

	public MSGUI() {

		x = 15;
		y = 30;
		numOfBomb = 1;
		
		frame = new Frame("");
		newGame();
		
		frame.setResizable(false);
		frame.setVisible(true);
	}

	public void makeButtons() {

		panel.setLayout(new GridLayout(x, y));
		mGrid = new Field[x][y];
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {

				final JToggleButton bu = new JToggleButton();

				bu.setMargin(new Insets(0, 0, 0, 0));
				bu.setBackground(new Color(235, 235, 235));
				mGrid[i][j] = new Field();
				mGrid[i][j].setBombValue(0);
				mGrid[i][j].setBu(bu);
				final Field field = mGrid[i][j];
				bu.setName("bu#" + i + ";" + j);
				bu.addMouseListener(new MouseListener() {

					public void mouseReleased(MouseEvent e) {
						if (lose)
							e.consume();

						// TODO Auto-generated method stub

					}

					public void mousePressed(MouseEvent e) {
						if (lose)
							e.consume();

						// TODO Auto-generated method stub

					}

					public void mouseExited(MouseEvent e) {
						if (lose)
							e.consume();

						// TODO Auto-generated method stub

					}

					public void mouseEntered(MouseEvent e) {
						if (lose)
							e.consume();

						// TODO Auto-generated method stub

					}

					public void mouseClicked(MouseEvent e) {

						if (!lose) {
							if (e.getButton() == MouseEvent.BUTTON1) {
								if (!bu.getText().equals("F")) {

									if (field.isBomb()) {

										bu.doClick();
										bu.setBackground(Color.RED);

										lose = true;

									} else {

										// bu.setEnabled(false);

										autoClick(bu);

										System.out.println(bu.getName());

									}
								}
							}

							if (e.getButton() == MouseEvent.BUTTON3) {

								if (bu.getText().equals("F")) {
									bu.setText("");
									bombCounter.setLabel(Integer
											.toString(Integer
													.parseInt(bombCounter
															.getLabel()) + 1));
								} else if (bu.getText().isEmpty()) {
									bu.setFont(new Font("Ariel", 2, 13));
									bu.setForeground(Color.RED);
									bu.setText("F");
									bombCounter.setLabel(Integer
											.toString(Integer
													.parseInt(bombCounter
															.getLabel()) - 1));
								} else {
									e.consume();
									bu.setSelected(true);
								}
							}

						} else {
							e.consume();
							bu.setSelected(false);
						}
					}
				});

				panel.add(bu);
			}
		}

	}

	protected ImageIcon createImageIcon(String path, String description) {
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL, description);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	private int autoClick(JToggleButton bu) {

		String xy = bu.getName().substring(bu.getName().indexOf("#") + 1);
		int buX = Integer.parseInt(xy.split(";")[0]);
		int buY = Integer.parseInt(xy.split(";")[1]);

		if (mGrid[buX][buY].isBomb()) {
			bu.setBackground(Color.RED);
			return 0;
		}
		mGrid[buX][buY].getBu().setFont(new Font("Ariel", 1, 12));

		Color[] numColor = new Color[] { Color.LIGHT_GRAY, Color.BLUE,
				Color.GRAY, Color.GREEN, Color.YELLOW, Color.ORANGE,
				Color.PINK, Color.MAGENTA, Color.RED };

		mGrid[buX][buY].getBu().setForeground(
				numColor[mGrid[buX][buY].getBombValue()]);

		mGrid[buX][buY].getBu().setText(
				Integer.toString(mGrid[buX][buY].getBombValue()));

		mGrid[buX][buY].getBu().setSelected(true);

		mGrid[buX][buY].setOpened(true);

		if (mGrid[buX][buY].getBombValue() == 0) {

			if (buX > 0 && !mGrid[buX - 1][buY].isOpened())
				autoClick(mGrid[buX - 1][buY].getBu());

			if (buY > 0 && !mGrid[buX][buY - 1].isOpened())
				autoClick(mGrid[buX][buY - 1].getBu());

			if (buX < x - 1 && !mGrid[buX + 1][buY].isOpened())
				autoClick(mGrid[buX + 1][buY].getBu());

			if (buY < y - 1 && !mGrid[buX][buY + 1].isOpened())
				autoClick(mGrid[buX][buY + 1].getBu());

			if (buX > 0 && buY > 0 && !mGrid[buX - 1][buY - 1].isOpened())
				autoClick(mGrid[buX - 1][buY - 1].getBu());

			if (buX > 0 && buY < y - 1 && !mGrid[buX - 1][buY + 1].isOpened())
				autoClick(mGrid[buX - 1][buY + 1].getBu());

			if (buX < x - 1 && buY < y - 1
					&& !mGrid[buX + 1][buY + 1].isOpened())
				autoClick(mGrid[buX + 1][buY + 1].getBu());

			if (buX < x - 1 && buY > 0 && !mGrid[buX + 1][buY - 1].isOpened())
				autoClick(mGrid[buX + 1][buY - 1].getBu());
		}

		return 0;
	}

	public void placeBomb() {

		for (int i = 0; i < numOfBomb; i++) {

			int bombX = (int) (Math.random() * x);
			int bombY = (int) (Math.random() * y);

			while (mGrid[bombX][bombY].isBomb()) {
				bombX = (int) (Math.random() * x);
				bombY = (int) (Math.random() * y);
			}

			mGrid[bombX][bombY].setBomb(true);

			if (bombX > 0)
				mGrid[bombX - 1][bombY].setBombValue(mGrid[bombX - 1][bombY]
						.getBombValue() + 1);
			if (bombY > 0)
				mGrid[bombX][bombY - 1].setBombValue(mGrid[bombX][bombY - 1]
						.getBombValue() + 1);
			if (bombX < x - 1)
				mGrid[bombX + 1][bombY].setBombValue(mGrid[bombX + 1][bombY]
						.getBombValue() + 1);
			if (bombY < y - 1)
				mGrid[bombX][bombY + 1].setBombValue(mGrid[bombX][bombY + 1]
						.getBombValue() + 1);
			if (bombX > 0 && bombY > 0)
				mGrid[bombX - 1][bombY - 1]
						.setBombValue(mGrid[bombX - 1][bombY - 1]
								.getBombValue() + 1);
			if (bombX > 0 && bombY < y - 1)
				mGrid[bombX - 1][bombY + 1]
						.setBombValue(mGrid[bombX - 1][bombY + 1]
								.getBombValue() + 1);
			if (bombX < x - 1 && bombY < y - 1)
				mGrid[bombX + 1][bombY + 1]
						.setBombValue(mGrid[bombX + 1][bombY + 1]
								.getBombValue() + 1);
			if (bombX < x - 1 && bombY > 0)
				mGrid[bombX + 1][bombY - 1]
						.setBombValue(mGrid[bombX + 1][bombY - 1]
								.getBombValue() + 1);

			System.out.println("bomb placed at " + bombX + "," + bombY);

		}

		for (int j = 0; j < y; j++) {

			for (int i = 0; i < x; i++) {

				if (mGrid[i][j].isBomb())
					System.out.print("o");
				else
					System.out.print("x");

			}
			System.out.println("");

		}

		for (int i = 0; i < y; i++) {

			for (int j = 0; j < x; j++) {

				System.out.print(mGrid[j][i].getBombValue());

			}
			System.out.println("");

		}

	}

	private void newGame() {
		
		lose = false;
		frame.removeAll();
		frame.setLayout(new GridLayout(1, 0));
		panel = new Panel();
		MenuBar menuBar = new MenuBar();
		Menu menu = new Menu("File");
		MenuItem mi = new MenuItem("New Game");
		MenuItem retryMi = new MenuItem("Retry");
		
		bombCounter = new Menu(Integer.toString(numOfBomb));

		final JTextField tFRows = new JTextField();
		final JTextField tFColumns = new JTextField();
		final JTextField tFMines = new JTextField();
		
		final JComponent[] inputs = new JComponent[] { new JLabel("Rows"),
				tFRows, new JLabel("Columns"), tFColumns, new JLabel("Mines"),
				tFMines };
		
		retryMi.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				retry();
				
			}
		});

		mi.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, inputs, "New Game",
						JOptionPane.PLAIN_MESSAGE);
				y = Integer.parseInt(tFRows.getText());
				x = Integer.parseInt(tFColumns.getText());
				numOfBomb = Integer.parseInt(tFMines.getText());
				newGame();

			}
		});

		menu.add(mi);
		menu.add(retryMi);

		menuBar.add(menu);
		menuBar.add(bombCounter);

		frame.setMenuBar(menuBar);

		frame.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});

		makeButtons();
		placeBomb();

		frame.add(panel, "South");

		frame.setSize(y * 18 + 6, x * 18 + 22);


	}
	
	private void retry(){
		
		for (int j = 0; j < y; j++) {
			for (int i = 0; i < x; i++) {
				mGrid[i][j].getBu().setBackground(new Color(235, 235, 235));
				mGrid[i][j].getBu().setText("");
				mGrid[i][j].getBu().setSelected(false);
				mGrid[i][j].setBomb(false);
				mGrid[i][j].setBombValue(0);
				mGrid[i][j].setOpened(false);
				bombCounter.setLabel(Integer.toString(numOfBomb));
			}
		}
		lose = false;
		placeBomb();
		
	}

}
