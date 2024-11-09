package edu.mephi.java.logic;

import edu.mephi.java.components.Colorable;
import edu.mephi.java.events.OnItemSwapListener;
import edu.mephi.java.events.OnLostListener;
import edu.mephi.java.events.OnScoreEarnedListener;
import edu.mephi.java.utils.BoardPoint;
import edu.mephi.java.utils.ColorManager;

import java.util.Collection;
import java.util.Optional;

import static edu.mephi.java.GameSettings.NUMBER_OF_TILES_COLORS;

public class GameBoard {

    private Colorable[][] boardMatrix;

    private OnLostListener onLostListener;
    private OnScoreEarnedListener onScoreEarnedListener;

    private final int rows;
    private final int cols;

    public GameBoard(Colorable[][] boardMatrix) {
        this.boardMatrix = boardMatrix;
        this.rows = boardMatrix.length;
        this.cols = boardMatrix[0].length;
        putQuizOnBoard();
        deleteRowItemsAndGetScore();
    }

    public void setOnLostListener(OnLostListener onLostListener) {
        this.onLostListener = onLostListener;
    }

    public void setOnScoreEarnedListener(OnScoreEarnedListener onScoreEarnedListener) {
        this.onScoreEarnedListener = onScoreEarnedListener;
    }

    public int deleteRowItemsAndGetScore() {
        int earnedScore = 0;
        Optional<Collection<BoardPoint>> boardPoints;

        if ((boardPoints = GameLogic.checkBoard(boardMatrix)).isPresent()) {
            earnedScore = boardPoints.get().size();
            boardPoints.get().forEach(boardPoint ->
                    boardMatrix[boardPoint.getX()][boardPoint.getY()]
                            .setColorIdx(ColorManager.getRandomColorIdx(NUMBER_OF_TILES_COLORS)));
            deleteRowItemsAndGetScore();
        }

        return earnedScore;
    }

    public int getItemColor(int row, int col) {
        if (row < 0 || col < 0 || row >= rows || col >= cols) {
            throw new IndexOutOfBoundsException();
        }

        return boardMatrix[row][col].getColorIdx();
    }

    private void swap(BoardPoint point1, BoardPoint point2) {
        Colorable swappingItem = boardMatrix[point2.getX()][point2.getY()];
        Colorable boardItem = boardMatrix[point1.getX()][point1.getY()];
        boardMatrix[point1.getX()][point1.getY()] = swappingItem;
        boardMatrix[point2.getX()][point2.getY()] = boardItem;
    }

    public OnItemSwapListener onItemSwapListener = (BoardPoint point1, BoardPoint point2) -> {
        boolean wasSuccess = GameLogic.trySwapAndCheck(
                boardMatrix,
                point1.getX(),
                point1.getY(),
                point2.getX(),
                point2.getY()
        );

        if (wasSuccess) {
            GameBoard.this.swap(point1, point2);
            onScoreEarnedListener.onEarn(deleteRowItemsAndGetScore());
            if (!hasMove()) {
                onLostListener.onLost();
            }
        }

        return wasSuccess;

    };

    public boolean hasMove() {

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (row + 1 < rows) {
                    if (GameLogic.trySwapAndCheck(boardMatrix, row, col, row + 1, col)) {
                        return true;
                    }
                }
                if (col + 1 < cols) {
                    if (GameLogic.trySwapAndCheck(boardMatrix, row, col, row, col + 1)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void putQuizOnBoard() {
        while (!hasMove()) {
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    boardMatrix[row][col].setColorIdx(ColorManager.getRandomColorIdx(NUMBER_OF_TILES_COLORS));
                }
            }
            deleteRowItemsAndGetScore();
        }
    }

}
