import java.util.*;
import java.awt.Color;

public class Player {
    private List<GamePiece> pieces;
    private Color color;

    public Player(int position, Color color) {
        this.color = color;
        this.pieces = new ArrayList<GamePiece>();
        for (int i = 0; i < 4; i++) {
            this.pieces.add(new GamePiece(position, color));
        }
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }

    public void AddSafeSlots(Color color) {
        for (GamePiece piece : this.pieces) {
            piece.AddSafeSlots(color);
        }
    }

    public List<GamePiece> getPieces() {
        return this.pieces;
    }

    public void movePiece(int piece, int x) {
        this.pieces.get(piece).move(x);
    }

    public void setPieces(List<GamePiece> pieces) {
        this.pieces = pieces;
    }

    public boolean isFinished() {
        int count = 0;
        for (GamePiece piece : this.pieces) {
            if (piece.isHome()) {
                count++;
            }
        }
        return count == 4;
    }
}