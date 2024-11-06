package edu.mephi.java.screens;

import javax.swing.*;
import java.awt.*;

public class BaseScreen extends JFrame {

    public static final int BASE_SCREEN_WIDTH = 400;
    public static final int BASE_SCREEN_HEIGHT = 400;

    public BaseScreen() {
        this(BASE_SCREEN_WIDTH, BASE_SCREEN_HEIGHT);
    }

    public BaseScreen(int screenWidth, int screenHeight) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setResizable(false);
        pack();
        hideScreen();
        // setLayout(null);
    }

    public void showScreen() {
        setVisible(true);
    }

    public void hideScreen() {
        setVisible(false);
    }

}
