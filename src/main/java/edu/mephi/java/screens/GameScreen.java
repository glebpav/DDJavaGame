package edu.mephi.java.screens;

import edu.mephi.java.components.BoardView;
import edu.mephi.java.components.LoseDialogView;
import edu.mephi.java.events.OnLostListener;
import edu.mephi.java.events.OnScoreEarnedListener;
import edu.mephi.java.logic.GameBoard;
import edu.mephi.java.logic.Session;

import javax.swing.*;

public class GameScreen extends BaseScreen {

    private JLabel scoreLabel;
    private BoardView boardView;
    private LoseDialogView loseDialogView;

    private final GameBoard gameBoard;

    Session session;

    public GameScreen(int width, int height) {
        super(width, height);

        session = new Session();

        buildUi();

        gameBoard = new GameBoard(boardView.getItems());

        // board.setOnScoreEarned(onScoreEarnedListener);
        gameBoard.setOnScoreEarnedListener(onScoreEarnedListener);
        gameBoard.setOnLostListener(onLostListener);
        boardView.setOnItemSwapListener(gameBoard.onItemSwapListener);
        loseDialogView.getExitButton().addActionListener(e -> System.exit(0));
    }

    private void buildUi() {
        setLayout(null);

        boardView = new BoardView();
        scoreLabel = new JLabel("Score: " + session.getScore(), JLabel.CENTER);
        loseDialogView = new LoseDialogView(this);

        boardView.setBounds((getSize().width - boardView.getWidth()) / 2, 0, boardView.getWidth(), boardView.getHeight());
        scoreLabel.setBounds(0, 600, getSize().width, 100);

        add(boardView);
        add(scoreLabel);
    }

    OnScoreEarnedListener onScoreEarnedListener = (int earnedScore) -> {
        session.addScore(earnedScore);
        scoreLabel.setText("Score: " + session.getScore());
    };

    OnLostListener onLostListener = () -> {
        loseDialogView.setVisible(true);
    };

}
