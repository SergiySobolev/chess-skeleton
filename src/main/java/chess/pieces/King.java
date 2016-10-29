package chess.pieces;

import chess.Player;
import chess.movestrategies.DirectionMovementStrategy;
import chess.movestrategies.MovementStrategy;

import java.util.Arrays;
import java.util.Collection;

import static chess.movestrategies.Direction.DIAGONAL;
import static chess.movestrategies.Direction.HORIZONTAL_VERTICAL;
import static java.util.Collections.singletonList;

/**
 * The King class
 */
public class King extends Piece {
    public King(Player owner) {
        super(owner);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'k';
    }

    @Override
    protected Collection<MovementStrategy> initializeMovementStrategies() {
        return Arrays.asList(new DirectionMovementStrategy(1, DIAGONAL), new DirectionMovementStrategy(1, HORIZONTAL_VERTICAL));
    }
}
