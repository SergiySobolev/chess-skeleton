package chess;


import chess.pieces.Piece;

import java.util.Objects;

public class Move {
    private Piece piece;
    private Position from;
    private Position to;

    public Move(Piece piece, Position from, Position to) {
        this.piece = piece;
        this.from = from;
        this.to = to;
    }

    public Piece getPiece() {
        return piece;
    }

    public Position getFrom() {
        return from;
    }

    public Position getTo() {
        return to;
    }

    @Override
    public String toString() {
        return getPiece().getIdentifier() + from.toString() + " - " + to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Move move = (Move) o;

        if (!piece.equals(move.getPiece())) return false;

        if (!from.equals(move.getFrom())) return false;

        if (!to.equals(move.getTo())) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(piece, from, to);
    }
}
