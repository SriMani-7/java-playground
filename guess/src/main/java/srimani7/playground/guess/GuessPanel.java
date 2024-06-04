package srimani7.playground.guess;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static java.lang.Integer.parseInt;
import static javax.swing.BorderFactory.createEmptyBorder;

/**
 * The GuessPanel class represents a Swing JPanel that implements a simple guessing game UI.
 * It displays a title, an instruction label, an input text field for entering guesses, a submit button,
 * and a reset button. It also displays remaining attempts and provides feedback
 * on the correctness of the guesses. The class uses a GuessGameLogic object to handle the game logic.
 */
class GuessPanel extends JPanel {
    private final JTextField inputTextField;
    private final JLabel attemptsLabel, instructionLabel;
    private final JButton submitButton, resetButton;

    private final GuessGameLogic gameLogic;

    /**
     * Constructs a new `GuessPanel` object.
     */
    public GuessPanel() {
        gameLogic = new GuessGameLogic();
        attemptsLabel = new JLabel();
        instructionLabel = new JLabel("Enter your guess");
        inputTextField = new JTextField();
        submitButton = new JButton("Submit");
        resetButton = new JButton("Reset");

        BoxLayout box = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(box);
        setBorder(createEmptyBorder(10, 15, 20, 15));
        initUi(); // Initialize the UI components
        resetGame(); // Reset the game state
    }

    /**
     * Initializes the UI components of the panel.
     */
    private void initUi() {

        JLabel titleLabel = new JLabel("Guess the Number\n 1 to 100");
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 20));

        // Create and configure a horizontal box for the reset and submit buttons
        Box horizontalBox = Box.createHorizontalBox();
        horizontalBox.add(resetButton);
        horizontalBox.add(Box.createHorizontalStrut(20));
        horizontalBox.add(submitButton);
        horizontalBox.setBorder(createEmptyBorder(15, 20, 20, 20));

        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        instructionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        inputTextField.setAlignmentX(Component.CENTER_ALIGNMENT);
        attemptsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add components to panel in order
        add(Box.createVerticalStrut(30));
        add(titleLabel);
        add(Box.createVerticalGlue()); // weight = 1
        add(attemptsLabel);
        add(Box.createVerticalStrut(20));
        add(instructionLabel);
        add(Box.createVerticalGlue()); // weight = 1
        add(inputTextField);
        add(Box.createVerticalStrut(20));
        add(horizontalBox);
        add(Box.createVerticalGlue()); // weight = 1

        // Add event listeners for input text field and buttons
        inputTextField.addActionListener(this::performGuess);
        submitButton.addActionListener(this::performGuess);
        resetButton.addActionListener(e -> resetGame());

        theming(); // Apply theming (colors, fonts, alignment) to UI components
    }

    /**
     * This method applies custom theming to the GUI components in the GuessPanel.
     */
    private void theming() {
        instructionLabel.setFont(new Font("Comic sans MS", Font.BOLD, 18));
        attemptsLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        submitButton.setFont(new Font("Arial", Font.PLAIN, 14));
        submitButton.setBackground(Color.DARK_GRAY);
        submitButton.setForeground(Color.WHITE);
        submitButton.setBorder(createEmptyBorder(10, 20, 10, 20));
        submitButton.setFocusPainted(false);

        resetButton.setFocusPainted(false);
        resetButton.setContentAreaFilled(false);
        resetButton.setFont(resetButton.getFont().deriveFont(Font.BOLD, 16f));
        resetButton.setBorderPainted(false);
        resetButton.setOpaque(true);

        Dimension textFieldSize = new Dimension(200, 80);
        inputTextField.setMaximumSize(textFieldSize);
        inputTextField.setBorder(createEmptyBorder(20, 20, 20, 20));
        inputTextField.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
    }

    /**
     * Resets the game by calling the reset method of the gameLogic object.
     * setting the ui components state.
     * making the input text field visible.
     */
    private void resetGame() {
        gameLogic.reset();

        resetButton.setText("Reset");
        updateAttemptLabel();
        instructionLabel.setText("Enter your guess");
        inputTextField.setVisible(true);
        inputTextField.setText("");
        submitButton.setVisible(true);
        inputTextField.requestFocus();
        instructionLabel.setForeground(Color.GRAY);
    }

    /**
     * This method is called when the user clicks the submit button to perform a guess.
     *
     * @param e ActionEvent
     */
    private void performGuess(ActionEvent e) {
        try {
            int number = parseInt(inputTextField.getText()); // throws exception for number formatting
            int diff = gameLogic.performGuess(number);
            String iText; // instructionLabel text based on guess
            Color iColor = Color.RED;

            if (gameLogic.isGameOver())
                iText = "Correct guess is " + gameLogic.getGeneratedNumber();
            else if (diff == 0) {
                iText = "You won the game";
                iColor = Color.BLUE;
            } else iText = String.format("you guess %d is %s", number, diff < 0 ? "low" : "high");

            if (gameLogic.isGameOver() || diff == 0) {
                inputTextField.setVisible(false);
                submitButton.setVisible(false);
                resetButton.setText("PLay again");
            }

            inputTextField.setText("");
            inputTextField.requestFocus();
            instructionLabel.setText(iText);
            instructionLabel.setForeground(iColor);
            updateAttemptLabel();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void updateAttemptLabel() {
        attemptsLabel.setText("Remaining attempts: " + gameLogic.getAttempts());
    }
}