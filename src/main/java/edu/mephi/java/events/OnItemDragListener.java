package edu.mephi.java.events;

import edu.mephi.java.components.BoardItem;

import java.awt.event.MouseEvent;

public interface OnItemDragListener {
    void onDragged(BoardItem boardItem, MouseEvent mouseEvent);
}
