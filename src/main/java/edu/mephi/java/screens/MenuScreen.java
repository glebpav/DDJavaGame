package edu.mephi.java.screens;

import edu.mephi.java.events.OnClickListener;

import javax.swing.*;
import java.awt.*;

public class MenuScreen extends BaseScreen {

    private final JButton startButton;
    private OnClickListener onButtonStartClicked;

    public MenuScreen(int width, int height) {
        super(width, height);

        JLabel label = new JLabel("Menu", SwingConstants.CENTER);
        startButton = new JButton("Start game");
        JComponent gap = new JPanel();

        getContentPane().setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        getContentPane().add(label, constraints);

        constraints.gridy = 1;
        getContentPane().add(gap, constraints);

        constraints.gridy = 2;
        getContentPane().add(startButton, constraints);

        startButton.addActionListener(e -> {
            if (onButtonStartClicked != null) onButtonStartClicked.onClick();
        });

    }

    public void setOnButtonStartClicked(OnClickListener onButtonStartClicked) {
        this.onButtonStartClicked = onButtonStartClicked;
    }
}
