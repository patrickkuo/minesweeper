package pat.game.MS;

import java.awt.event.*;
import javax.swing.*;

public class MSGUI {

	public MSGUI() {
		JFrame frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});
		JMenuBar mb = new JMenuBar();
		JMenu menu = new JMenu("Options");
		JMenuItem mi = new JMenuItem("New Game");
		menu.add(mi);
		mb.add(menu);
		frame.setJMenuBar(mb);		
		
		new Game(20, 20, 20, frame);

		frame.setVisible(true);
		frame.setResizable(false);

	}

}
