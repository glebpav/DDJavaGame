package edu.mephi.java.events;

import edu.mephi.java.components.BoardItemView;

import java.awt.event.MouseEvent;

public interface OnItemDragListener {
    void onDragged(BoardItemView boardItemView, MouseEvent mouseEvent);
}
