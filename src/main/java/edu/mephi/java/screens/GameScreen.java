package edu.mephi.java.screens;

import edu.mephi.java.components.Board;
import edu.mephi.java.events.OnScoreEarned;

public class GameScreen extends BaseScreen {

    Board board;

    public GameScreen() {
        board = new Board();
        board.setOnScoreEarned(onScoreEarned);
        add(board);
    }

    OnScoreEarned onScoreEarned = (int score) -> {
        System.out.println("Score: " + score);
    };

}
