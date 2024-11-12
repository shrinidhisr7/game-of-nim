
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Game {

    private Player player1;
    private Player player2;
    private Pile pile;
    private Player currentPlayer;
    private boolean turnExpired = false;

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        chooseFirstPlayer();
        initializePile();
    }

    private void initializePile() {
        int initialSize = 10 + (int) (Math.random() * 41); // Random size between 10 and 50
        pile = new Pile(initialSize);
        System.out.println("Starting pile size: " + initialSize);
    }

    private void chooseFirstPlayer() {
        currentPlayer = Math.random() > 0.5 ? player1 : player2;
        System.out.println(currentPlayer.getName() + " goes first.");
    }

    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        while (!isGameOver()) {
            System.out.println("Pile size: " + pile.getPieces());
            int maxRemoval = pile.getMaxRemoval();
            System.out.println(currentPlayer.getName() + ", it's your turn.");
            System.out.println("You have 5 seconds to make a move.");
            System.out.println("You can remove between 1 and " + maxRemoval + " pieces.");

            // Start the 5-second timer for the player's turn
            startTurnTimer();

            // Get the player's move
            int piecesToRemove = 0;
            while (piecesToRemove < 1 || piecesToRemove > maxRemoval) {
                piecesToRemove = getPlayerMove(scanner);
                if (turnExpired) {
                    System.out.println("Time's up! " + currentPlayer.getName() + " missed the turn.");
                    switchTurn();  // Pass the turn
                    break;
                }
            }

            // If turn expired, continue to the next turn
            if (turnExpired) {
                continue;
            }

            // Make the move if valid
            pile.removePieces(piecesToRemove);
            if (!isGameOver()) {
                switchTurn();
            }
        }
        announceWinner();
    }

    private boolean isGameOver() {
        return pile.getPieces() == 0;
    }

    private void switchTurn() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    private void announceWinner() {
        Player winner = (currentPlayer == player1) ? player2 : player1;
        System.out.println("Congratulations, " + winner.getName() + "! You win.");
        winner.updateScore(1);
        System.out.println("Scores: " + player1.getName() + " - " + player1.getScore() + ", " + player2.getName() + " - " + player2.getScore());
    }

    public void playAgain() {
        System.out.println("Do you want to play again? (yes/no)");
        Scanner scanner = new Scanner(System.in);
        String response = scanner.nextLine();
        if (response.equalsIgnoreCase("yes")) {
            initializePile();  // Reset pile size for new game
            chooseFirstPlayer();  // Randomly select the first player again
            startGame();  // Restart the game loop
        } else {
            System.out.println("Thanks for playing!");
        }
    }

    private int getPlayerMove(Scanner scanner) {
        int playerMove = -1;
        long startTime = System.currentTimeMillis();

        // Wait for player's input within the 5-second window
        while (System.currentTimeMillis() - startTime < 5000) {
            if (scanner.hasNextInt()) {
                playerMove = scanner.nextInt();
                break;
            }
        }

        return playerMove;
    }

    private void startTurnTimer() {
        turnExpired = false;
        // Create a TimerTask that will set turnExpired to true after 5 seconds
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                turnExpired = true;
            }
        };

        // Start a timer for 5 seconds
        Timer timer = new Timer();
        timer.schedule(task, 5000);
    }
}
