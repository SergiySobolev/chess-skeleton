package chess.movestrategies;

import chess.GameState;
import chess.Player;
import chess.Position;
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
    private static final Map<Player, Pair<Integer, Integer>> shifts = new HashMap<Player, Pair<Integer, Integer>>() {{
        put(Player.White, new Pair<>(0, 1));
        put(Player.Black, new Pair<>(0, -1));
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
        Pair<Integer, Integer> shift = shifts.get(player);
        while(distance > 0) {
            currentPosition = currentPosition.makeShift(shift);
            if(!gameState.isReachable(currentPosition)) break;
            positions.add(currentPosition);
            distance--;
        }
        return positions;
    }
}
