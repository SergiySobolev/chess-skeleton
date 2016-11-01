package chess.pieces;

import chess.Move;
import chess.Player;
import chess.movestrategies.MovementStrategy;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * A base class for chess pieces
 */
public abstract class Piece {
    private final Collection<MovementStrategy> movementStrategies;
    private final Player owner;

    protected Piece(Player owner) {
        this.owner = owner;
        movementStrategies = initializeMovementStrategies();
    }

    public char getIdentifier() {
        char id = getIdentifyingCharacter();
        if (owner.equals(Player.White)) {
            return Character.toLowerCase(id);
        } else {
            return Character.toUpperCase(id);
        }
    }

    public Player getOwner() {
        return owner;
    }

    protected abstract char getIdentifyingCharacter();

    protected abstract Collection<MovementStrategy> initializeMovementStrategies();

    /**
     * @return all movements strategies of piece
     */
    public Collection<MovementStrategy> getMovementStrategies() {
        return movementStrategies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Piece piece = (Piece) o;

        if (piece.getIdentifier() != this.getIdentifier()) return false;

        if (!owner.equals(piece.getOwner())) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdentifier(), owner);
    }
}
