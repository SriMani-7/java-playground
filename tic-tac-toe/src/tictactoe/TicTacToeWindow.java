package tictactoe;

import javax.swing.JFrame;

import java.awt.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 * The TicTacToeWindow class represents the main entry point of the Tic Tac Toe game.
 * It creates a JFrame with a GamePanel that contains the game board and user interface.
 * The game board is a 3x3 grid of buttons that the player can click to make their moves,
 * and the user interface displays the current player and game status.
 * The class also defines various constants that are used throughout the game,
 * such as the size of the buttons, the background and text colors, and the border color.
 */
public class TicTacToeWindow {

    public static final int BUTTON_SIZE = 90, BUTTON_GAP = 3;
    public static final Color BACKGROUND = new Color(0x283C4F);
    public static final Color BORDER = new Color(0x097DC9);
    public static final Color TEXT_COLOR = new Color(0xDDE8E9);
    public static final Color SYMBOL_COLOR = new Color(0xFDFDFD);
    public static final Color MESSAGE_COLOR = new Color(0x3FE4C0);

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("Tic Tac Toe");
        frame.setUndecorated(true);
        frame.add(new GamePanel());
        frame.setResizable(false);
        frame.setSize(500, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}