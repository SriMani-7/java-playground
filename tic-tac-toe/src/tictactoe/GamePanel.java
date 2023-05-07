package tictactoe;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class GamePanel extends JPanel {

    public static final int BUTTON_SIZE = 90, BUTTON_GAP = 3;
    public static final Color
            BOXCOLOR = new Color(0xfa9884),
            BUTTONCOLOR = new Color(0xfff3e2);

    public GamePanel() {
        var box = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(box);
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        // Title label
        JLabel titleLabel = new JLabel("Tic Tac Toe");
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 22));

        // restart button
        JButton restartButton = new JButton("Restart");
        restartButton.setFocusPainted(false);
        restartButton.setContentAreaFilled(false);
        restartButton.setFont(restartButton.getFont().deriveFont(Font.BOLD, 16f));
        restartButton.setBorderPainted(false);

        // message label
        JLabel messageLabel = new JLabel("X move");
        messageLabel.setFont(new Font("Comic sans MS", Font.BOLD, 20));

        JPanel gridPanel = new JPanel();
        gridPanel.setMaximumSize(grisSize());
        gridPanel.setLayout(new GridLayout(3, 3, BUTTON_GAP, BUTTON_GAP));
        gridPanel.setBackground(BOXCOLOR);
        gridPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                JButton button = new JButton("");
                button.setSize(BUTTON_SIZE, BUTTON_SIZE);
                button.setBackground(BUTTONCOLOR);
                button.setFocusPainted(false);
                button.setFont(new Font("cursive", Font.PLAIN, 34));
                button.setBorderPainted(false);
                button.addActionListener(event -> {

                });
                gridPanel.add(button);
            }
        }

        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        restartButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        gridPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(titleLabel);
        add(Box.createVerticalStrut(25));
        add(messageLabel);
        add(Box.createVerticalStrut(50));
        add(gridPanel);
        add(Box.createVerticalStrut(45));
        add(restartButton);

    }

    private Dimension grisSize() {
        int size = BUTTON_SIZE * 3 + BUTTON_GAP * 3;
        return new Dimension(size, size);
    }

}
