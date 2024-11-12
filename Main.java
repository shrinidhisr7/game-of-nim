
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter name for Player 1: ");
        String player1Name = scanner.nextLine();
        Player player1 = new Player(player1Name);

        System.out.print("Enter name for Player 2: ");
        String player2Name = scanner.nextLine();
        Player player2 = new Player(player2Name);

        Game game = new Game(player1,  player2);

        // Start the game and allow players to play again
        boolean playAgain = true;
        while (playAgain) {
            game.startGame();  // Start the game
            game.playAgain();  // Ask if they want to play again
        }

        System.out.println("Thank you for playing!");
    }
}


