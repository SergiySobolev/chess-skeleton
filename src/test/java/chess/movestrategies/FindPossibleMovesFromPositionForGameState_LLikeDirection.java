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

public class FindPossibleMovesFromPositionForGameState_LLikeDirection {
    @Test
    public void startE5_emptyBoard_distance1() throws Exception {
        MovementStrategy movementStrategy = new DirectionMovementStrategy((byte) 1, Collections.singletonList(Direction.L_LIKE));
        Collection<Position> possibleMoves = movementStrategy.findPossibleMovesFromPositionForGameState(new Position("e5"), new GameState());
        assertThat(possibleMoves, samePositions(asList("f7", "g6", "g4", "f3", "d3", "c4", "c6", "d7")));
    }

    @Test
    public void startA1_emptyBoard_distance1() throws Exception {
        MovementStrategy movementStrategy = new DirectionMovementStrategy((byte) 1, Collections.singletonList(Direction.L_LIKE));
        Collection<Position> possibleMoves = movementStrategy.findPossibleMovesFromPositionForGameState(new Position("a1"), new GameState());
        assertThat(possibleMoves, samePositions(asList("c2", "b3")));
    }

    @Test
    public void startE1_emptyBoard_distance1() throws Exception {
        MovementStrategy movementStrategy = new DirectionMovementStrategy((byte) 1, Collections.singletonList(Direction.L_LIKE));
        Collection<Position> possibleMoves = movementStrategy.findPossibleMovesFromPositionForGameState(new Position("e1"), new GameState());
        assertThat(possibleMoves, samePositions(asList("f3", "g2", "d3", "c2")));
    }

    @Test
    public void startG8_obstaclesH6E7_distance1() throws Exception {
        MovementStrategy movementStrategy = new DirectionMovementStrategy((byte) 1, Collections.singletonList(Direction.L_LIKE));
        GameState gameState = new GameState();
        gameState.placePiece(new Pawn(Player.White), new Position("h6"));
        gameState.placePiece(new Pawn(Player.White), new Position("e7"));
        Collection<Position> possibleMoves = movementStrategy.findPossibleMovesFromPositionForGameState(new Position("g8"), gameState);
        assertThat(possibleMoves, samePositions(asList("f6")));
    }


}
