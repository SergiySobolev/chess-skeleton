package chess;

import chess.pieces.Pawn;
import chess.pieces.Piece;
import chess.pieces.Queen;
import chess.pieces.Rook;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static junit.framework.Assert.*;

/**
 * Basic unit tests for the GameState class
 */
public class GameStateTest {

    private GameState state;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
        state = new GameState();
    }

    @Test
    public void testStartsEmpty() {
        // Make sure all the positions are empty
        for (char col = Position.MIN_COLUMN; col <= Position.MAX_COLUMN; col++) {
            for (int row = Position.MIN_ROW; row <= Position.MAX_ROW; row++) {
                assertNull("All pieces should be empty", state.getPieceAt(String.valueOf(col) + row));
            }
        }
    }

    @Test
    public void testInitialGame() {
        // Start the game
        state.reset();

        // White should be the first player
        Player current = state.getCurrentPlayer();
        assertEquals("The initial player should be White", Player.White, current);

        // Spot check a few pieces
        Piece whiteRook = state.getPieceAt("a1");
        assertTrue("A rook should be at a1", whiteRook instanceof Rook);
        assertEquals("The rook at a1 should be owned by White", Player.White, whiteRook.getOwner());


        Piece blackQueen = state.getPieceAt("d8");
        assertTrue("A queen should be at d8", blackQueen instanceof Queen);
        assertEquals("The queen at d8 should be owned by Black", Player.Black, blackQueen.getOwner());
    }

    @Test
    public void move_startPositionPawnFromE2toE4_ok() {
        state.reset();
        state.move("e2", "e4");
        assertNull(state.getPieceAt("e2"));
        assertEquals(state.getPieceAt("e4"), new Pawn(Player.White));
    }

    @Test
    public void move_rookFromA1ToA2_IllegalArgumentNotReachable() {
        state.reset();
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("To position isn't reachable: a2");
        state.move("a1", "a2");
    }

    @Test
    public void move_unExistingPieceOnA3_IllegalArgumentNotReachable() {
        state.reset();
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("No piece on from position: a3");
        state.move("a3", "c8");
    }
}
