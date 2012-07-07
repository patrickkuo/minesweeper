package pat.game.MS;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class Game {

	private boolean lose;

	private static final int buWidth = 20, buHeight = 20;

	private Field[][] fieldGrid;
	private int numOfBomb, gameWidth, gameHeight;

	public Game(int x, int y, int numOfBomb, JFrame frame) {
		frame.setSize(x * buWidth + 5, y * buHeight + 50);
		frame.setLayout(null);
		frame.setBackground(Color.red);
		fieldGrid = new Field[x][y];

		for (int j = 0; j < y; j++) {
			for (int i = 0; i < x; i++) {
				JToggleButton bu = new ImageButton();
				bu.setMargin(new Insets(0, 0, 0, 0));
				bu.setBounds(i * buWidth, j * buHeight, buWidth, buHeight);
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
		lose = false;

	}

	private void setMouseAction(final Field field) {
		JToggleButton bu = field.getBu();

		bu.addMouseListener(new MouseListener() {

			public void mouseReleased(MouseEvent arg0) {
				arg0.consume();
			}

			public void mousePressed(MouseEvent arg0) {
				if (arg0.getButton() != MouseEvent.BUTTON3)
					mouseClicked(arg0);
			}

			public void mouseExited(MouseEvent arg0) {
			}

			public void mouseEntered(MouseEvent arg0) {
			}

			public void mouseClicked(MouseEvent arg0) {
				if (!lose) {
					
					if (arg0.getButton() == MouseEvent.BUTTON1) { // if left
																	// click

						if (!field.isFlaged()) { // consume action if field is
													// flaged
							click(field, arg0);
						}else
							field.getBu().setSelected(false);

					} else if (arg0.getButton() == MouseEvent.BUTTON3) { // right
																			// click
						ImageButton bu = (ImageButton) field.getBu();
						if (!field.isOpened()) {
							if (field.isFlaged()) {
								field.setFlaged(false);
								bu.setImg(null);
							} else {
								field.setFlaged(true);
								bu.setImg(ImageButton.FLAG_IMAGE);
							}
						}
						bu.repaint();

					}
				}
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
			bu.setBackground(Color.RED);
			bu.setImg(ImageButton.MINE_IMAGE);
			this.lose = true;
			return 0;
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

}
