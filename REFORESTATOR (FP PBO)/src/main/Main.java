package main;

import javax.swing.JFrame;

public class Main {
	
	public Main(GamePanel game) {
		JFrame frame = new JFrame("REFORESTATOR");
		// setDefaultCloseOperation means window can properly close when user clicks x button
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(game);
		frame.pack();
		// setResizeable means cannot resize window
		frame.setResizable(false);
		// setLocationRelativeTo means this window will be displayed at the center
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		game.start();
	}
}
