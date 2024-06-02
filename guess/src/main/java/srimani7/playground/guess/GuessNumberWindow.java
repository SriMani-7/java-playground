package srimani7.playground.guess;

import javax.swing.*;

/**
 * A GUI window for the "Guess the Number" game.
 * <p>
 * Allows the user to input guesses and displays the number of attempts and game status.
 */
public class GuessNumberWindow extends JFrame {
    private final GuessPanel guessPanel;

    /**
     * Constructs a GuessNumberWindow object with a title, a GuessPanel, and default size and location.
     */
    public GuessNumberWindow() {
        setTitle("Guess the Number");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        guessPanel = new GuessPanel();

        add(guessPanel);

        setSize(400, 500);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Launches the GuessNumberWindow application.
     * @param args an array of command-line arguments for the application.
     */
    public static void main(String[] args) {
        new GuessNumberWindow();
    }
}
