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
		
		final Game game = new Game(20, 20, 20, frame);;
		
		JMenuBar mb = new JMenuBar();
		JMenu menu = new JMenu("Options");
		JMenuItem newGameMI = new JMenuItem("New Game");
		JMenuItem retryMI = new JMenuItem("Retry");
		
		retryMI.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				game.retry();
			}
		});
		
		menu.add(newGameMI);
		menu.add(retryMI);
		mb.add(menu);
		frame.setJMenuBar(mb);
		
		frame.setVisible(true);
		frame.setResizable(false);

	}

}
