package edu.mephi.java.components;

import edu.mephi.java.events.OnItemDragListener;
import edu.mephi.java.events.OnItemSwapListener;
import edu.mephi.java.logic.GameBoard;
import edu.mephi.java.utils.BoardPoint;
import edu.mephi.java.utils.ColorManager;

import javax.swing.*;
import java.awt.*;

import static edu.mephi.java.GameSettings.*;

public class BoardView extends JPanel {

    private final int rows = NUMBER_OF_TILES;
    private final int cols = NUMBER_OF_TILES;

    private BoardItemView[][] items;

    private OnItemSwapListener onItemSwapListener;

    public BoardView() {
        setLayout(null);
        items = new BoardItemView[rows][cols];
        generateItems();
        setBounds(new Rectangle(0, 0, cols * TILE_SIZE, rows * TILE_SIZE));
    }

    public void setOnItemSwapListener(OnItemSwapListener onItemSwapListener) {
        this.onItemSwapListener = onItemSwapListener;
    }

    public Colorable[][] getItems() {
        return items;
    }

    private void generateItems() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                items[i][j] = new BoardItemView(ColorManager.getRandomColorIdx(NUMBER_OF_TILES_COLORS));
                items[i][j].setOnItemDraggedListener(onItemDraggedListener);
                items[i][j].setBounds(TILE_SIZE * j, TILE_SIZE * i, TILE_SIZE, TILE_SIZE);
                System.out.println(items[i][j].getX());
                add(items[i][j]);
            }
        }
    }

    OnItemDragListener onItemDraggedListener = (boardItemView, mouseEvent) -> {
        int xDif = boardItemView.getInitialDragX() - mouseEvent.getXOnScreen();
        int yDif = boardItemView.getInitialDragY() - mouseEvent.getYOnScreen();

        int rowIdx = (boardItemView.getInitialTileY()) / TILE_SIZE;
        int columnIdx = (boardItemView.getInitialTileX()) / TILE_SIZE;

        if (Math.abs(xDif) >= Math.abs(yDif)) {
            if (Math.abs(xDif) < TILE_SIZE * TILE_SIZE_PART_TO_SWAP) {
                boardItemView.setLocation(boardItemView.getInitialTileX() - xDif, boardItemView.getInitialTileY());
            } else {
                if (xDif < 0) {
                    if (columnIdx == cols - 1) return;
                    BoardPoint point1 = new BoardPoint(rowIdx, columnIdx);
                    BoardPoint point2 = new BoardPoint(rowIdx, columnIdx + 1);
                    swapItemsIfNotProhibited(point1, point2);
                    // swap with right
                } else {
                    if (columnIdx == 0) return;
                    BoardPoint point1 = new BoardPoint(rowIdx, columnIdx);
                    BoardPoint point2 = new BoardPoint(rowIdx, columnIdx - 1);
                    swapItemsIfNotProhibited(point1, point2);
                    // swap with left
                }
            }
        } else {
            if (Math.abs(yDif) < TILE_SIZE * TILE_SIZE_PART_TO_SWAP) {
                boardItemView.setLocation(boardItemView.getInitialTileX(), boardItemView.getInitialTileY() - yDif);
            } else {
                if (yDif < 0) {
                    if (rowIdx == rows - 1) return;
                    BoardPoint point1 = new BoardPoint(rowIdx, columnIdx);
                    BoardPoint point2 = new BoardPoint(rowIdx + 1, columnIdx);
                    swapItemsIfNotProhibited(point1, point2);
                    // swap with bottom
                } else {
                    if (rowIdx == 0) return;
                    BoardPoint point1 = new BoardPoint(rowIdx, columnIdx);
                    BoardPoint point2 = new BoardPoint(rowIdx - 1, columnIdx);
                    swapItemsIfNotProhibited(point1, point2);
                    // swap with top
                }
            }
        }
    };

    private void swapItemsIfNotProhibited(BoardPoint item1, BoardPoint item2) {

        if (onItemSwapListener == null) return;

        if (onItemSwapListener.onSuccessSwap(item1, item2)) {
            BoardItemView boardItemView = items[item2.getX()][item2.getY()];
            BoardItemView swappingItem = items[item1.getX()][item1.getY()];

            Point pointA = swappingItem.getLocation();
            Point pointB = new Point(boardItemView.getInitialTileX(), boardItemView.getInitialTileY());

            swappingItem.setLocation(pointB);
            boardItemView.setLocation(pointA);

            items[item2.getX()][item2.getY()].wasSwapped();
            // updateBoard();
        }
    }

}
