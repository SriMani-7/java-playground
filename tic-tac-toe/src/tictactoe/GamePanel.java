package tictactoe;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private final BoardLogic boardLogic;
    private final JButton[][] buttons;
    private final JLabel messageLabel;

    public GamePanel() {
        boardLogic = new BoardLogic();
        buttons = new JButton[3][3];
        var box = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(box);
        setBackground(BOXCOLOR);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Title label
        JLabel titleLabel = new JLabel("Tic Tac Toe");
        titleLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));

        titleLabel.setForeground(new Color(0xDDE8E9));

        messageLabel = new JLabel("X");
        messageLabel.setFont(new Font("verdana", Font.BOLD, 20));
        messageLabel.setForeground(new Color(0x3FE4C0));

        JPanel gridPanel = new JPanel();

        // restart button
        JButton restartButton = new JButton("Restart");
        restartButton.setFocusPainted(false);
        restartButton.setContentAreaFilled(false);
        restartButton.setFont(restartButton.getFont().deriveFont(Font.BOLD, 16f));
        restartButton.setBorderPainted(false);
        restartButton.setForeground(new Color(0xF6D220));
        restartButton.addActionListener(event -> resetGame());

        // message label

        gridPanel.setMaximumSize(grisSize());
        gridPanel.setLayout(new GridLayout(3, 3, BUTTON_GAP, BUTTON_GAP));
        gridPanel.setBackground(BUTTONCOLOR);
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                int X = x, Y = y;
                JButton button = new JButton(" ");
                button.setSize(BUTTON_SIZE, BUTTON_SIZE);
                button.setFocusPainted(false);
                button.setContentAreaFilled(false);
                button.setFont(new Font("cursive", Font.PLAIN, 34));
                button.setBorderPainted(false);
                button.setOpaque(true);
                button.setBackground(BOXCOLOR);
                button.setForeground(new Color(0xFDFDFD));
                button.addActionListener(event -> onButtonClick(X, Y));
                buttons[x][y] = button;
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

    private void resetGame() {
        boardLogic.reset();
        for (JButton[] row : buttons) {
            for (JButton button : row) {
                button.setText(" ");
            }
        }
        messageLabel.setText(boardLogic.currentPlayer());
    }

    public void onButtonClick(int x, int y) {
        String message = switch (boardLogic.makeMove(x, y)) {
            case MOVE -> boardLogic.currentPlayer();
            case WINNER -> String.format("<-- %s wins -->", boardLogic.currentPlayer());
            case TIE -> "<-- Game over -->";
            case INVALID -> null;
        };
        if (message != null) {
            messageLabel.setText(message);
            buttons[x][y].setText(boardLogic.currentPlayer());
        }
    }

    private Dimension grisSize() {
        int size = BUTTON_SIZE * 3 + BUTTON_GAP * 3;
        return new Dimension(size, size);
    }


    public static final int BUTTON_SIZE = 90, BUTTON_GAP = 3;
    public static final Color
            BOXCOLOR = new Color(0x283C4F),
            BUTTONCOLOR = new Color(0x097DC9);

}
