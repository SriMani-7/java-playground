package tictactoe;

import java.util.Arrays;

public class BoardLogic {
    private boolean isXTurn = true;
    private final int[][] board;
    private boolean isCompleted = false;

    public BoardLogic() {
        board = new int[3][3];
    }

    public void reset() {
        for (int[] row : board) {
            Arrays.fill(row, 0);
        }
        isXTurn = true;
        isCompleted = false;
    }

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

    public String currentPlayer() {
        return isXTurn ? "X" : "O";
    }

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

    public boolean isGameTie() {
        for (int[] row : board) {
            for (int cell : row) {
                if (cell == 0) return false;
            }
        }
        isCompleted = true;
        return true;
    }

    enum State {MOVE, WINNER, TIE, INVALID}
}