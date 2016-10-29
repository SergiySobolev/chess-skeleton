package chess.pieces;

import chess.Player;
import chess.movestrategies.DirectionMovementStrategy;
import chess.movestrategies.MovementStrategy;

import java.util.Arrays;
import java.util.Collection;

import static chess.movestrategies.Direction.DIAGONAL;
import static chess.movestrategies.Direction.HORIZONTAL_VERTICAL;

/**
 * The Queen
 */
public class Queen extends Piece{
    public Queen(Player owner) {
        super(owner);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'q';
    }

    @Override
    protected Collection<MovementStrategy> initializeMovementStrategies() {
        return Arrays.asList(new DirectionMovementStrategy(7, DIAGONAL), new DirectionMovementStrategy(7, HORIZONTAL_VERTICAL));
    }
}
