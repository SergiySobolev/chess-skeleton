package chess.movestrategies;

import chess.GameState;
import chess.Position;
import javafx.util.Pair;

import java.util.*;


/**
 * Strategy implementation witch has a set of possible shifts and max number of repeating this shifts
 */
public class DirectionMovementStrategy implements MovementStrategy {
    private int maxDistance;
    private List<Direction> directions;

    public DirectionMovementStrategy(int maxDistance, Direction direction) {
        this.maxDistance = maxDistance;
        this.directions = Collections.singletonList(direction);
    }

    public DirectionMovementStrategy(byte maxDistance, List<Direction> directions) {
        this.maxDistance = maxDistance;
        this.directions = directions;
    }

    public Collection<Position> findPossibleMovesFromPositionForGameState(Position startPosition, GameState gameState) {
        assert Objects.nonNull(startPosition);
        assert Objects.nonNull(gameState);
        Collection<Position> possibleMoves = new ArrayList<>();
        for(Direction d: directions) {
            for(Pair<Integer, Integer> shift : d.getShifts()) {
                Position currentPosition = startPosition.makeShift(shift);
                byte movesDone = 1;
                while(gameState.isReachable(currentPosition) && movesDone <= maxDistance){
                    possibleMoves.add(currentPosition);
                    currentPosition = currentPosition.makeShift(shift);
                    movesDone++;
                }
            }
        }
        return possibleMoves;
    }
   }
