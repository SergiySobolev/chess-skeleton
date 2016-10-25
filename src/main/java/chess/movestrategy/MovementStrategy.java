package chess.movestrategy;


import chess.GameState;
import chess.Position;

import java.util.Collection;

/**
 * Interface for moving strategies of chess pieces
 */
public interface MovementStrategy {

    /**
     * @param startPosition position for start moving
     * @param gameState current gameState of board where moves try to be found
     * @return collection of possible moves for provided start position and game state
     */
    Collection<Position> findPossibleMovesFromPositionForGameState(Position startPosition, GameState gameState);
}
