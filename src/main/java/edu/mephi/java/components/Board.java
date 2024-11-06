package edu.mephi.java.components;

import edu.mephi.java.events.OnItemDragListener;
import edu.mephi.java.events.OnScoreEarnedListener;
import edu.mephi.java.utils.BoardPoint;
import edu.mephi.java.utils.ColorManager;
import edu.mephi.java.utils.GameLogic;
import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;

import static edu.mephi.java.GameSettings.*;

public class Board extends JPanel {

    private final int rows = NUMBER_OF_TILES;
    private final int cols = NUMBER_OF_TILES;
    private final BoardItem[][] items;

    private OnScoreEarnedListener onScoreEarnedListener;

    public Board() {
        setLayout(null);
        items = new BoardItem[rows][cols];
        generateItems();
        setBounds(new Rectangle(0, 0, cols * TILE_SIZE, rows * TILE_SIZE));
    }

    public void setOnScoreEarned(OnScoreEarnedListener onScoreEarnedListener) {
        this.onScoreEarnedListener = onScoreEarnedListener;
    }

    private void generateItems() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                items[i][j] = new BoardItem(ColorManager.getRandomColorIdx(NUMBER_OF_TILES_COLORS));
                items[i][j].setOnItemDraggedListener(onItemDraggedListener);
                items[i][j].setBounds(TILE_SIZE * j, TILE_SIZE * i, TILE_SIZE, TILE_SIZE);
                add(items[i][j]);
            }
        }
        clearBoard(GameLogic.checkBoard(items));
    }

    OnItemDragListener onItemDraggedListener = (boardItem, mouseEvent) -> {
        int xDif = boardItem.getInitialDragX() - mouseEvent.getXOnScreen();
        int yDif = boardItem.getInitialDragY() - mouseEvent.getYOnScreen();

        int rowIdx = (boardItem.getInitialTileY() - getY()) / TILE_SIZE;
        int columnIdx = (boardItem.getInitialTileX() - getX()) / TILE_SIZE;

        if (Math.abs(xDif) >= Math.abs(yDif)) {
            if (Math.abs(xDif) < TILE_SIZE) {
                boardItem.setLocation(boardItem.getInitialTileX() - xDif, boardItem.getInitialTileY());
            } else {
                if (xDif < 0) {
                    if (columnIdx == cols - 1) return;
                    Point point1 = new Point(rowIdx, columnIdx);
                    Point point2 = new Point(rowIdx, columnIdx + 1);
                    swapItems(point1, point2);
                    // swap with right
                } else {
                    if (columnIdx == 0) return;
                    Point point1 = new Point(rowIdx, columnIdx);
                    Point point2 = new Point(rowIdx, columnIdx - 1);
                    swapItems(point1, point2);
                    // swap with left
                }
            }
        } else {
            if (Math.abs(yDif) < TILE_SIZE) {
                boardItem.setLocation(boardItem.getInitialTileX(), boardItem.getInitialTileY() - yDif);
            } else {
                if (yDif < 0) {
                    if (rowIdx == rows - 1) return;
                    Point point1 = new Point(rowIdx, columnIdx);
                    Point point2 = new Point(rowIdx + 1, columnIdx);
                    swapItems(point1, point2);
                    // swap with bottom
                } else {
                    if (rowIdx == 0) return;
                    Point point1 = new Point(rowIdx, columnIdx);
                    Point point2 = new Point(rowIdx - 1, columnIdx);
                    swapItems(point1, point2);
                    // swap with top
                }
            }
        }
    };

    private void swapItems(Point item1, Point item2) {


        BoardItem swappingItem = items[item2.x][item2.y];
        BoardItem boardItem = items[item1.x][item1.y];

        Point pointA = swappingItem.getLocation();
        Point pointB = new Point(boardItem.getInitialTileX(), boardItem.getInitialTileY());

        swappingItem.setLocation(pointB);
        boardItem.setLocation(pointA);

        items[item1.x][item1.y] = swappingItem;
        items[item2.x][item2.y] = boardItem;

        boardItem.wasSwapped();

        processBoard();
    }

    private void processBoard() {
        Collection<BoardPoint> deletingPoints = GameLogic.checkBoard(items);
        if (onScoreEarnedListener != null && !deletingPoints.isEmpty()) {
            onScoreEarnedListener.onEarn(deletingPoints.size());
        }
        clearBoard(deletingPoints);
    }

    private void clearBoard(Collection<BoardPoint> deletingPoints) {
        while (!deletingPoints.isEmpty()) {
            for (BoardPoint boardPoint : deletingPoints) {
                items[boardPoint.getX()][boardPoint.getY()]
                        .setColorIdx(
                                ColorManager.getRandomColorIdx(NUMBER_OF_TILES_COLORS)
                        );
            }
            deletingPoints = GameLogic.checkBoard(items);
        }
    }

}
