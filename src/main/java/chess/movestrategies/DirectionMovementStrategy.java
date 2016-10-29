package chess.movestrategies;

import chess.GameState;
import chess.Position;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * Strategy implementation witch has a set of possible shifts and max number of repeating this shifts
 */
public class DirectionMovementStrategy implements MovementStrategy {
    private byte maxDistance;
    private List<Direction> directions;

    public DirectionMovementStrategy(byte maxDistance, List<Direction> directions) {
        this.maxDistance = maxDistance;
        this.directions = directions;
    }

    public Collection<Position> findPossibleMovesFromPositionForGameState(Position startPosition, GameState gameState) {
        Collection<Position> possibleMoves = new ArrayList<Position>();
        for(Direction d: directions) {
            for(Pair<Integer, Integer> shift : d.getShifts()) {
                Position currentPosition = makeShift(startPosition, shift);
                byte movesDone = 1;
                while(isReachable(currentPosition, gameState) && movesDone <= maxDistance){
                    possibleMoves.add(currentPosition);
                    currentPosition = makeShift(currentPosition, shift);
                    movesDone++;
                }
            }
        }
        return possibleMoves;
    }

    private boolean isNotOutOfTheBoard(Position currentPosition) {
        char currentPositionColumn = currentPosition.getColumn();
        int currentPositionRow = currentPosition.getRow();
        boolean isColumnNotOutOfTheBoard = Position.MIN_COLUMN <= currentPositionColumn && currentPositionColumn <= Position.MAX_COLUMN;
        boolean isRowNotOutOfTheBoard = Position.MIN_ROW <= currentPositionRow && currentPositionRow <= Position.MAX_ROW;
        return isColumnNotOutOfTheBoard && isRowNotOutOfTheBoard;
    }

    private boolean noObstacle(Position position, GameState gameState) {
        return gameState.getPieceAt(position) == null;
    }

    private boolean isReachable(Position currentPosition, GameState gameState) {
        return isNotOutOfTheBoard(currentPosition) && noObstacle(currentPosition, gameState);
    }

    private Position makeShift(Position startPosition, Pair<Integer, Integer> shift){
        return new Position((char)(startPosition.getColumn() + shift.getKey()), startPosition.getRow() + shift.getValue());
    }
}
