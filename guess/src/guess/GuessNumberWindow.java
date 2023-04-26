package guess;

import javax.swing.*;

public class GuessNumberWindow extends JFrame {
    private GuessPanel guessPanel;

    public GuessNumberWindow() {
        setTitle("Guess the Number");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        guessPanel = new GuessPanel();

        add(guessPanel);

        setSize(400, 500);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new GuessNumberWindow();
    }
}
