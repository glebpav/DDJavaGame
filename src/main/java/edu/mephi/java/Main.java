package edu.mephi.java;

import edu.mephi.java.screens.GameScreen;
import edu.mephi.java.screens.MenuScreen;

import static edu.mephi.java.GameSettings.WINDOW_HEIGHT;
import static edu.mephi.java.GameSettings.WINDOW_WIDTH;

public class Main {

	public static void main(String[] args) {

		MenuScreen menuScreen = new MenuScreen(WINDOW_WIDTH, WINDOW_HEIGHT);
		GameScreen gameScreen = new GameScreen(WINDOW_WIDTH, WINDOW_HEIGHT);

		menuScreen.showScreen();

		menuScreen.setOnButtonStartClicked(() -> {
			gameScreen.showScreen();
			menuScreen.hideScreen();
		});

	}

}