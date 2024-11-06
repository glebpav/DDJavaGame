package edu.mephi.java.utils;

import java.awt.*;

public class ColorManager {

    private static final Color[] KNOWN_COLORS = {
            Color.BLUE,
            Color.RED,
            Color.GREEN,
            Color.YELLOW,
            Color.ORANGE,
            Color.MAGENTA,
            Color.CYAN,
            Color.PINK,
            Color.BLACK,
            Color.GRAY,
    };

    public static int getRandomColorIdx(int maxColorIdx) {
        return (int) (Math.random() * maxColorIdx);
    }

    public static Color getColorByIdx(int idx) {
        if (idx < 0 || idx >= KNOWN_COLORS.length) {
            throw new IllegalArgumentException("Invalid color idx: " + idx);
        }
        return KNOWN_COLORS[idx];
    }

}
