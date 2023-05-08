package tictactoe;

import javax.swing.*;
import java.awt.*;

import static tictactoe.TicTacToeWindow.*;

public class GamePanel extends JPanel {

    private final BoardLogic boardLogic = new BoardLogic();
    private final JButton[][] buttons = new JButton[3][3];
    private final JLabel messageLabel;

    public GamePanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(BACKGROUND);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // message label
        messageLabel = new JLabel(boardLogic.currentPlayer());
        messageLabel.setFont(new Font("verdana", Font.BOLD, 20));
        messageLabel.setForeground(MESSAGE_COLOR);

        // restart button
        CustomButton restartButton = new CustomButton("▶️  Restart", true);
        restartButton.setFont(Font.BOLD, 16);
        restartButton.setColors(MESSAGE_COLOR, Color.BLACK);
        restartButton.padding(20, 15);
        restartButton.addActionListener(event -> resetGame());

        JPanel gridPanel = new JPanel();
        gridPanel.setMaximumSize(grisSize());
        gridPanel.setLayout(new GridLayout(3, 3, BUTTON_GAP, BUTTON_GAP));
        gridPanel.setBackground(BORDER);
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                int X = x, Y = y;
                CustomButton button = new CustomButton(" ", false);
                button.setSize(BUTTON_SIZE);
                button.setFont("fantasy", Font.BOLD, 34);
                button.setColors(BACKGROUND, SYMBOL_COLOR);
                button.addActionListener(event -> onButtonClick(X, Y));
                buttons[x][y] = button;
                gridPanel.add(button);
            }
        }

        restartButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        gridPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(getCustomToolbar());
        add(Box.createVerticalStrut(25));
        add(messageLabel);
        add(Box.createVerticalStrut(50));
        add(gridPanel);
        add(Box.createVerticalStrut(45));
        add(restartButton);
    }

    private Component getCustomToolbar() {
        JLabel titleLabel = new JLabel("Tic Tac Toe");
        titleLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        titleLabel.setForeground(TEXT_COLOR);
        CustomButton button = new CustomButton("Exit ️", true);
        button.setFont(Font.BOLD, 14);
        button.setColors(BORDER, TEXT_COLOR);
        button.padding(10, 8);
        button.addActionListener(event -> System.exit(0));
        button.setAlignmentX(Component.RIGHT_ALIGNMENT);

        Box horizontalBox = Box.createHorizontalBox();
        horizontalBox.add(titleLabel);
        horizontalBox.add(Box.createHorizontalGlue());
        horizontalBox.add(button);
        return horizontalBox;
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
        String previous = boardLogic.currentPlayer();
        String message = switch (boardLogic.makeMove(x, y)) {
            case MOVE -> boardLogic.currentPlayer();
            case WINNER -> String.format("<-- %s wins -->", previous);
            case TIE -> "<-- Game over -->";
            case INVALID -> null;
        };
        if (message != null) {
            messageLabel.setText(message);
            buttons[x][y].setText(previous);
        }
    }

    private Dimension grisSize() {
        int size = BUTTON_SIZE * 3 + BUTTON_GAP * 3;
        return new Dimension(size, size);
    }
}