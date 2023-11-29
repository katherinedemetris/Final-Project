import java.awt.Font;

public class Main {

    public static void main(String[] args) { //runs loop until the baord is full or ap player wins
        StdDraw.setScale(-1,4);

        // Create an instance of the TicTacToeGame class
        TicTacToeGame game = new TicTacToeGame(3);

        game.drawBoard(); // Calls the drawBoard method



        // Check if there is already a shape in the box, runs while the board is not full
        while (!game.isBoardFull()) {
            if (game.getCurrentPlayer() == '0') {
                game.userMove(); // If it's user's turn call userMove method
            } else {
                game.computerMove(); // If it's  computer's turn call computerMove method
            }
            StdDraw.pause(500); // make drawing of xs a little slower
        }





    }
}



