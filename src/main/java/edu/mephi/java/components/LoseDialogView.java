package edu.mephi.java.components;

import javax.swing.*;
import java.awt.*;

public class LoseDialogView extends JDialog {

    private final JButton exitButton;

    public LoseDialogView(JFrame parent) {
        super(parent);
        JLabel label = new JLabel("You lost :-(");
        exitButton = new JButton("Close game");
        label.setHorizontalAlignment(SwingConstants.CENTER);

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
        getContentPane().add(exitButton, constraints);

        setSize(new Dimension(200, 100));
        setLocation(new Point((parent.getX() - getWidth()) / 2, (parent.getY() - getHeight()) / 2));
    }

    public JButton getExitButton() {
        return exitButton;
    }

}
