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
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class DirectionMovementStrategy_FindPossibleMovesFromPositionForGameState_HorizontalVerticalDirectionTest {

    @Test
    public void startE4_emptyBoard_distance2() {
        MovementStrategy movementStrategy = new DirectionMovementStrategy((byte) 2, Collections.singletonList(Direction.HORIZONTAL_VERTICAL));
        Collection<Position> possibleMoves = movementStrategy.findPossibleMovesFromPositionForGameState(new Position("e4"), new GameState());
        assertThat(possibleMoves, samePositions(asList("e5", "e6", "e3", "e2", "f4", "g4", "d4", "c4")));
    }

    @Test
    public void startG2_emptyBoard_distance4()  {
        MovementStrategy movementStrategy = new DirectionMovementStrategy((byte) 4, Collections.singletonList(Direction.HORIZONTAL_VERTICAL));
        Collection<Position> possibleMoves = movementStrategy.findPossibleMovesFromPositionForGameState(new Position("g2"), new GameState());
        assertThat(possibleMoves, samePositions(asList("g1", "g3", "g4", "g5", "g6", "h2", "f2", "e2", "d2", "c2")));
    }

    @Test
    public void startA1_emptyBoard_distance9()  {
        MovementStrategy movementStrategy = new DirectionMovementStrategy((byte) 9, Collections.singletonList(Direction.HORIZONTAL_VERTICAL));
        Collection<Position> possibleMoves = movementStrategy.findPossibleMovesFromPositionForGameState(new Position("a1"), new GameState());
        assertThat(possibleMoves, samePositions(asList("b1", "c1", "d1", "e1", "f1", "g1", "h1", "a2", "a3", "a4", "a5", "a6", "a7", "a8")));
    }

    @Test
    public void startE5_obstacleE7_distance2() {
        MovementStrategy movementStrategy = new DirectionMovementStrategy((byte) 2, Collections.singletonList(Direction.HORIZONTAL_VERTICAL));
        GameState gameState = new GameState();
        gameState.placePiece(new Pawn(Player.Black), new Position("e7"));
        Collection<Position> possibleMoves = movementStrategy.findPossibleMovesFromPositionForGameState(new Position("e5"), gameState);
        assertThat(possibleMoves, samePositions(asList("e4", "e3", "e6", "d5", "c5", "f5", "g5")));
    }

    @Test
    public void startA1_obstacleA2B1B2_distance5_noMoves()  {
        MovementStrategy movementStrategy = new DirectionMovementStrategy((byte) 5, Collections.singletonList(Direction.HORIZONTAL_VERTICAL));
        GameState gameState = new GameState();
        gameState.placePiece(new Pawn(Player.White), new Position("a2"));
        gameState.placePiece(new Pawn(Player.White), new Position("b2"));
        gameState.placePiece(new Knight(Player.White), new Position("b1"));
        Collection<Position> possibleMoves = movementStrategy.findPossibleMovesFromPositionForGameState(new Position("a1"), gameState);
        assertThat(possibleMoves, empty());
    }



}