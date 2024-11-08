package edu.mephi.java.logic;

import edu.mephi.java.components.Colorable;
import edu.mephi.java.utils.BoardPoint;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

import static edu.mephi.java.GameSettings.COUNT_OF_ITEMS_IN_ROW_TO_DELETE;

public class GameLogic {

    /*
    public static Collection<BoardPoint> checkBoard(Colorable[][] board) {

        Collection<BoardPoint> deletingPoints = new HashSet<>();

        int columnsSize = board[0].length;
        int rowsSize = board.length;

        // check row
        for(int rowIdx = 0; rowIdx < rowsSize; rowIdx++) {

            if (rowsSize < 2) {
                break;
            }

            int lastColor = board[rowIdx][0].getColorIdx();
            int lastColorPosition = 0;
            boolean colorWasChanged;
            boolean isLastElement;
            for (int colIdx = 0; colIdx < board[rowIdx].length - 1; colIdx++) {

                colorWasChanged = lastColor != board[rowIdx][colIdx + 1].getColorIdx();
                isLastElement = colIdx + 1 == board[rowIdx].length - 1;

                if (colorWasChanged || isLastElement) {
                    System.out.println("horiz: " + (colIdx + 1 - lastColorPosition));
                    if (colIdx + 1 - lastColorPosition >= COUNT_OF_ITEMS_IN_ROW_TO_DELETE) {
                        addInRangeHorizontal(rowIdx, lastColorPosition, colIdx, deletingPoints);
                    }
                    lastColor = board[rowIdx][colIdx + 1].getColorIdx();
                    lastColorPosition = colIdx + 1;
                }


            }
            System.out.println("new row");

        }

        // check column
        for(int columnIdx = 0; columnIdx < columnsSize; columnIdx++) {

            if (board.length < 2) {
                break;
            }

            int lastColor = board[0][columnIdx].getColorIdx();
            int lastColorPosition = 0;
            boolean colorWasChanged;
            boolean isLastElement;
            for (int j = 0; j < rowsSize - 1; j++) {

                colorWasChanged = lastColor != board[j + 1][columnIdx].getColorIdx();
                isLastElement = j + 1 == columnsSize - 1;

                if (colorWasChanged || isLastElement) {
                    System.out.println("vert: " + (j - lastColorPosition));
                    if (j - lastColorPosition >= COUNT_OF_ITEMS_IN_ROW_TO_DELETE) {
                        addInRangeVertical(columnIdx, lastColorPosition, j, deletingPoints);
                    }
                    lastColor = board[j + 1][columnIdx].getColorIdx();
                    lastColorPosition = j + 1;
                }

            }

            System.out.println("new column");

        }

        return deletingPoints;
    }
    */

    public static Optional<Collection<BoardPoint>> checkBoard(Colorable[][] board) {
        Collection<BoardPoint> result = new HashSet<>();
        int rows = board.length;
        int cols = board[0].length;

        // Check horizontal matches
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

        // Check vertical matches
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

    // Пытается поменять две ячейки местами и проверить, образуется ли комбинация
    public static boolean trySwapAndCheck(Colorable[][] board, int row1, int col1, int row2, int col2) {
        swap(board, row1, col1, row2, col2); // Меняем местами ячейки
        boolean result = checkForMatch(board, row1, col1) || checkForMatch(board, row2, col2);
        swap(board, row1, col1, row2, col2); // Возвращаем местами обратно
        return result;
    }

    // Проверяет, есть ли три или более ячеек одного цвета в ряд для заданной позиции
    public static boolean checkForMatch(Colorable[][] board, int row, int col) {
        int colorIdx = board[row][col].getColorIdx();

        // Проверяем горизонтальные совпадения
        int count = 1;
        for (int c = col - 1; c >= 0 && board[row][c].getColorIdx() == colorIdx; c--) count++;
        for (int c = col + 1; c < board[0].length && board[row][c].getColorIdx() == colorIdx; c++) count++;
        if (count >= 3) return true;

        // Проверяем вертикальные совпадения
        count = 1;
        for (int r = row - 1; r >= 0 && board[r][col].getColorIdx() == colorIdx; r--) count++;
        for (int r = row + 1; r < board.length && board[r][col].getColorIdx() == colorIdx; r++) count++;
        if (count >= 3) return true;

        return false;
    }

    // Меняет местами две ячейки
    private static void swap(Colorable[][] board, int row1, int col1, int row2, int col2) {
        Colorable temp = board[row1][col1];
        board[row1][col1] = board[row2][col2];
        board[row2][col2] = temp;
    }

}
