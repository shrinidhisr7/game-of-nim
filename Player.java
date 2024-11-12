
public class Player {

    private String name;
    private int score;

    public Player(String name) {
        this.name = name;
        this.score = 0; // Initial score is set to 0
    }

    public String getName() {
        return name;
    }

    public void updateScore(int points) {
        score += points;  // Adds the points to the player's score
    }

    public int getScore() {
        return score;  // Returns the player's current score
    }
}
