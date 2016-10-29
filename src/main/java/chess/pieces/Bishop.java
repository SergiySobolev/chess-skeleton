package chess.pieces;

import chess.Player;
import chess.movestrategies.DirectionMovementStrategy;
import chess.movestrategies.MovementStrategy;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static chess.movestrategies.Direction.DIAGONAL;
import static java.util.Collections.singletonList;

/**
 * The 'Bishop' class
 */
public class Bishop extends Piece {
    public Bishop(Player owner) {
        super(owner);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'b';
    }

    @Override
    protected Collection<MovementStrategy> initializeMovementStrategies() {
        return singletonList(new DirectionMovementStrategy(7, DIAGONAL));
    }
}
