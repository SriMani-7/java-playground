package guess;

import java.util.Random;

public class GuessGameLogic {
    private final Random random;
    private int score;

    public int getRandomNumber() {
        return randomNumber;
    }

    private int randomNumber;

    GuessGameLogic() {
        random = new Random();
    }

    public void reset() {
        randomNumber = random.nextInt(1, 100);
        score = 100;
        System.out.println("Generated random number " + randomNumber);
    }

    public int getScore() {
        return score;
    }

    public boolean isGameOver() {
        return score <= 0;
    }

    public int getAttempts() {
        return score / 10;
    }

    public void reduceScore() {
        score -= 10;
    }

    public boolean isCorrect(int other) {
        return randomNumber == other;
    }

    public String getInstruction(int other) {
        if (other > randomNumber) return other+" is too high";
        else if (other < randomNumber) return other+" is too small";
        else return "";
    }
}