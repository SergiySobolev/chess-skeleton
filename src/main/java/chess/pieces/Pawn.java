package chess.pieces;

import chess.Player;
import chess.movestrategies.MovementStrategy;
import chess.movestrategies.PawnMovementStrategy;

import java.util.Collection;

import static java.util.Collections.singletonList;

/**
 * The Pawn
 */
public class Pawn extends Piece {
    public Pawn(Player owner) {
        super(owner);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'p';
    }

    @Override
    protected Collection<MovementStrategy> initializeMovementStrategies() {
        return singletonList(new PawnMovementStrategy(getOwner()));
    }
}
