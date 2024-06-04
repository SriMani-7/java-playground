package srimani7.playground.guess;

import javax.swing.*;

/**
 * A GUI window for the "Guess the Number" game.
 * <p>
 * Allows the user to input guesses and displays the number of attempts and game status.
 */
public class GuessNumberWindow {

    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setTitle("Guess the Number");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GuessPanel guessPanel = new GuessPanel();

        jFrame.add(guessPanel);

        jFrame.setSize(400, 500);
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }
}
