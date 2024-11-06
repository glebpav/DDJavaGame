package edu.mephi.java.components;

import javax.swing.*;
import java.awt.*;

public class TransparentPanel extends JPanel {

    Color color;

    // private ;

    public TransparentPanel(Color color) {
        this.color = color;
        setBackground(color);
    }

    public void fadeOut(int timeInMilliseconds) {

    }

}
