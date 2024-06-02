package srimani7.playground.tic;

import java.util.Arrays;

/**
 * The BoardLogic class represents the logic behind the Tic Tac Toe game board. It keeps track of the current state of the game
 * and determines if a move is valid or not. It also determines the winner of the game or if it is a tie.
 */
public class BoardLogic {
    private boolean isXTurn = true;
    private final int[][] board;
    private boolean isCompleted = false;

    /**
     * Constructs a new instance of BoardLogic with a new empty game board.
     */
    public BoardLogic() {
        board = new int[3][3];
    }

    /**
     * Resets the game board to its initial state, where all cells are empty and X is the first player.
     */
    public void reset() {
        for (int[] row : board) {
            Arrays.fill(row, 0);
        }
        isXTurn = true;
        isCompleted = false;
    }

    /**
     * Makes a move on the game board at the specified coordinates (x, y).
     *
     * @param x the row index of the cell to be filled (0 <= x < 3)
     * @param y the column index of the cell to be filled (0 <= y < 3)
     * @return the State of the game after the move is made (MOVE, WINNER, TIE, or INVALID)
     */
    public State makeMove(int x, int y) {
        if ((x >= 0 && x < 3) && (y >= 0 && y < 3)) {
            if (!isCompleted && board[x][y] == 0) {
                isXTurn = !isXTurn;
                board[x][y] = isXTurn ? 1 : 2;
                isCompleted = checkWinner();
                return isCompleted ? State.WINNER : (isGameTie() ? State.TIE : State.MOVE);
            }
        }
        return State.INVALID;
    }

    /**
     * Returns the current player of the game.
     *
     * @return the current player ("X" or "O")
     */
    public String currentPlayer() {
        return isXTurn ? "X" : "O";
    }

    /**
     * Checks if there is a winner in the game.
     *
     * @return true if there is a winner, false otherwise
     */
    public boolean checkWinner() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != 0 && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return true;
            }
            if (board[0][i] != 0 && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                return true;
            }
        }
        if (board[0][0] != 0 && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return true;
        }
        return board[0][2] != 0 && board[0][2] == board[1][1] && board[1][1] == board[2][0];
    }

    /**
     * Checks if the game is a tie (no empty cells left on the board).
     *
     * @return true if the game is a tie, false otherwise
     */
    public boolean isGameTie() {
        for (int[] row : board) {
            for (int cell : row) {
                if (cell == 0) return false;
            }
        }
        isCompleted = true;
        return true;
    }

    /**
     * The possible states that the game can be in after a move is made.
     */
    enum State {
        /**
         * The game is ongoing and the turn has been switched.
         */
        MOVE,
        /**
         * The game has been won by a current player.
         */
        WINNER,
        /**
         * The game has ended in a tie.
         */
        TIE,
        /**
         * The move is invalid and cannot be made.
         */
        INVALID
    }
}