package chess.movestrategies;


import chess.GameState;
import chess.Player;
import chess.Position;
import chess.Take;

import java.util.Collection;
import java.util.Optional;

/**
 * Interface for moving strategies of chess pieces
 */
public interface MovementStrategy {

    /**
     * Returns collections of possible moving from start position for game state
     * @param startPosition position for start moving
     * @param gameState current gameState of board where moves try to be found
     * @return collection of possible moves for provided start position and game state
     */
    Collection<Position> findPossibleMovesFromPositionForGameState(Position startPosition, GameState gameState);

    /**
     * Returns collections of possible taking from start position for game state
     * @param startPosition position for start moving
     * @param gameState current gameState of board where moves try to be found
     * @param currentPlayer current players
     * @return collection of possible taking for provided start position and game state
     */
    Collection<Take> findPossibleTakingFromPositionForGameState(Position startPosition, GameState gameState, Player currentPlayer);
}
