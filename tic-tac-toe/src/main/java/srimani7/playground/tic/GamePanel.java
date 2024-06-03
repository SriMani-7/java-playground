package srimani7.playground.tic;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

/**
 * This class represents the game panel of a Tic Tac Toe game.
 * It is responsible for rendering the game interface, handling user inputs, and updating the game state.
 *
 * @author [SriMani-7]
 */
public class GamePanel extends JPanel {

    private final BoardLogic boardLogic = new BoardLogic();
    private final JButton[][] buttons = new JButton[3][3];
    private final JLabel messageLabel;

    public GamePanel() {

        // Set layout and background
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(TicTacToeWindow.BACKGROUND);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Initialize message label
        messageLabel = new JLabel(boardLogic.currentPlayer());
        messageLabel.setFont(new Font("verdana", Font.BOLD, 20));
        messageLabel.setForeground(TicTacToeWindow.MESSAGE_COLOR);

        // Initialize restart button
        CustomButton restartButton = new CustomButton("▶️  Restart", true);
        restartButton.setFont(Font.BOLD, 16);
        restartButton.setColors(TicTacToeWindow.MESSAGE_COLOR, Color.BLACK);
        restartButton.padding(20, 15);
        restartButton.addActionListener(event -> resetGame());

        // Initialize grid panel
        JPanel gridPanel = new JPanel();
        gridPanel.setMaximumSize(grisSize());
        gridPanel.setLayout(new GridLayout(3, 3, TicTacToeWindow.BUTTON_GAP, TicTacToeWindow.BUTTON_GAP));
        gridPanel.setBackground(TicTacToeWindow.BORDER);
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                int X = x, Y = y;
                CustomButton button = new CustomButton(" ", false);
                button.setSize(TicTacToeWindow.BUTTON_SIZE);
                button.setFont("fantasy", Font.BOLD, 34);
                button.setColors(TicTacToeWindow.BACKGROUND, TicTacToeWindow.SYMBOL_COLOR);
                button.addActionListener(event -> onButtonClick(X, Y));
                buttons[x][y] = button;
                gridPanel.add(button);
            }
        }

        // Set alignment of components
        restartButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        gridPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add components to panel
        add(getCustomToolbar());
        add(Box.createVerticalStrut(25));
        add(messageLabel);
        add(Box.createVerticalStrut(50));
        add(gridPanel);
        add(Box.createVerticalStrut(45));
        add(restartButton);
    }

    /**
     * Creates and returns a custom toolbar for the game panel.
     *
     * @return The custom toolbar as a Component object.
     */
    private Component getCustomToolbar() {
        JLabel titleLabel = new JLabel("Tic Tac Toe");
        titleLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        titleLabel.setForeground(TicTacToeWindow.TEXT_COLOR);
        CustomButton button = new CustomButton("Exit", true);
        button.setFont(Font.BOLD, 14);
        button.setColors(TicTacToeWindow.BORDER, TicTacToeWindow.TEXT_COLOR);
        button.padding(10, 8);
        button.addActionListener(event -> System.exit(0));
        button.setAlignmentX(Component.RIGHT_ALIGNMENT);

        Box horizontalBox = Box.createHorizontalBox();
        horizontalBox.add(titleLabel);
        horizontalBox.add(Box.createHorizontalGlue());
        horizontalBox.add(button);
        return horizontalBox;
    }

    /**
     * Resets the game to its initial state.
     */
    private void resetGame() {
        boardLogic.reset();
        for (JButton[] row : buttons) {
            for (JButton button : row) {
                button.setText(" ");
            }
        }
        messageLabel.setText(boardLogic.currentPlayer());
    }

    /**
     * This method handles the click event of a button on the game board.
     * It calls the makeMove method of the BoardLogic class and updates the message label and the button text accordingly.
     *
     * @param x The row index of the clicked button.
     * @param y The column index of the clicked button.
     */
    public void onButtonClick(int x, int y) {
        String previous = boardLogic.currentPlayer();
        String message;
        switch (boardLogic.makeMove(x, y)) {
            case MOVE: message = boardLogic.currentPlayer(); break;
            case WINNER: message = String.format("<-- %s wins -->", previous); break;
            case TIE: message = "<-- Game over -->"; break;
            default: message = null;
        };
        if (message != null) {
            messageLabel.setText(message);
            buttons[x][y].setText(previous);
        }
    }

    /**
     * This method calculates and returns the size of the game board based on the button size and gap size.
     *
     * @return The size of the game board as a Dimension object.
     */
    private Dimension grisSize() {
        int size = TicTacToeWindow.BUTTON_SIZE * 3 + TicTacToeWindow.BUTTON_GAP * 3;
        return new Dimension(size, size);
    }
}