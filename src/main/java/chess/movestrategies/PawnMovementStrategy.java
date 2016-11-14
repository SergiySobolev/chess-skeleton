package chess.movestrategies;

import chess.GameState;
import chess.Player;
import chess.Position;
import chess.Take;
import chess.pieces.Piece;
import javafx.util.Pair;

import java.util.*;

/**
 * Special movement strategy for pawns, dependant of player color
 */
public class PawnMovementStrategy implements MovementStrategy {
    private static final List<Integer> impossibleLinesForPawn = Arrays.asList(1,8);
    private static final Map<Player, Integer> firstLinesForPawn = new HashMap<Player, Integer>() {{
        put(Player.White, 2);
        put(Player.Black, 7);
    }};
    private static final Map<Player, Pair<Integer, Integer>> moveShifts = new HashMap<Player, Pair<Integer, Integer>>() {{
        put(Player.White, new Pair<>(0, 1));
        put(Player.Black, new Pair<>(0, -1));
    }};
    private static final Map<Player, List<Pair<Integer, Integer>>> takeShifts = new HashMap<Player, List<Pair<Integer, Integer>>>() {{
        put(Player.White, Arrays.asList(new Pair<>(1, 1), new Pair<>(-1, 1)));
        put(Player.Black, Arrays.asList(new Pair<>(1, -1), new Pair<>(-1, -1)));
    }};

    private Player player;

    public PawnMovementStrategy(Player player) {
        this.player = player;
    }
    public Collection<Position> findPossibleMovesFromPositionForGameState(Position startPosition, GameState gameState) {
        if(impossibleLinesForPawn.contains(startPosition.getRow())) {
            throw new IllegalArgumentException(startPosition.getRow() + " line is illegal line for pawn");
        }
        boolean isPieceOnStartPosition = firstLinesForPawn.get(player) == startPosition.getRow();
        int distance = isPieceOnStartPosition ? 2 : 1;
        Collection<Position> positions = new ArrayList<>();
        Position currentPosition = startPosition;
        Pair<Integer, Integer> shift = moveShifts.get(player);
        while(distance > 0) {
            currentPosition = currentPosition.makeShift(shift);
            if(!gameState.isReachable(currentPosition)) break;
            positions.add(currentPosition);
            distance--;
        }
        return positions;
    }

    @Override
    public Collection<Take> findPossibleTakingFromPositionForGameState(Position startPosition, GameState gameState, Player currentPlayer) {
        if(impossibleLinesForPawn.contains(startPosition.getRow())) {
            throw new IllegalArgumentException(startPosition.getRow() + " line is illegal line for pawn");
        }
        Collection<Take> taking = new ArrayList<>();
        Piece currentPiece = gameState.getPieceAt(startPosition);
        List<Pair<Integer, Integer>> shifts = takeShifts.get(player);
        for(Pair<Integer, Integer> shift : shifts) {
            Position currentPosition = startPosition.makeShift(shift);
            if(gameState.hasPieceOfAnotherPlayer(currentPlayer, currentPosition)){
                taking.add(new Take(currentPiece, startPosition, gameState.getPieceAt(currentPosition), currentPosition, Collections.emptyList()));
            }
        }
        return taking;
    }
}
