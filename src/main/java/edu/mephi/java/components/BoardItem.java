package edu.mephi.java.components;

import edu.mephi.java.events.OnItemDragListener;
import edu.mephi.java.utils.ColorManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import static edu.mephi.java.GameSettings.TILE_SIZE;

public class BoardItem extends JPanel implements Colorable {

    private boolean wasSwapped;

    private int colorIdx;

    private int initialDragX;
    private int initialDragY;

    private int initialTileX;
    private int initialTileY;

    private OnItemDragListener onItemDraggedListener;

    public BoardItem(int colorIdx) {
        setColorIdx(colorIdx);
        setPreferredSize(new Dimension(TILE_SIZE, TILE_SIZE));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseMotionAdapter);

        wasSwapped = false;
    }

    public void setColorIdx(int colorIdx) {
        this.colorIdx = colorIdx;
        setBackground(ColorManager.getColorByIdx(colorIdx));
    }

    public int getInitialDragX() {
        return initialDragX;
    }

    public int getInitialDragY() {
        return initialDragY;
    }

    public int getInitialTileX() {
        return initialTileX;
    }

    public int getInitialTileY() {
        return initialTileY;
    }

    public void setOnItemDraggedListener(OnItemDragListener onItemDraggedListener) {
        this.onItemDraggedListener = onItemDraggedListener;
    }

    public void wasSwapped() {
        wasSwapped = true;
    }

    MouseAdapter mouseAdapter = new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
            getParent().setComponentZOrder(BoardItem.this, 0);
            initialDragX = e.getXOnScreen();
            initialDragY = e.getYOnScreen();
            initialTileX = getX();
            initialTileY = getY();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (!wasSwapped) {
                setLocation(initialTileX, initialTileY);
            }
            wasSwapped = false;
        }
    };

    MouseMotionAdapter mouseMotionAdapter = new MouseMotionAdapter() {
        @Override
        public void mouseDragged(MouseEvent mouseEvent) {
            if (wasSwapped) return;
            onItemDraggedListener.onDragged(BoardItem.this, mouseEvent);
        }
    };

    @Override
    public int getColorIdx() {
        return colorIdx;
    }

}
