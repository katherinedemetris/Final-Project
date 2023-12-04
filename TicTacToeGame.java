import java.awt.*;
import java.util.Random;

public class TicTacToeGame {
    private int BOARD_SIZE;
    private char[][] board;
    private char currentPlayer;

    public String result = "IT'S A TIE!";
    public int compWins = 0;
    public int playerWins = 0;
    public int gamesPlayed = 0;

    // Constructor
    public TicTacToeGame(int boardSize) { //init game with specific board size and drawing of board, and that the first playr is O
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
    public void drawBoard() { //draw current state of board
        StdDraw.setPenRadius(0.01);
        StdDraw.setPenColor(StdDraw.BLACK);


        //  horizontal lines
        for (int i = 0; i <= BOARD_SIZE; i ++) {
            StdDraw.line(0.0, i, BOARD_SIZE, i);
        }

        //  vertical lines
        for (int i = 0; i <= BOARD_SIZE; i ++) {
            StdDraw.line(i, 0.0, i, BOARD_SIZE);
        }

        // printing out the title and the score board on the screen
        scoreBoard();

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

    public void reset(){ // reset array
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '\0';
            }
        }
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

    public boolean isWinner() {
        int count = 0;

        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][0] != '\0') { // check the columns
                if (board[i][0] == '0') {
                    result = "0's WIN!";
                    playerWins++;
                    count = 1; // add one so I can see if it is a tie at the end
                } else {
                    result = "X's WIN!";
                    compWins++;
                    count = 1;
                }
                gamesPlayed++; // increase game count
                return true;
            }
        }

        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[0][i] == board[1][i] && board[0][i] == board[2][i] && board[0][i] != '\0') {  // check the rows
                if (board[0][i] == '0') {
                    result = "0's WIN!";
                    playerWins++;
                    count = 1;
                } else {
                    result = "X's WIN!";
                    compWins++;
                    count = 1;
                }
                gamesPlayed++;
                return true;
            }
        }

        if (((board[0][0] == board[1][1] && board[0][0] == board[2][2]) || (board[0][2] == board[1][1] && board[0][2] == board[2][0])) && board[1][1] != '\0')  { // check the diagonal
            if (board[1][1] == '0') {
                result = "0's WIN!";
                playerWins++;
                count = 1;
            } else {
                result = "X's WIN!";
                compWins++;
                count = 1;
            }
            gamesPlayed++;
            return true;
        }

        if (count != 1 && isBoardFull()){ // check if a tie
            result = "IT'S A TIE!";
            return true;
        }

        return false;
    }


    public void scoreBoard(){ // displaying the scoreboard and title

        Font font = new Font("Arial", Font.BOLD, 60);
        StdDraw.setFont(font);
        StdDraw.text(1.5, 3.5, "TIC TAC TOE"); // game title

        Font font2 = new Font("Arial", Font.BOLD, 15); // scoreboard title
        StdDraw.setFont(font2);
        StdDraw.text(-0.25, -0.25, "SCOREBOARD");

        Font font3 = new Font("Arial", Font.PLAIN, 10); // wins and games played
        StdDraw.setFont(font3);
        StdDraw.text(-0.35, -0.45, "Computer Wins: " + compWins);
        StdDraw.text(-0.35, -0.65, "Player Wins:      " + playerWins);
        StdDraw.text(-0.35, -0.85, "Games Played:  " + gamesPlayed);
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


