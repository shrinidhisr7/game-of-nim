public class Pile {

    private int pieces; // The number of tokens in the pile

    // Constructor to initialize the pile with a given size
    public Pile(int initialSize) {
        this.pieces = initialSize; // Set the initial number of pieces in the pile
    }

    // Method to remove a specified number of pieces from the pile
    public void removePieces(int count) {
        // Ensure the count is valid (positive and does not exceed the current pile size)
        if (count <= pieces && count > 0) {
            pieces -= count; // Decrease the pile by the specified number of pieces
        } else {
            // If the move is invalid (e.g., removing more pieces than available), print an error message
            System.out.println("Invalid move. Try again.");
        }
    }

    // Getter method to retrieve the current number of pieces in the pile
    public int getPieces() {
        return pieces; // Return the current pile size
    }

    // Method to calculate the maximum number of pieces a player can remove (up to half the pile size)
    public int getMaxRemoval() {
        return Math.max(1, pieces / 2); // Players can remove between 1 and half of the current pile size
    }
}
