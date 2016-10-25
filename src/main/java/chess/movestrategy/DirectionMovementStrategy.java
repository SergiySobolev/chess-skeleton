package chess.movestrategy;

import chess.GameState;
import chess.Position;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


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
                Position currentPosition = startPosition;
                for(byte i=0; i<maxDistance; i++) {
                    Position newPosition = makeShift(currentPosition, shift);
                    if(isReachable(newPosition, gameState) ){
                        possibleMoves.add(newPosition);
                        currentPosition = newPosition;
                    } else {
                        break;
                    }
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
