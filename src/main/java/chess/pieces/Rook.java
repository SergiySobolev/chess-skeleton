package chess.pieces;

import chess.Player;
import chess.movestrategies.DirectionMovementStrategy;
import chess.movestrategies.MovementStrategy;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static chess.movestrategies.Direction.DIAGONAL;
import static chess.movestrategies.Direction.HORIZONTAL_VERTICAL;
import static java.util.Collections.singletonList;

/**
 * The 'Rook' class
 */
public class Rook extends Piece {

    public Rook(Player owner) {
        super(owner);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'r';
    }

    @Override
    protected Collection<MovementStrategy> initializeMovementStrategies() {
        return singletonList(new DirectionMovementStrategy(7, HORIZONTAL_VERTICAL));
    }
}
