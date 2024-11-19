public class Player {

    private String name;  // The player's name
    private int score;    // The player's current score

    // Constructor to initialize a player with a name and initial score of 0
    public Player(String name) {
        this.name = name;  // Set the player's name
        this.score = 0;    // Initialize the score to 0 at the start of the game
    }

    // Getter method to retrieve the player's name
    public String getName() {
        return name;  // Return the player's name
    }

    // Method to update the player's score by adding points
    public void updateScore(int points) {
        score += points;  // Add the specified points to the current score
    }

    // Getter method to retrieve the player's current score
    public int getScore() {
        return score;  // Return the player's current score
    }
}
