package edu.mephi.java.logic;

import edu.mephi.java.components.Colorable;
import edu.mephi.java.utils.BoardPoint;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

import static edu.mephi.java.GameSettings.COUNT_OF_ITEMS_IN_ROW_TO_DELETE;

public class GameLogic {

    public static Optional<Collection<BoardPoint>> checkBoard(Colorable[][] board) {
        Collection<BoardPoint> result = new HashSet<>();
        int rows = board.length;
        int cols = board[0].length;

        for (int row = 0; row < rows; row++) {
            int count = 1;
            for (int col = 1; col < cols; col++) {
                if (board[row][col].getColorIdx() == board[row][col - 1].getColorIdx()) {
                    count++;
                } else {
                    if (count >= COUNT_OF_ITEMS_IN_ROW_TO_DELETE) {
                        for (int k = 0; k < count; k++) {
                            result.add(new BoardPoint(row, col - 1 - k));
                        }
                    }
                    count = 1;
                }
            }
            if (count >= 3) {
                for (int k = 0; k < count; k++) {
                    result.add(new BoardPoint(row, cols - 1 - k));
                }
            }
        }

        for (int col = 0; col < cols; col++) {
            int count = 1;
            for (int row = 1; row < rows; row++) {
                if (board[row][col].getColorIdx() == board[row - 1][col].getColorIdx()) {
                    count++;
                } else {
                    if (count >= COUNT_OF_ITEMS_IN_ROW_TO_DELETE) {
                        for (int k = 0; k < count; k++) {
                            result.add(new BoardPoint(row - 1 - k, col));
                        }
                    }
                    count = 1;
                }
            }
            if (count >= 3) {
                for (int k = 0; k < count; k++) {
                    result.add(new BoardPoint(rows - 1 - k, col));
                }
            }
        }

        return result.isEmpty() ? Optional.empty() : Optional.of(result);
    }

    public static boolean trySwapAndCheck(Colorable[][] board, int row1, int col1, int row2, int col2) {
        swap(board, row1, col1, row2, col2);
        boolean result = checkForMatch(board, row1, col1) || checkForMatch(board, row2, col2);
        swap(board, row1, col1, row2, col2);
        return result;
    }

    public static boolean checkForMatch(Colorable[][] board, int row, int col) {
        int colorIdx = board[row][col].getColorIdx();

        int count = 1;
        for (int c = col - 1; c >= 0 && board[row][c].getColorIdx() == colorIdx; c--) count++;
        for (int c = col + 1; c < board[0].length && board[row][c].getColorIdx() == colorIdx; c++) count++;
        if (count >= 3) return true;

        count = 1;
        for (int r = row - 1; r >= 0 && board[r][col].getColorIdx() == colorIdx; r--) count++;
        for (int r = row + 1; r < board.length && board[r][col].getColorIdx() == colorIdx; r++) count++;
        return count >= 3;
    }

    private static void swap(Colorable[][] board, int row1, int col1, int row2, int col2) {
        Colorable temp = board[row1][col1];
        board[row1][col1] = board[row2][col2];
        board[row2][col2] = temp;
    }

}
