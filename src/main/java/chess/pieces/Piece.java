package chess.pieces;

import chess.Player;
import chess.movestrategies.MovementStrategy;

import java.util.Collection;

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

    protected Collection<MovementStrategy> getMovementStrategies() {
        return movementStrategies;
    }
}
