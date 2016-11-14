package chess;

import chess.pieces.*;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;

import static chess.matchers.SameMoves.sameMoves;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;

public class GameStateTest_findAllPossibleTakesTest {


    @Test
    public void whiteRookOnA1BishopF3_enemiesOnA8E4() {
        GameState gs = new GameState();
        Rook piece1 = new Rook(Player.White);
        String a1 = "a1";
        gs.placePiece(piece1, a1);
        Bishop piece2 = new Bishop(Player.White);
        String f3 = "f3";
        gs.placePiece(piece2, f3);
        Knight victim1 = new Knight(Player.Black);
        String a8 = "a8";
        gs.placePiece(victim1, a8);
        Queen victim2 = new Queen(Player.Black);
        String e4 = "e4";
        gs.placePiece(victim2, e4);

        Collection<Take> allPossibleMovesForWhitePlayer = gs.findAllPossibleTakesForWhitePlayer();
        assertThat(allPossibleMovesForWhitePlayer, hasSize(2));
    }

    @Test
    public void scandinavianDefenceFirstTwoMoves() {
        GameState gs = new GameState();
        gs.reset();
        gs.move("e2", "e4");
        gs.switchPlayers();
        gs.move("d7", "d5");
        gs.switchPlayers();

        Collection<Take> allPossibleTakesForWhitePlayer = gs.findAllPossibleTakesForWhitePlayer();
        assertThat(allPossibleTakesForWhitePlayer, hasSize(1));
        gs.switchPlayers();
        Collection<Take> allPossibleTakesForBlackPlayer = gs.findAllPossibleTakesForBlackPlayer();
        assertThat(allPossibleTakesForBlackPlayer, hasSize(1));
        gs.switchPlayers();
        gs.move("b1", "c3");
        gs.switchPlayers();
        gs.move("g8", "f6");
        gs.switchPlayers();
        assertThat(gs.findAllPossibleTakesForWhitePlayer(), hasSize(2));
        gs.move("d1", "f3");
        gs.switchPlayers();
        assertThat(gs.findAllPossibleTakesForBlackPlayer(), hasSize(2));
        gs.move("b8", "c6");
        gs.switchPlayers();
        assertThat(gs.findAllPossibleTakesForWhitePlayer(), hasSize(3));

    }
}
