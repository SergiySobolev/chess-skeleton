package chess;


import chess.pieces.*;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static chess.Player.Black;
import static chess.Player.White;
import static junit.framework.TestCase.assertFalse;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.Assert.assertTrue;

public class GameStateTest_findAttacksOnPieceOnPositionTest {

    @Test
    public void enemiesRookA1A8_targetA5() {
        GameState gs = new GameState();
        Rook target = new Rook(Black);
        String a5 = "a5";
        gs.placePiece(target, a5);
        Rook offer1 = new Rook(White);
        String a1 = "a1";
        gs.placePiece(offer1, a1);
        Rook offer2 = new Rook(White);
        String a8 = "a8";
        gs.placePiece(offer2, a8);

        Collection<Take> allAttacks = gs.findAttacksOnPosition(new Position(a5));
        assertThat(allAttacks, hasSize(2));
        assertThat(allAttacks, contains(
                new Take(offer1, new Position(a1), target, new Position(a5), Arrays.asList(new Position("a2"), new Position("a3"), new Position("a4"))),
                new Take(offer2, new Position(a8), target, new Position(a5), Arrays.asList(new Position("a7"), new Position("a6")))
        ));
    }

    @Test
    public void enemiesRookA1A8BishopA7_targetA5() {
        GameState gs = new GameState();
        Rook target = new Rook(Black);
        String a5 = "a5";
        gs.placePiece(target, a5);
        Rook offer1 = new Rook(White);
        String a1 = "a1";
        gs.placePiece(offer1, a1);
        Rook offer2 = new Rook(White);
        String a8 = "a8";
        gs.placePiece(offer2, a8);
        Bishop cover = new Bishop(White);
        gs.placePiece(cover, "a7");

        Collection<Take> allAttacks = gs.findAttacksOnPosition(new Position(a5));
        assertThat(allAttacks, hasSize(1));
        assertThat(allAttacks, contains(
                new Take(offer1, new Position(a1), target, new Position(a5), Arrays.asList(new Position("a2"), new Position("a3"), new Position("a4")))
        ));
    }

    @Test
    public void enemiesRookA1A8BishopA7KnightB7_targetA5() {
        GameState gs = new GameState();
        Rook target = new Rook(Black);
        String a5 = "a5";
        gs.placePiece(target, a5);
        Rook offer1 = new Rook(White);
        String a1 = "a1";
        gs.placePiece(offer1, a1);
        Rook offer2 = new Rook(White);
        String a8 = "a8";
        gs.placePiece(offer2, a8);
        Bishop cover = new Bishop(White);
        gs.placePiece(cover, "a7");
        Knight offer3 = new Knight(White);
        String b7 = "b7";
        gs.placePiece(offer3, b7);

        Collection<Take> allAttacks = gs.findAttacksOnPosition(new Position(a5));
        assertThat(allAttacks, hasSize(2));
        assertThat(allAttacks, contains(
                new Take(offer1, new Position(a1), target, new Position(a5), Arrays.asList(new Position("a2"), new Position("a3"), new Position("a4"))),
                new Take(offer3, new Position(b7), target, new Position(a5), Collections.emptyList())
        ));
    }

    @Test
    public void findAttacksOnKing_blackKingOneE8_whiteQueenOnH5() {
        GameState gs = new GameState();
        King blackKing = new King(Black);
        Position blackKingPosition = new Position("e8");
        gs.placePiece(blackKing, blackKingPosition);
        Queen whiteQueen = new Queen(White);
        Position whiteQueenPosition = new Position("h5");
        gs.placePiece(whiteQueen, whiteQueenPosition);
        Collection<Take> attacksOnBlackKing = gs.findAttacksOnKing(Black);
        assertTrue(gs.hasKingSafeMoves(Black));
        assertThat(attacksOnBlackKing, hasSize(1));
        assertThat(attacksOnBlackKing, contains(
                new Take(whiteQueen, whiteQueenPosition, blackKing, blackKingPosition, Arrays.asList(
                        new Position("g6"), new Position("f7")
                ))
        ));
        gs.placePiece(new Pawn(Black), new Position("f7"));
        assertThat(gs.findAttacksOnKing(Black), hasSize(0));
        gs.placePiece(new Pawn(Black), new Position("e7"));
        Knight whiteKnight = new Knight(White);
        Position whiteKnightPosition = new Position("f6");
        gs.placePiece(whiteKnight, whiteKnightPosition);
        attacksOnBlackKing = gs.findAttacksOnKing(Black);
        assertThat(attacksOnBlackKing, hasSize(1));
        assertThat(attacksOnBlackKing, contains(
                new Take(whiteKnight, whiteKnightPosition, blackKing, blackKingPosition, Collections.emptyList())
        ));
        assertTrue(gs.hasKingSafeMoves(Black));
        gs.placePiece(new Rook(Black), new Position("f8"));
        assertTrue(gs.hasKingSafeMoves(Black));
        gs.placePiece(new Rook(Black), new Position("d8"));
        assertFalse(gs.hasKingSafeMoves(Black));
        assertFalse(gs.isOppositeKingCheckMated(White));
    }
}
