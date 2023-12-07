import java.util.*;
import java.awt.Color;

public class Player {
    private List<GamePiece> pieces;
    private Color color;

    public Player(Color color, int position) {
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

    public List<GamePiece> getPieces() {
        return this.pieces;
    }

    public void movePiece(int piece, int x) {
        System.out.println(piece);
        this.pieces.get(piece).move(x);
    }
}