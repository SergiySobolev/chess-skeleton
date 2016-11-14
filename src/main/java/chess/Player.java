package chess;

/**
 * Which side of the board is being played
 */
public enum Player {
    White, Black;
    public static Player oppositePlayer(Player p) {
        return p == White ? Black : White;
    }

}
