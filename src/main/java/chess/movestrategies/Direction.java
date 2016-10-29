package chess.movestrategies;

import javafx.util.Pair;

import java.util.Arrays;
import java.util.List;

public enum Direction {
    HORIZONTAL_VERTICAL(Arrays.asList(new Pair<>(1, 0),
                                      new Pair<>(-1, 0),
                                      new Pair<>(0,1),
                                      new Pair<>(0,-1)
            )),

    DIAGONAL(Arrays.asList(new Pair<>(1,1),
                           new Pair<>(-1,1),
                           new Pair<>(1,-1),
                           new Pair<>(-1,-1)
            )),

    L_LIKE(Arrays.asList(new Pair<>(2,1),
                         new Pair<>(2,-1),
                         new Pair<>(-2,1),
                         new Pair<>(-2,-1),
                         new Pair<>(1,2),
                         new Pair<>(1,-2),
                         new Pair<>(-1,2),
                         new Pair<>(-1,-2)
            ));

    private List<Pair<Integer, Integer>> shifts;
    Direction(List<Pair<Integer, Integer>> shifts) {
        this.shifts = shifts;
    }
    public List<Pair<Integer, Integer>> getShifts() {
        return shifts;
    }
}
