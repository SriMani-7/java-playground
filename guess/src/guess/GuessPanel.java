package guess;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static java.lang.Integer.parseInt;
import static javax.swing.BorderFactory.createEmptyBorder;
import static guess.SwingUtils.dimen;

class GuessPanel extends JPanel {
    private final JTextField inputTextField;
    private final JLabel attemptsLabel, instructionLabel;
    private final JButton submitButton, resetButton;

    private final GuessGameLogic gameLogic;

    public GuessPanel() {
        gameLogic = new GuessGameLogic();
        attemptsLabel = new JLabel();
        instructionLabel = new JLabel("Enter your guess");
        inputTextField = new JTextField();
        attemptsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton = new JButton("Submit");
        resetButton = new JButton("Reset");

        var box = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(box);
        setBorder(createEmptyBorder(10, 15, 20, 15));
        initUi();
        resetGame();
    }

    private void initUi() {

        JLabel titleLabel = new JLabel("Guess the Number\n 1 to 100");
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 20));

        Box horizontalBox = Box.createHorizontalBox();
        horizontalBox.add(resetButton);
        horizontalBox.add(Box.createHorizontalStrut(20));
        horizontalBox.add(submitButton);
        horizontalBox.setBorder(createEmptyBorder(15, 20, 20, 20));

        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        instructionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        inputTextField.setAlignmentX(Component.CENTER_ALIGNMENT);

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

        // Event listeners
        inputTextField.addKeyListener(new DigitKeyAdapter());
        inputTextField.addActionListener(this::performGuess);
        submitButton.addActionListener(this::performGuess);
        resetButton.addActionListener(e -> resetGame());

        theming(); // apply colors, font, alignment
    }

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

        inputTextField.setPreferredSize(dimen(200, 80));
        inputTextField.setMaximumSize(dimen(200, 80));
        inputTextField.setBorder(createEmptyBorder(20, 20, 20, 20));
        inputTextField.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
    }

    private void resetGame() {

        gameLogic.reset();

        resetButton.setText("Reset");
        attemptsLabel.setText(attemptsString());
        instructionLabel.setText("Enter your guess");
        inputTextField.setVisible(true);
        inputTextField.setText("");
        submitButton.setVisible(true);
        inputTextField.requestFocus();
        instructionLabel.setForeground(Color.GRAY);
    }

    private void gameOver() {
        attemptsLabel.setText("");
        inputTextField.setVisible(false);
        submitButton.setVisible(false);
        resetButton.setText("PLay again");
    }

    private void performGuess(ActionEvent e) {
        try {
            var number = parseInt(inputTextField.getText()); // throws exception for number formatting
            if (gameLogic.isCorrect(number)) {
                var inst = String.format("You won the game : %02d / 100", gameLogic.getScore());
                instructionLabel.setText(inst);
                instructionLabel.setForeground(Color.BLUE);
                gameOver();
            } else {
                gameLogic.reduceScore();
                if (gameLogic.isGameOver()) {
                    instructionLabel.setText("Correct guess is "+gameLogic.getRandomNumber());
                    gameOver();
                } else {
                    attemptsLabel.setText(attemptsString());
                    instructionLabel.setText(gameLogic.getInstruction(number));
                    instructionLabel.setForeground(Color.RED);
                }
            }
            inputTextField.setText("");
            inputTextField.requestFocus();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private String attemptsString() {
        return String.format("Attempts : %02d", gameLogic.getAttempts());
    }
}