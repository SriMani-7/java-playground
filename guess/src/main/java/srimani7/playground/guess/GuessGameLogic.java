package srimani7.playground.guess;

import java.util.Random;

/**
 * The GuessGameLogic class contains the game logic for the Guess the Number game.
 */
public class GuessGameLogic {
    private final Random random;
    private int attempts;
    private int generatedNumber;

    GuessGameLogic() {
        random = new Random();
    }

    public int getGeneratedNumber() {
        return generatedNumber;
    }

    public void reset() {
        generatedNumber = random.nextInt(100) + 1;
        attempts = 10;
        System.out.println("Generated random number " + generatedNumber); // for debugging
    }

    public boolean isGameOver() {
        return attempts == 0;
    }

    /**
     * This method returns remaining attempts
     * */
    public int getAttempts() {
        return attempts;
    }

    /**
     * Compares user's guess to generated number.
     *
     * - Calculates difference (positive: too high, negative: too low, zero: correct).
     * - Decrements attempts on wrong guess.
     *
     * @param number User's guess.
     * @return Difference between guess and generated number.
     */
    public int performGuess(int number) {
        int diff = number - generatedNumber;
        if (diff != 0) attempts -= 1;
        return diff;
    }
}