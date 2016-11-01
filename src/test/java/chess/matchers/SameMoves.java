package chess.matchers;


import chess.Move;
import chess.Player;
import chess.Position;
import chess.pieces.*;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeDiagnosingMatcher;

import java.util.*;
import java.util.stream.Collectors;

public class SameMoves extends TypeSafeDiagnosingMatcher<Collection<Move>> {
    private final Map<Character, Piece> pieceIdentifiers = new HashMap<Character, Piece>(){{
        put('p', new Pawn(Player.White));
        put('P', new Pawn(Player.Black));
        put('b', new Bishop(Player.White));
        put('B', new Bishop(Player.Black));
        put('k', new King(Player.White));
        put('K', new King(Player.Black));
        put('n', new Knight(Player.White));
        put('N', new Knight(Player.Black));
        put('q', new Queen(Player.White));
        put('Q', new Queen(Player.Black));
        put('r', new Queen(Player.White));
        put('R', new Queen(Player.Black));
    }};

    private List<String> expectedMovesAsStrings;
    public SameMoves(List<String> expectedMovesAsStrings) {
        this.expectedMovesAsStrings = expectedMovesAsStrings;
    }
    @Override
    protected boolean matchesSafely(Collection<Move> actualMoves, Description description) {
        if(expectedMovesAsStrings.size() != actualMoves.size()) {
            description
                    .appendText("Actual and expected moves lists has different size");
            return false;
        }
        List<Move> expectedMoves = expectedMovesAsStrings.stream().map(this::parseStringAsMove).collect(Collectors.toList());
        for(Move m : actualMoves){
            if(!expectedMoves.contains(m)){
                description
                        .appendText("Move ")
                        .appendValue(m)
                        .appendText(" not exists in expected moves lists ")
                        .appendValue(expectedMoves.toString());
                return false;
            }
        }
        for(Move m : expectedMoves){
            if(!actualMoves.contains(m)){
                description
                        .appendText("Expected actualMoves contains ")
                        .appendValue(m)
                        .appendText(" that absent in actual moves list ");
                return false;
            }
        }
        return true;
    }

    @Override
    public void describeTo(Description description) {
        description
                .appendText("All actual moves enters to expected moves: ")
                .appendValue(expectedMovesAsStrings.toString());
    }

    public static SameMoves sameMoves(List<String> expectedMovesAsStrings) {
        return new SameMoves(expectedMovesAsStrings);
    }

    private Move parseStringAsMove(String s) {
        String parsed = s.replace(" ", "").replace("-","");
        assert parsed.length() == 5;
        Piece p = pieceIdentifiers.get(parsed.charAt(0));
        assert Objects.nonNull(p);
        Position from = new Position(parsed.substring(1,3));
        Position to = new Position(parsed.substring(3,5));
        return new Move(p, from, to);
    }
}
