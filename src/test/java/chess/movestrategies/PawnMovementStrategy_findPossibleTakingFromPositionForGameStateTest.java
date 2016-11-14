package chess.movestrategies;

import chess.GameState;
import chess.Player;
import chess.Position;
import chess.Take;
import chess.pieces.*;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.junit.Assert.assertThat;

public class PawnMovementStrategy_findPossibleTakingFromPositionForGameStateTest {
    private MovementStrategy whiteMovementStrategy = new PawnMovementStrategy(Player.White);
    private MovementStrategy blackMovementStrategy = new PawnMovementStrategy(Player.Black);

    @Test
    public void startWhiteE2_emptyBoard_noPossibleTaking() {
        Collection<Take> possibleMoves = whiteMovementStrategy.findPossibleTakingFromPositionForGameState(new Position("e2"), new GameState(), Player.White);
        assertThat(possibleMoves, empty());
    }

    @Test
    public void startA3_enemyOnB4_1take() {
        GameState gameState = new GameState();
        Pawn attacker = new Pawn(Player.White);
        Position attackerPosition = new Position("a3");
        gameState.placePiece(attacker, attackerPosition);
        Queen victim = new Queen(Player.Black);
        Position victimPosition = new Position("b4");
        gameState.placePiece(victim, victimPosition);
        Collection<Take> possibleMoves = whiteMovementStrategy.findPossibleTakingFromPositionForGameState(attackerPosition, gameState, Player.White);
        assertThat(possibleMoves, hasSize(1));
        assertThat(possibleMoves, contains(new Take(attacker, attackerPosition, victim, victimPosition, Collections.emptyList())));
    }

    @Test
    public void startE4_enemyOnD5_friendOnF5_1take() {
        GameState gameState = new GameState();
        Pawn attacker = new Pawn(Player.White);
        Position attackerPosition = new Position("e4");
        gameState.placePiece(attacker, attackerPosition);
        Queen victim = new Queen(Player.Black);
        Position victimPosition = new Position("d5");
        gameState.placePiece(victim, victimPosition);
        Queen friend = new Queen(Player.White);
        Position friendPosition = new Position("F5");
        gameState.placePiece(friend, friendPosition);
        Collection<Take> possibleMoves = whiteMovementStrategy.findPossibleTakingFromPositionForGameState(attackerPosition, gameState, Player.White);
        assertThat(possibleMoves, hasSize(1));
        assertThat(possibleMoves, contains(new Take(attacker, attackerPosition, victim, victimPosition, Collections.emptyList())));
    }

    @Test
    public void startF6_friendOnE7AndG7_noTake() {
        GameState gameState = new GameState();
        Pawn attacker = new Pawn(Player.White);
        Position attackerPosition = new Position("f6");
        gameState.placePiece(attacker, attackerPosition);
        Queen friend1 = new Queen(Player.White);
        Position friendPosition1 = new Position("e7");
        gameState.placePiece(friend1, friendPosition1);
        Queen friend2 = new Queen(Player.White);
        Position friendPosition2 = new Position("g7");
        gameState.placePiece(friend2, friendPosition2);
        Collection<Take> possibleMoves = whiteMovementStrategy.findPossibleTakingFromPositionForGameState(attackerPosition, gameState, Player.White);
        assertThat(possibleMoves, hasSize(0));
    }

    @Test
    public void black_startH6_enemyOnG5_1take() {
        GameState gameState = new GameState();
        Pawn attacker = new Pawn(Player.Black);
        Position attackerPosition = new Position("h6");
        gameState.placePiece(attacker, attackerPosition);
        Queen victim = new Queen(Player.White);
        Position victimPosition = new Position("g5");
        gameState.placePiece(victim, victimPosition);
        Collection<Take> possibleMoves = blackMovementStrategy.findPossibleTakingFromPositionForGameState(attackerPosition, gameState, Player.Black);
        assertThat(possibleMoves, hasSize(1));
        assertThat(possibleMoves, contains(new Take(attacker, attackerPosition, victim, victimPosition, Collections.emptyList())));
    }

    @Test
    public void black_startE7_enemyOnd6f6_1take() {
        GameState gameState = new GameState();
        Pawn attacker = new Pawn(Player.Black);
        Position attackerPosition = new Position("e7");
        gameState.placePiece(attacker, attackerPosition);
        Queen victim1 = new Queen(Player.White);
        Position victimPosition1 = new Position("d6");
        gameState.placePiece(victim1, victimPosition1);
        Queen victim2 = new Queen(Player.White);
        Position victimPosition2 = new Position("f6");
        gameState.placePiece(victim2, victimPosition2);
        Collection<Take> possibleMoves = blackMovementStrategy.findPossibleTakingFromPositionForGameState(attackerPosition, gameState, Player.Black);
        assertThat(possibleMoves, hasSize(2));
        assertThat(possibleMoves, contains(
                new Take(attacker, attackerPosition, victim1, victimPosition1, Collections.emptyList()),
                new Take(attacker, attackerPosition, victim2, victimPosition2, Collections.emptyList())
                ));
    }

}
