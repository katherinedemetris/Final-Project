import java.util.Random;

public class TicTacToeGame {
    private final int BOARD_SIZE;
    private char[][] board;
    private char currentPlayer;

    // Constructor
    public TicTacToeGame(int boardSize) { //init game with specific baord size and drawing of board, and that the first playr is O
        this.BOARD_SIZE = boardSize;
        this.board = new char[BOARD_SIZE][BOARD_SIZE];
        this.currentPlayer = '0';
    }

    // Accessor methods
    public char[][] getBoard() { //return current version of the board
        return board;
    }
    public void setBoard(char [][] board) {
        this.board = board;
    }

    public char getCurrentPlayer() { //return symbol X or O of current player
        return currentPlayer;
    }

    public void setCurrentPlayer(char currentplayer) {
        this.currentPlayer = currentplayer;
    }



    // Mutator method
    public void switchPlayer() { //switch between X and O
        if (currentPlayer == '0') {
            currentPlayer = 'X';
        } else {
            currentPlayer = '0';
        }
    }

    // make game board using a loop
    public void drawBoard() { //draw current state of baord
        StdDraw.setPenRadius(0.01);
        StdDraw.setPenColor(StdDraw.BLACK);

        //  horizontal lines
        for (int i = 0; i < BOARD_SIZE; i++) {
            StdDraw.line(0.0, i, BOARD_SIZE, i);
        }

        //  vertical lines
        for (int i = 0; i < BOARD_SIZE; i++) {
            StdDraw.line(i, 0.0, i, BOARD_SIZE);
        }

        StdDraw.show();
    }

    // draw xs and os
    public void drawShape(int row, int col) {
        StdDraw.setPenRadius(0.02);

        // the O
        if (currentPlayer == '0') {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.circle(col + 0.5, BOARD_SIZE - row - 0.5, 0.4);
            board[row][col] = '0';

            // the X
        } else {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line(col + 0.1, BOARD_SIZE - row - 0.1, col + 0.9, BOARD_SIZE - row - 0.9);
            StdDraw.line(col + 0.1, BOARD_SIZE - row - 0.9, col + 0.9, BOARD_SIZE - row - 0.1);
            board[row][col] = 'X';
        }

        StdDraw.show();
    }

    // Check if a box is empty
    public boolean isEmpty(int row, int col) {
        return board[row][col] == '\0';  // using '\0' as the space character
    }

    // Check if board is full
    public boolean isBoardFull() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] == '\0') {
                    return false;
                }
            }
        }
        return true;
    }

    // users move using input from mouse
    public void userMove() {
        if (StdDraw.isMousePressed()) {
            double x = StdDraw.mouseX();
            double y = StdDraw.mouseY();

            int row = (int) (3 - y);
            int col = (int) x;

            if (isEmpty(row, col)) {
                drawShape(row, col);
                switchPlayer();
            }
        }
    }

    // computers moveds by generating random position until an empyt one is found on th e board
    public void computerMove() {
        Random random = new Random();

        int row, col;
        do {
            row = random.nextInt(BOARD_SIZE);
            col = random.nextInt(BOARD_SIZE);
        } while (!isEmpty(row, col));

        drawShape(row, col);
        switchPlayer();
    }
}


