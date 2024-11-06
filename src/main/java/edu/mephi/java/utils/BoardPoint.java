package edu.mephi.java.utils;

import java.util.Objects;

public class BoardPoint {

    private final int x;
    private final int y;

    public BoardPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardPoint that = (BoardPoint) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "BoardPoint{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
