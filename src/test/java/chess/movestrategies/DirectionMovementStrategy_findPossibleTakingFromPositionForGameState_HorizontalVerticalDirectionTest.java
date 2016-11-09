package chess.movestrategies;

import chess.GameState;
import chess.Player;
import chess.Position;
import chess.Take;
import chess.pieces.Bishop;
import chess.pieces.Rook;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.junit.Assert.assertThat;

public class DirectionMovementStrategy_findPossibleTakingFromPositionForGameState_HorizontalVerticalDirectionTest {
    private MovementStrategy movementStrategy = new DirectionMovementStrategy((byte) 7, Collections.singletonList(Direction.HORIZONTAL_VERTICAL));

    @Test
    public void startE5_emptyBoard_noPossibleTaking() {
        Collection<Take> possibleMoves = movementStrategy.findPossibleTakingFromPositionForGameState(new Position("e5"), new GameState(), Player.White);
        assertThat(possibleMoves, empty());
    }

    @Test
    public void startE5_enemyOnE7_1take() {
        GameState gameState = new GameState();
        Rook attacker = new Rook(Player.White);
        Position attackerPosition = new Position("e5");
        gameState.placePiece(attacker, attackerPosition);
        Bishop victim = new Bishop(Player.Black);
        Position victimPosition = new Position("e7");
        gameState.placePiece(victim, victimPosition);
        Collection<Take> possibleMoves = movementStrategy.findPossibleTakingFromPositionForGameState(attackerPosition, gameState, Player.White);
        assertThat(possibleMoves, hasSize(1));
        assertThat(possibleMoves, contains(new Take(attacker, attackerPosition, victim, victimPosition, Collections.singletonList(new Position("e6")))));
    }

    @Test
    public void startE5_enemiesOnE8A5_2take() {
        GameState gameState = new GameState();
        Rook attacker = new Rook(Player.White);
        Position attackerPosition = new Position("e5");
        gameState.placePiece(attacker, attackerPosition);
        Bishop victim1 = new Bishop(Player.Black);
        Position victim1Position = new Position("e8");
        gameState.placePiece(victim1, victim1Position);
        Rook victim2 = new Rook(Player.Black);
        Position victim2Position = new Position("a5");
        gameState.placePiece(victim2, victim2Position);
        Collection<Take> possibleMoves = movementStrategy.findPossibleTakingFromPositionForGameState(attackerPosition, gameState, Player.White);
        assertThat(possibleMoves, hasSize(2));
        assertThat(possibleMoves, containsInAnyOrder(
                new Take(attacker, attackerPosition,  victim1, victim1Position,  Arrays.asList(new Position("e6"), new Position("e7"))),
                new Take(attacker, attackerPosition, victim2, victim2Position, Arrays.asList(new Position("d5"), new Position("c5"), new Position("b5")))
        ));
    }
}
