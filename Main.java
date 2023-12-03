import java.awt.Font;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) { //runs loop until the baord is full or ap player wins
        StdDraw.setScale(-1,4);

        // Create an instance of the TicTacToeGame class
        TicTacToeGame game = new TicTacToeGame(3);

        game.drawBoard(); // Calls the drawBoard method

        while (!game.isWinner()) { // Check if there is isn't a winner
            if (game.getCurrentPlayer() == '0') {
                game.userMove(); // If it's user's turn call userMove method
            } else {
                game.computerMove(); // If it's  computer's turn call computerMove method
            }
            StdDraw.pause(400); // make drawing of xs a little slower
        }

        Font font = new Font("Arial", Font.BOLD, 60);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setFont(font);
        StdDraw.text(1.5, 1.5, game.result); // print who won

        System.out.print("\nPlay Again? (y/n) "); // ask user if they want to play again
        Scanner input = new Scanner(System.in);
        String playAgain = input.nextLine();

        while (playAgain.equals("y")){
            StdDraw.clear();
            game.drawBoard();

            game.reset();

            while (!game.isWinner()) { // Check if there is isn't a winner
                if (game.getCurrentPlayer() == '0') {
                    game.userMove(); // If it's user's turn call userMove method
                } else {
                    game.computerMove(); // If it's  computer's turn call computerMove method
                }
                StdDraw.pause(400); // make drawing of xs a little slower
            }

            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setFont(font);
            StdDraw.text(1.5, 1.5, game.result); // print who won

            System.out.print("\nPlay Again? (y/n) "); // ask question again
            playAgain = input.nextLine();
        }

        StdDraw.clear();
        game.drawBoard();

        StdDraw.setFont(font); // print winner of game if the user is done
        if (game.compWins > game.playerWins){
            StdDraw.text(1.5, 1.5, "X WON!");
        } else if (game.compWins < game.playerWins){
            StdDraw.text(1.5, 1.5, "0 WON!");
        } else{
            StdDraw.text(1.5, 1.5, "IT WAS A TIE!");
        }

    }
}



