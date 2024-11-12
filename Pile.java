
public class Pile {

    private int pieces;

    public Pile(int initialSize) {
        this.pieces = initialSize;
    }

    public void removePieces(int count) {
        if (count <= pieces && count > 0) {
            pieces -= count;
        } else {
            System.out.println("Invalid move. Try again.");
        }
    }

    public int getPieces() {
        return pieces;
    }

    public int getMaxRemoval() {
        return Math.max(1, pieces / 2); // Players can remove between 1 and half the pile size
    }
}
