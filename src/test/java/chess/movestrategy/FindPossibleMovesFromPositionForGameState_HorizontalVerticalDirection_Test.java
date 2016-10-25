package chess.movestrategy;

import chess.GameState;
import chess.Player;
import chess.Position;
import chess.pieces.Knight;
import chess.pieces.Pawn;
import org.junit.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class FindPossibleMovesFromPositionForGameState_HorizontalVerticalDirection_Test {

    @Test
    public void startE5_emptyBoard_distance2() throws Exception {
        MovementStrategy movementStrategy = new DirectionMovementStrategy((byte) 2, Collections.singletonList(Direction.HORIZONTAL_VERTICAL));
        Collection<Position> possibleMoves = movementStrategy.findPossibleMovesFromPositionForGameState(new Position("e4"), new GameState());
        assertThat(possibleMoves, hasSize(8));
        assertThat(possibleMoves, containsInAnyOrder(new Position("e5"), new Position("e6"), new Position("e3"), new Position("e2"),
                new Position("f4"), new Position("g4"), new Position("d4"), new Position("c4")));
    }

    @Test
    public void startG2_emptyBoard_distance4() throws Exception {
        MovementStrategy movementStrategy = new DirectionMovementStrategy((byte) 4, Collections.singletonList(Direction.HORIZONTAL_VERTICAL));
        Collection<Position> possibleMoves = movementStrategy.findPossibleMovesFromPositionForGameState(new Position("g2"), new GameState());
        assertThat(possibleMoves, hasSize(10));
        assertThat(possibleMoves, containsInAnyOrder(new Position("g1"), new Position("g3"), new Position("g4"), new Position("g5"), new Position("g6"),
                new Position("h2"), new Position("f2"), new Position("e2"), new Position("d2"), new Position("c2")));
    }

    @Test
    public void startA1_emptyBoard_distance9() throws Exception {
        MovementStrategy movementStrategy = new DirectionMovementStrategy((byte) 9, Collections.singletonList(Direction.HORIZONTAL_VERTICAL));
        Collection<Position> possibleMoves = movementStrategy.findPossibleMovesFromPositionForGameState(new Position("a1"), new GameState());
        assertThat(possibleMoves, hasSize(14));
        assertThat(possibleMoves, containsInAnyOrder(new Position("b1"), new Position("c1"), new Position("d1"),
                new Position("e1"), new Position("f1"), new Position("g1"), new Position("h1"),
                new Position("a2"), new Position("a3"), new Position("a4"), new Position("a5"), new Position("a6"), new Position("a7"), new Position("a8")));
    }

    @Test
    public void startE5_obstacleE7_distance2() throws Exception {
        MovementStrategy movementStrategy = new DirectionMovementStrategy((byte) 2, Collections.singletonList(Direction.HORIZONTAL_VERTICAL));
        GameState gameState = new GameState();
        gameState.placePiece(new Pawn(Player.Black), new Position("e7"));
        Collection<Position> possibleMoves = movementStrategy.findPossibleMovesFromPositionForGameState(new Position("e5"), gameState);
        assertThat(possibleMoves, hasSize(7));
        assertThat(possibleMoves, containsInAnyOrder(new Position("e4"), new Position("e3"), new Position("e6"),
                new Position("d5"), new Position("c5"), new Position("f5"), new Position("g5")));
    }

    @Test
    public void startA1_obstacleA2B1B2_distance5_noMoves() throws Exception {
        MovementStrategy movementStrategy = new DirectionMovementStrategy((byte) 5, Collections.singletonList(Direction.HORIZONTAL_VERTICAL));
        GameState gameState = new GameState();
        gameState.placePiece(new Pawn(Player.White), new Position("a2"));
        gameState.placePiece(new Pawn(Player.White), new Position("b2"));
        gameState.placePiece(new Knight(Player.White), new Position("b1"));
        Collection<Position> possibleMoves = movementStrategy.findPossibleMovesFromPositionForGameState(new Position("a1"), gameState);
        assertThat(possibleMoves, empty());
    }



}