package edu.mephi.java;

import edu.mephi.java.screens.GameScreen;
import edu.mephi.java.screens.MenuScreen;

public class Main {

	public static void main(String[] args) {

		MenuScreen menuScreen = new MenuScreen();
		GameScreen gameScreen = new GameScreen();

		menuScreen.showScreen();

		menuScreen.setOnButtonStartClicked(() -> {
			System.out.println("Start game button clicked");
			gameScreen.showScreen();
			menuScreen.hideScreen();
		});


		// JFrame frame = new JFrame("Three in a row");
		/*Game game = new Game();
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);*/

		/*game.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (game.isGameOver() && e.getKeyCode() == KeyEvent.VK_R) {
					game.restart();
				}
			}
		});*/

	}


}