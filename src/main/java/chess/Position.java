package chess;

import javafx.util.Pair;

/**
 * Describes a position on the Chess Board
 */
public class Position {
    public static final int MIN_ROW = 1;
    public static final int MAX_ROW = 8;
    public static final char MIN_COLUMN = 'a';
    public static final char MAX_COLUMN = 'h';
    private int row;
    private char column;

    /**
     * Create a new position object
     *
     * @param column The column
     * @param row The row
     */
    public Position(char column, int row) {
        this.row = row;
        this.column = column;
    }

    /**
     * Create a new Position object by parsing the string
     * @param colrow The column and row to use.  I.e. "a1", "h7", etc.
     */
    public Position(String colrow) {
        this(colrow.toCharArray()[0], Character.digit(colrow.toCharArray()[1], 10));
    }

    public int getRow() {
        return row;
    }

    public char getColumn() {
        return column;
    }

    /**
     * Create a new Position object by shifting
     * @param shift pair, where key - rows number to shift, value - columns number to shift
     * @return position after shift
     */
    public Position makeShift(Pair<Integer, Integer> shift){
        return new Position((char)(getColumn() + shift.getKey()), getRow() + shift.getValue());
    }

    /**
     * @return true, if position row between row min and max, column - between column min and max.
     * False otherwise
     */
    public boolean isNotOutOfTheBoard() {
        boolean isColumnNotOutOfTheBoard = Position.MIN_COLUMN <= column && column <= Position.MAX_COLUMN;
        boolean isRowNotOutOfTheBoard = Position.MIN_ROW <= row && row <= Position.MAX_ROW;
        return isColumnNotOutOfTheBoard && isRowNotOutOfTheBoard;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (column != position.column) return false;

        //noinspection RedundantIfStatement
        if (row != position.row) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = row;
        result = 31 * result + (int) column;
        return result;
    }

    @Override
    public String toString() {
        return "" + column + row;
    }

}
