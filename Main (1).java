import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Create a scanner object to handle user input
        Scanner scanner = new Scanner(System.in);

        // Display a welcome message explaining the rules of the game
        System.out.println("Welcome! You are currently playing the game of nim. In Nim, two players (decide now, who player1 and player2 will be!) take turns removing tokens from a pile, aiming to avoid taking the last tokens. The first player is chosen randomly and can set the pile size by guessing a number correctly. On each turn, players remove 1 to half of the remaining tokens until the game ends. Good Luck! ");
        System.out.println(""); // Print an empty line for better readability

        // Define player names (can be customized or entered by the user)
        String player1Name = "player1";
        Player player1 = new Player(player1Name); // Create the first player with the name "player1"

        String player2Name = "player2";
        Player player2 = new Player(player2Name); // Create the second player with the name "player2"

        // Create a new game instance with the two players
        Game game = new Game(player1, player2);

        // Start the game and check if players want to play again after each game
        boolean playAgain = true; // A flag to control the game loop
        while (playAgain) {
            game.playGame(); // Start and run the game
            playAgain = game.promptReplay(); // Ask the players if they want to play again, update the flag accordingly
        }

        // Print a message when the player exits the game
        System.out.println("Thank you for playing!");

        // Close the scanner to free up resources
        scanner.close();
    }
}
