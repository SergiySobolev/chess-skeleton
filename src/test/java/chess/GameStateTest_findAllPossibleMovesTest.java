package chess;

import chess.pieces.Bishop;
import org.junit.Test;

import java.util.Arrays;

import static chess.matchers.SameMoves.sameMoves;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsEmptyCollection.empty;

public class GameStateTest_findAllPossibleMovesTest {

    @Test
    public void singleBishop_startPositionE5() {
        GameState gs = new GameState();
        gs.placePiece(new Bishop(Player.White), "e5");
        assertThat(gs.findAllPossibleMovesForBlackPlayer(), empty());
        assertThat(gs.findAllPossibleMovesForWhitePlayer(), sameMoves(Arrays.asList(
                "be5 - f6",
                "be5 - b8",
                "be5 - g3",
                "be5 - c3",
                "be5 - h2",
                "be5 - g7",
                "be5 - f4",
                "be5 - a1",
                "be5 - d6",
                "be5 - d4",
                "be5 - h8",
                "be5 - c7",
                "be5 - b2"
        )));
    }
}
