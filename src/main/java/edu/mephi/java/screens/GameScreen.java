package edu.mephi.java.screens;

import edu.mephi.java.components.Board;
import edu.mephi.java.events.OnScoreEarnedListener;

import javax.swing.*;

public class GameScreen extends BaseScreen {

    int score;
    Board board;
    JLabel scoreLabel;

    public GameScreen(int width, int height) {
        super(width, height);
        score = 0;
        board = new Board();
        scoreLabel = new JLabel("Score: " + score, JLabel.CENTER);
        setLayout(null);
        board.setOnScoreEarned(onScoreEarnedListener);
        board.setBounds((width - board.getWidth()) / 2, 0, board.getWidth(), board.getHeight());
        scoreLabel.setBounds(0, 600, width, 100);
        add(board);
        add(scoreLabel);

    }

    OnScoreEarnedListener onScoreEarnedListener = (int earnedScore) -> {
        System.out.println("Score: " + earnedScore);
        this.score += earnedScore;
        scoreLabel.setText("Score: " + score);
    };

}
