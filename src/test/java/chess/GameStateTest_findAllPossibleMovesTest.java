package chess;

import chess.pieces.Bishop;
import chess.pieces.Knight;
import chess.pieces.Pawn;
import chess.pieces.Rook;
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

    @Test
    public void whiteKnightC1_blackKnightD3() {
        GameState gs = new GameState();
        gs.placePiece(new Knight(Player.White), "c1");
        gs.placePiece(new Knight(Player.Black), "d3");
        assertThat(gs.findAllPossibleMovesForBlackPlayer(), sameMoves(Arrays.asList(
                "Nd3 - b2",
                "Nd3 - e5",
                "Nd3 - b4",
                "Nd3 - c5",
                "Nd3 - f2",
                "Nd3 - e1",
                "Nd3 - f4"
        )));
        assertThat(gs.findAllPossibleMovesForWhitePlayer(), sameMoves(Arrays.asList(
                "nc1 - e2",
                "nc1 - a2",
                "nc1 - b3"
        )));
    }

    @Test
    public void allPawnsOnStartPositions() {
        GameState gs = new GameState();
        gs.placePiece(new Pawn(Player.White), "a2");
        gs.placePiece(new Pawn(Player.White), "b2");
        gs.placePiece(new Pawn(Player.White), "c2");
        gs.placePiece(new Pawn(Player.White), "d2");
        gs.placePiece(new Pawn(Player.White), "e2");
        gs.placePiece(new Pawn(Player.White), "f2");
        gs.placePiece(new Pawn(Player.White), "g2");
        gs.placePiece(new Pawn(Player.White), "h2");

        gs.placePiece(new Pawn(Player.Black), "a7");
        gs.placePiece(new Pawn(Player.Black), "b7");
        gs.placePiece(new Pawn(Player.Black), "c7");
        gs.placePiece(new Pawn(Player.Black), "d7");
        gs.placePiece(new Pawn(Player.Black), "e7");
        gs.placePiece(new Pawn(Player.Black), "f7");
        gs.placePiece(new Pawn(Player.Black), "g7");
        gs.placePiece(new Pawn(Player.Black), "h7");
        
        assertThat(gs.findAllPossibleMovesForWhitePlayer(), sameMoves(Arrays.asList(
                "pa2 - a3",
                "pa2 - a4",
                "pb2 - b3",
                "pb2 - b4",
                "pc2 - c3",
                "pc2 - c4",
                "pd2 - d3",
                "pd2 - d4",
                "pe2 - e3",
                "pe2 - e4",
                "pf2 - f3",
                "pf2 - f4",
                "pg2 - g3",
                "pg2 - g4",
                "ph2 - h3",
                "ph2 - h4"
        )));
        assertThat(gs.findAllPossibleMovesForBlackPlayer(), sameMoves(Arrays.asList(
                "Pa7 - a6",
                "Pa7 - a5",
                "Pb7 - b6",
                "Pb7 - b5",
                "Pc7 - c6",
                "Pc7 - c5",
                "Pd7 - d6",
                "Pd7 - d5",
                "Pe7 - e6",
                "Pe7 - e5",
                "Pf7 - f6",
                "Pf7 - f5",
                "Pg7 - g6",
                "Pg7 - g5",
                "Ph7 - h6",
                "Ph7 - h5"
        )));
    }

    @Test
    public void allPawnsBlocked() {
        GameState gs = new GameState();
        gs.placePiece(new Pawn(Player.White), "a4");
        gs.placePiece(new Pawn(Player.White), "b4");
        gs.placePiece(new Pawn(Player.White), "c4");
        gs.placePiece(new Pawn(Player.White), "d4");
        gs.placePiece(new Pawn(Player.White), "e4");
        gs.placePiece(new Pawn(Player.White), "f4");
        gs.placePiece(new Pawn(Player.White), "g4");
        gs.placePiece(new Pawn(Player.White), "h4");

        gs.placePiece(new Pawn(Player.Black), "a5");
        gs.placePiece(new Pawn(Player.Black), "b5");
        gs.placePiece(new Pawn(Player.Black), "c5");
        gs.placePiece(new Pawn(Player.Black), "d5");
        gs.placePiece(new Pawn(Player.Black), "e5");
        gs.placePiece(new Pawn(Player.Black), "f5");
        gs.placePiece(new Pawn(Player.Black), "g5");
        gs.placePiece(new Pawn(Player.Black), "h5");

        assertThat(gs.findAllPossibleMovesForWhitePlayer(), empty());
        assertThat(gs.findAllPossibleMovesForBlackPlayer(), empty());
    }

    @Test
    public void whiteRookOnA1BlockedByKnights() {
        GameState gs = new GameState();
        gs.placePiece(new Rook(Player.White), "a1");
        gs.placePiece(new Knight(Player.Black), "a2");
        gs.placePiece(new Knight(Player.Black), "b1");
        gs.placePiece(new Knight(Player.Black), "b2");

        assertThat(gs.findAllPossibleMovesForWhitePlayer(), empty());
        assertThat(gs.findAllPossibleMovesForBlackPlayer(), sameMoves(Arrays.asList(
                "Na2 - b4",
                "Na2 - c3",
                "Na2 - c1",
                "Nb2 - a4",
                "Nb2 - c4",
                "Nb2 - d3",
                "Nb2 - d1",
                "Nb1 - a3",
                "Nb1 - c3",
                "Nb1 - d2"
        )));
    }
}
