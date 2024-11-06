package edu.mephi.java.utils;

import edu.mephi.java.components.Colorable;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

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

    public static Collection<BoardPoint> checkBoard(Colorable[][] board) {
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

        return result;
    }

    // Todo: remove
    private static void addInRangeHorizontal(int row, int columnFrom, int columnTo, Collection<BoardPoint> points) {
        for (int columnIdx = columnFrom; columnIdx <= columnTo; columnIdx++) {
            points.add(new BoardPoint(row, columnIdx));
        }
    }

    // Todo: remove
    private static void addInRangeVertical(int column, int rowFrom, int rowTo, Collection<BoardPoint> points) {
        for (int rowIdx = rowFrom; rowIdx <= rowTo; rowIdx++) {
            points.add(new BoardPoint(column, rowIdx));
        }
    }

}
