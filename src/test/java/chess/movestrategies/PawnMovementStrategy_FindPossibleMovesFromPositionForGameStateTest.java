package chess.movestrategies;

import chess.GameState;
import chess.Player;
import chess.Position;
import chess.pieces.Pawn;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Collection;

import static chess.matchers.SamePositions.samePositions;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hamcrest.Matchers.empty;
import static org.junit.Assert.assertThat;

public class PawnMovementStrategy_FindPossibleMovesFromPositionForGameStateTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void blackPlayerStartPositionNoObstacles_2moves() throws Exception {
        MovementStrategy movementStrategy = new PawnMovementStrategy(Player.Black);
        Collection<Position> possibleMoves = movementStrategy.findPossibleMovesFromPositionForGameState(new Position("e7"), new GameState());
        assertThat(possibleMoves, samePositions(asList("e6", "e5")));
    }

    @Test
    public void blackPlayerNoObstacles_1moves() throws Exception {
        MovementStrategy movementStrategy = new PawnMovementStrategy(Player.Black);
        Collection<Position> possibleMoves = movementStrategy.findPossibleMovesFromPositionForGameState(new Position("e6"), new GameState());
        assertThat(possibleMoves, samePositions(singletonList("e5")));
    }

    @Test
    public void blackPlayerIllegalLine_IllegalArgumentException() throws Exception {
        MovementStrategy movementStrategy = new PawnMovementStrategy(Player.Black);
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("8 line is illegal line for pawn");
        movementStrategy.findPossibleMovesFromPositionForGameState(new Position("A8"), new GameState());
    }

    @Test
    public void whitePlayerObstacleBefore_noMoves() throws Exception {
        MovementStrategy movementStrategy = new PawnMovementStrategy(Player.White);
        GameState gameState = new GameState();
        gameState.placePiece(new Pawn(Player.White), new Position("f6"));
        Collection<Position> possibleMoves = movementStrategy.findPossibleMovesFromPositionForGameState(new Position("f5"), gameState);
        assertThat(possibleMoves, empty());
    }


}
