package pat.game.MS;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class Game {
	private ImageButton faceBu;
	private boolean gameEnd;
	private int remaining, flagCount;

	private static final int buWidth = 20, buHeight = 20;

	private Field[][] fieldGrid;
	private int numOfBomb, gameWidth, gameHeight;

	public Game(int x, int y, int numOfBomb, JFrame frame) {
		frame.setSize(x * buWidth + 5, y * buHeight + 80);
		frame.setLayout(null);
		fieldGrid = new Field[x][y];
		this.gameWidth = x;
		this.gameHeight = y;
		this.remaining = x * y;
		this.flagCount = 0;

		faceBu = new ImageButton();
		faceBu.setMargin(new Insets(0, 0, 0, 0));
		faceBu.setBounds(buWidth * gameWidth / 2 - 10, 5, buWidth, buHeight);
		faceBu.setImg(ImageButton.HAHA_IMAGE);
		faceBuAction(faceBu);
		frame.add(faceBu);

		for (int j = 0; j < y; j++) { // add buttons
			for (int i = 0; i < x; i++) {
				JToggleButton bu = new ImageButton();
				bu.setMargin(new Insets(0, 0, 0, 0));
				bu.setBounds(i * buWidth, j * buHeight + 30, buWidth, buHeight);
				bu.setFocusable(false);
				fieldGrid[i][j] = new Field(bu, i, j);
				setMouseAction(fieldGrid[i][j]);
				frame.add(bu);
			}
		}

		this.numOfBomb = numOfBomb;
		this.gameWidth = x;
		this.gameHeight = y;
		placeBomb();
		gameEnd = false;

	}

	private void faceBuAction(final ImageButton faceBu) {

		faceBu.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				retry();

			}
		});

		faceBu.addMouseListener(new MouseListener() {

			public void mouseReleased(MouseEvent arg0) {
				faceBu.setImg(ImageButton.HAHA_IMAGE);
				faceBu.repaint();

			}

			public void mousePressed(MouseEvent arg0) {
				faceBu.setImg(ImageButton.OOO_IMAGE);
				faceBu.repaint();
			}

			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

	}

	private void setMouseAction(final Field field) {
		JToggleButton bu = field.getBu();

		bu.addMouseListener(new MouseListener() {

			public void mouseReleased(MouseEvent arg0) {
				
				if (gameEnd) {
					arg0.consume();
				} else {
					faceBu.doMouseEvent(arg0);
					if (arg0.getButton() == MouseEvent.BUTTON1) { // if left
																	// click

						if (!field.isFlaged() && !field.isOpened()) { // consume action if field is flaged
							click(field, arg0);
						}

					} else if (arg0.getButton() == MouseEvent.BUTTON3) { // right
																			// click
						ImageButton bu = (ImageButton) field.getBu();
						if (!field.isOpened()) {
							if (field.isFlaged()) {
								field.setFlaged(false);
								bu.setImg(null);
								--flagCount;
								System.out.println("flag: "+flagCount);
							} else {
								field.setFlaged(true);
								bu.setImg(ImageButton.FLAG_IMAGE);
								++flagCount;
								System.out.println("flag: "+flagCount);
							}
						}
						bu.repaint();

					}
				}
			}

			public void mousePressed(MouseEvent arg0) {
				if (gameEnd || field.isOpened()) {
					arg0.consume();
					field.getBu().setSelected(false);
					
				} else {
					System.out.println("2");
					if (arg0.getButton() != MouseEvent.BUTTON3)
						mouseClicked(arg0);
					faceBu.doMouseEvent(arg0);
				}
			}

			public void mouseExited(MouseEvent arg0) {
				arg0.consume();
			}

			public void mouseEntered(MouseEvent arg0) {
				arg0.consume();
			}

			public void mouseClicked(MouseEvent arg0) {
				arg0.consume();
			}
		});
	}

	private void placeBomb() {

		for (int i = 0; i < numOfBomb; i++) {

			int bombX = (int) (Math.random() * gameWidth);
			int bombY = (int) (Math.random() * gameHeight);

			while (fieldGrid[bombX][bombY].isBomb()) {
				bombX = (int) (Math.random() * gameWidth);
				bombY = (int) (Math.random() * gameHeight);
			}

			fieldGrid[bombX][bombY].setBomb(true);

			for (int k = -1; k < 2; k++) {
				for (int j = -1; j < 2; j++) {

					if (!(k == 0 && j == 0)) {

						if (bombX + k > -1 && bombX + k < gameWidth
								&& bombY + j > -1 && bombY + j < gameHeight)
							fieldGrid[bombX + k][bombY + j]
									.setBombValue(fieldGrid[bombX + k][bombY
											+ j].getBombValue() + 1);
					}
				}
			}
			System.out.println("bomb placed at " + bombX + "," + bombY);

		}
	}

	private int click(Field field, MouseEvent me) {
		int buX = field.getX();
		int buY = field.getY();
		ImageButton bu = (ImageButton) field.getBu();
		bu.setSelected(true);

		if (fieldGrid[buX][buY].isBomb()) {

			faceBu.setImg(ImageButton.DEAD_IMAGE);

			bu.setBackground(Color.RED);
			bu.setImg(ImageButton.MINE_IMAGE);

			for (Field[] fieldArray : fieldGrid) {
				for (Field fieldA : fieldArray) {
					ImageButton ibu = (ImageButton) fieldA.getBu();
					if (fieldA.isBomb() && !fieldA.isFlaged()) {
						ibu.setSelected(true);
						ibu.setImg(ImageButton.MINE_IMAGE);
					}
				}
			}

			this.gameEnd = true;

			return 0;
		}

		--remaining;
		System.out.println("remaining: "+remaining);

		if (remaining - flagCount == 0) {
			System.out.println("win");
			faceBu.setImg(ImageButton.WIN_IMAGE);
			faceBu.repaint();
			this.gameEnd = true;
			
		}

		fieldGrid[buX][buY].getBu().setFont(new Font("Ariel", 1, 12));

		Color[] numColor = new Color[] { Color.LIGHT_GRAY, Color.BLUE,
				Color.GRAY, Color.GREEN, Color.YELLOW, Color.ORANGE,
				Color.PINK, Color.MAGENTA, Color.RED };

		fieldGrid[buX][buY].getBu().setForeground(
				numColor[fieldGrid[buX][buY].getBombValue()]);

		fieldGrid[buX][buY].getBu().setText(
				Integer.toString(fieldGrid[buX][buY].getBombValue()));

		// mGrid[buX][buY].getBu().setSelected(true);

		fieldGrid[buX][buY].setOpened(true);

		if (fieldGrid[buX][buY].getBombValue() == 0) {

			for (int i = -1; i < 2; i++) {
				for (int j = -1; j < 2; j++) {

					if (!(i == 0 && j == 0)) {

						if (buX + i > -1 && buX + i < gameWidth && buY + j > -1
								&& buY + j < gameHeight
								&& !fieldGrid[buX + i][buY + j].isOpened()) {

							click(fieldGrid[buX + i][buY + j], me);
						}

					}

				}
			}

		}

		return 0;
	}

	public void retry() {

		this.gameEnd = false;

		for (Field[] fieldArray : fieldGrid) {

			for (Field field : fieldArray) {

				field.setBomb(false);
				field.setFlaged(false);
				field.setOpened(false);
				field.setBombValue(0);
				ImageButton bu = (ImageButton) field.getBu();
				bu.setImg(null);
				bu.setSelected(false);
				bu.setText("");
				bu.setBackground(null);
				flagCount=0;
				remaining=gameHeight*gameWidth;
			}
		}
		placeBomb();
	}

}
