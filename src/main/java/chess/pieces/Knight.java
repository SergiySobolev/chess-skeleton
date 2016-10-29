package chess.pieces;

import chess.Player;
import chess.movestrategies.DirectionMovementStrategy;
import chess.movestrategies.MovementStrategy;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static chess.movestrategies.Direction.DIAGONAL;
import static chess.movestrategies.Direction.HORIZONTAL_VERTICAL;
import static chess.movestrategies.Direction.L_LIKE;

/**
 * The Knight class
 */
public class Knight extends Piece {
    public Knight(Player owner) {
        super(owner);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'n';
    }

    @Override
    protected Collection<MovementStrategy> initializeMovementStrategies() {
        return Collections.singletonList(new DirectionMovementStrategy(1, L_LIKE));
    }
}
