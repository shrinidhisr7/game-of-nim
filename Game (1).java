import java.util.Scanner;

public class Game {

    private Player firstPlayer;  // Player 1
    private Player secondPlayer; // Player 2
    private Pile pile;           // The pile from which players remove tokens
    private Player activePlayer; // The player currently taking their turn

    // Constructor to initialize the game with two players
    public Game(Player player1, Player player2) {
        this.firstPlayer = player1;  // Set the first player
        this.secondPlayer = player2; // Set the second player
        setStartingPlayer();         // Decide who starts the game
    }

    // Randomly select the starting player and ask them to guess a number
    private void setStartingPlayer() {
        activePlayer = Math.random() > 0.5 ? firstPlayer : secondPlayer; // Randomly choose between player1 and player2

        // Generate a random correct number between 1 and 5 for the starting player's guess
        int correctAnswer = (int) (Math.random() * 5) + 1;
        promptNumberGuess(correctAnswer); // Ask the starting player to guess the number
    }

    // Prompt the starting player to guess a number and set the pile size accordingly
    private void promptNumberGuess(int correctNumber) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(activePlayer.getName() + ", try to guess a number between 1 and 5:");

        // Read the player's guess
        int guessedNumber = scanner.nextInt();

        // Check if the guess is correct
        if (guessedNumber == correctNumber) {
            System.out.println("Hooray, you were right! Set the pile size: ");
            setPlayerChosenPile(scanner); // Allow the player to choose a pile size
        } else {
            System.out.println("Aw man...incorrect guess. A random pile size will be used.");
            initializePile(); // Set a random pile size if the guess is incorrect
        }
    }

    // Initialize the pile with a random size between 10 and 30
    private void initializePile() {
        int randomSize = (int) (Math.random() * 21) + 10; // Random number between 10 and 30
        pile = new Pile(randomSize); // Create a new pile with the random size
        System.out.println("Your pile will start with " + randomSize + " tokens.");
    }

    // Allow the player to select a pile size between 10 and 30
    private void setPlayerChosenPile(Scanner scanner) {
        System.out.println("Select a number between 10-30, that will be your pile size: ");
        int chosenSize = scanner.nextInt();

        // Keep asking for a valid pile size if the input is out of range
        while (chosenSize < 10 || chosenSize > 30) {
            System.out.println("Please choose a pile size between 10 and 30:");
            chosenSize = scanner.nextInt();
        }

        // Set the pile with the chosen size
        pile = new Pile(chosenSize);
        System.out.println("Yay! The pile starts with " + chosenSize + " tokens.");
    }

    // Main game loop: players take turns removing tokens until the pile is empty
    public void playGame() {
        Scanner scanner = new Scanner(System.in);

        // Continue playing as long as the pile is not empty
        while (!isPileEmpty()) {
            System.out.println("Current token pile size: " + pile.getPieces()); // Display the current size of the pile
            int maxAllowed = pile.getMaxRemoval(); // Calculate the maximum number of tokens a player can remove
            System.out.println(activePlayer.getName() + ", you may remove a maximum of: " + maxAllowed + " tokens.");

            int piecesToRemove;
            do {
                piecesToRemove = getPlayerInput(scanner); // Get the player's input for how many tokens to remove
            } while (piecesToRemove < 1 || piecesToRemove > maxAllowed); // Ensure the input is within the valid range

            pile.removePieces(piecesToRemove); // Remove the tokens from the pile

            // If the pile is not empty, switch to the next player
            if (!isPileEmpty()) {
                switchActivePlayer();
            }
        }

        // Once the pile is empty, declare the winner
        displayWinner();
    }

    // Check if the pile is empty (i.e., the game has ended)
    private boolean isPileEmpty() {
        return pile.getPieces() == 0; // The pile is empty when the number of pieces is 0
    }

    // Switch the active player (toggle between player 1 and player 2)
    private void switchActivePlayer() {
        activePlayer = (activePlayer == firstPlayer) ? secondPlayer : firstPlayer; // Toggle the active player
    }

    // Display the winner of the game (the last player to take a valid turn)
    private void displayWinner() {
        Player winningPlayer = (activePlayer == firstPlayer) ? secondPlayer : firstPlayer; // The other player is the winner
        System.out.println("Hooray, " + winningPlayer.getName() + "! You've won this round.");
    }

    // Prompt the players to decide if they want to play again after the game ends
    public boolean promptReplay() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Would you like to play again? Enter Y/N: ");
        String response = scanner.nextLine(); // Get the player's response

        return response.equalsIgnoreCase("Y"); // Return true if the player wants to play again
    }

    // Get the player's input for how many tokens to remove
    private int getPlayerInput(Scanner scanner) {
        if (scanner.hasNextInt()) {
            return scanner.nextInt(); // If the input is a valid integer, return it
        }
        return -1; // Return -1 for invalid input (e.g., if the player enters something that isn't an integer)
    }
}
