package tictactoe;

import javax.swing.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class TicTacToeWindow {



    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("Tic Tac Toe");
        frame.add(new GamePanel());
        frame.setResizable(false);
        frame.setSize(500, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
