package chess.matchers;


import chess.Position;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeDiagnosingMatcher;

import java.util.Collection;
import java.util.List;

public class SamePositions extends TypeSafeDiagnosingMatcher<Collection<Position>> {

    private List<String> expectedPositions;
    public SamePositions(List<String> expectedPositions) {
        this.expectedPositions = expectedPositions;
    }

    @Override
    protected boolean matchesSafely(Collection<Position> actualPositions, Description description) {
        if(expectedPositions.size() != actualPositions.size()) {
            description
                    .appendText("Actual and expected positions lists has different size");
            return false;
        }
        for(Position p : actualPositions){
            if(!expectedPositions.contains(p.toString())){
                description
                        .appendText("Position ")
                        .appendValue(p.toString())
                        .appendText(" not exists in expected actualPositions lists ")
                        .appendValue(expectedPositions.toString());
                return false;
            }
        }
        for(String s : expectedPositions){
            if(!actualPositions.contains(new Position(s))){
                description
                        .appendText("Expected actualPositions contains ")
                        .appendValue(s)
                        .appendText(" that absent in actual actualPositions list ");
                return false;
            }
        }
        return true;
    }

    public void describeTo(Description description) {
        description
                .appendText("All actual positions enters to expected positions: ")
                .appendValue(expectedPositions.toString());
    }

    public static SamePositions samePositions(List<String> expectedPositions) {
        return new SamePositions(expectedPositions);
    }

}
