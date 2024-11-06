package edu.mephi.java.components;

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
    private Color color;

    private int initialDragX;
    private int initialDragY;

    private int initialTileX;
    private int initialTileY;

    private Timer fadeTimer;

    private OnItemDraggedListener onItemDraggedListener;

    public BoardItem(int colorIdx) {
        this.colorIdx = colorIdx;
        this.color = ColorManager.getColorByIdx(colorIdx);
        setBackground(color);
        setPreferredSize(new Dimension(TILE_SIZE, TILE_SIZE));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseMotionAdapter);

        wasSwapped = false;
    }

    public void setColorIdx(int colorIdx) {
        this.colorIdx = colorIdx;
        this.color = ColorManager.getColorByIdx(colorIdx);
        setBackground(color);
    }

    public Color getColor() {
        return color;
    }

    public int getInitialDragX() {
        return initialDragX;
    }

    public void setInitialDragX(int initialDragX) {
        this.initialDragX = initialDragX;
    }

    public int getInitialDragY() {
        return initialDragY;
    }

    public void setInitialDragY(int initialDragY) {
        this.initialDragY = initialDragY;
    }

    public int getInitialTileX() {
        return initialTileX;
    }

    public void setInitialTileX(int initialTileX) {
        this.initialTileX = initialTileX;
    }

    public int getInitialTileY() {
        return initialTileY;
    }

    public void setInitialTileY(int initialTileY) {
        this.initialTileY = initialTileY;
    }

    public void setOnItemDraggedListener(OnItemDraggedListener onItemDraggedListener) {
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
        public void mouseDragged(MouseEvent e) {
            if (wasSwapped) return;
            onItemDraggedListener.onDragged(BoardItem.this, e);
        }
    };

    @Override
    public int getColorIdx() {
        return colorIdx;
    }

    public interface OnItemDraggedListener {
        void onDragged(BoardItem boardItem, MouseEvent mouseEvent);
    }

}
