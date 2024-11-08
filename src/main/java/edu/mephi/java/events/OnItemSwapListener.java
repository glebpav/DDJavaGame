package edu.mephi.java.events;

import edu.mephi.java.utils.BoardPoint;

public interface OnItemSwapListener {
    boolean onSuccessSwap(BoardPoint point1, BoardPoint point2);
}
