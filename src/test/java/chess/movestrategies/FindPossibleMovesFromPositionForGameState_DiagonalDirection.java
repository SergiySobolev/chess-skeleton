package chess.movestrategies;


import chess.GameState;
import chess.Player;
import chess.Position;
import chess.pieces.Knight;
import chess.pieces.Pawn;
import org.junit.Test;

import java.util.Collection;
import java.util.Collections;

import static chess.matchers.SamePositions.samePositions;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.junit.Assert.assertThat;

public class FindPossibleMovesFromPositionForGameState_DiagonalDirection {
    @Test
    public void startE5_emptyBoard_distance2() throws Exception {
        MovementStrategy movementStrategy = new DirectionMovementStrategy((byte) 2, Collections.singletonList(Direction.DIAGONAL));
        Collection<Position> possibleMoves = movementStrategy.findPossibleMovesFromPositionForGameState(new Position("e5"), new GameState());
        assertThat(possibleMoves, hasSize(8));
        assertThat(possibleMoves, samePositions(asList("d6", "c7", "d4", "c3", "f6", "g7", "f4", "g3")));
    }

    @Test
    public void startA1_emptyBoard_distance8() throws Exception {
        MovementStrategy movementStrategy = new DirectionMovementStrategy((byte) 8, Collections.singletonList(Direction.DIAGONAL));
        Collection<Position> possibleMoves = movementStrategy.findPossibleMovesFromPositionForGameState(new Position("a1"), new GameState());
        assertThat(possibleMoves, hasSize(7));
        assertThat(possibleMoves, samePositions(asList("b2", "c3", "d4", "e5", "f6", "g7", "h8")));
    }

    @Test
    public void startE5_obstacleD4D6F4F6_distance2() throws Exception {
        MovementStrategy movementStrategy = new DirectionMovementStrategy((byte) 2, Collections.singletonList(Direction.DIAGONAL));
        GameState gameState = new GameState();
        gameState.placePiece(new Pawn(Player.White), new Position("d4"));
        gameState.placePiece(new Pawn(Player.White), new Position("d6"));
        gameState.placePiece(new Knight(Player.White), new Position("f4"));
        gameState.placePiece(new Knight(Player.White), new Position("f6"));
        Collection<Position> possibleMoves = movementStrategy.findPossibleMovesFromPositionForGameState(new Position("e5"), gameState);
        assertThat(possibleMoves, empty());
    }
}
