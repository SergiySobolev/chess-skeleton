package chess.movestrategies;

import chess.GameState;
import chess.Player;
import chess.Position;
import chess.Take;
import chess.pieces.Bishop;
import chess.pieces.Knight;
import chess.pieces.Queen;
import chess.pieces.Rook;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.junit.Assert.assertThat;

public class DirectionMovementStrategy_findPossibleTakingFromPositionForGameState_LLikeTest {
    private MovementStrategy movementStrategy = new DirectionMovementStrategy((byte) 1, Collections.singletonList(Direction.L_LIKE));

    @Test
    public void startE5_emptyBoard_noPossibleTaking() {
        Collection<Take> possibleMoves = movementStrategy.findPossibleTakingFromPositionForGameState(new Position("e5"), new GameState(), Player.White);
        assertThat(possibleMoves, empty());
    }

    @Test
    public void startE5_enemyOnD7_1take() {
        GameState gameState = new GameState();
        Knight attacker = new Knight(Player.White);
        Position attackerPosition = new Position("e5");
        gameState.placePiece(attacker, attackerPosition);
        Bishop victim = new Bishop(Player.Black);
        Position victimPosition = new Position("d7");
        gameState.placePiece(victim, victimPosition);
        Collection<Take> possibleMoves = movementStrategy.findPossibleTakingFromPositionForGameState(attackerPosition, gameState, Player.White);
        assertThat(possibleMoves, hasSize(1));
        assertThat(possibleMoves, contains(new Take(attacker, attackerPosition, victim, victimPosition, Collections.emptyList())));
    }

    @Test
    public void startE5_enemiesOnG6G4_2take() {
        GameState gameState = new GameState();
        Knight attacker = new Knight(Player.White);
        Position attackerPosition = new Position("e5");
        gameState.placePiece(attacker, attackerPosition);
        Bishop victim1 = new Bishop(Player.Black);
        Position victim1Position = new Position("g6");
        gameState.placePiece(victim1, victim1Position);
        Rook victim2 = new Rook(Player.Black);
        Position victim2Position = new Position("g4");
        gameState.placePiece(victim2, victim2Position);
        Collection<Take> possibleMoves = movementStrategy.findPossibleTakingFromPositionForGameState(attackerPosition, gameState, Player.White);
        assertThat(possibleMoves, hasSize(2));
        assertThat(possibleMoves, containsInAnyOrder(
                new Take(attacker, attackerPosition,  victim1, victim1Position, Collections.emptyList()),
                new Take(attacker, attackerPosition, victim2, victim2Position, Collections.emptyList())
        ));
    }

    @Test
    public void startE5_enemiesOnE8A5B5_2take_2cantBeTaken() {
        GameState gameState = new GameState();
        Rook attacker = new Rook(Player.White);
        Position attackerPosition = new Position("e5");
        gameState.placePiece(attacker, attackerPosition);
        Bishop victim1 = new Bishop(Player.Black);
        Position victim1Position = new Position("c4");
        gameState.placePiece(victim1, victim1Position);
        Rook victim2 = new Rook(Player.Black);
        Position victim2Position = new Position("c6");
        gameState.placePiece(victim2, victim2Position);
        Knight cantBeTaken1 = new Knight(Player.Black);
        gameState.placePiece(cantBeTaken1, new Position("g7"));
        Queen cantBeTaken2 = new Queen(Player.Black);
        gameState.placePiece(cantBeTaken2, new Position("e1"));
        Collection<Take> possibleMoves = movementStrategy.findPossibleTakingFromPositionForGameState(attackerPosition, gameState, Player.White);
        assertThat(possibleMoves, hasSize(2));
        assertThat(possibleMoves, containsInAnyOrder(
                new Take(attacker, attackerPosition,  victim1, victim1Position, Collections.emptyList()),
                new Take(attacker, attackerPosition, victim2, victim2Position, Collections.emptyList())
        ));
    }
}
