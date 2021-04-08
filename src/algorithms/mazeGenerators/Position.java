package algorithms.mazeGenerators;

import java.util.Objects;

public class Position {
    private int RowIndex;
    private int ColumnIndex;

    public Position(int rowIndex, int columnIndex) {
        RowIndex = rowIndex;
        ColumnIndex = columnIndex;
    }

    @Override
    public String toString() {
        return "{" + RowIndex + "," + ColumnIndex +"}";
    }

    public int getRowIndex() {
        return RowIndex;
    }

    public int getColumnIndex() {
        return ColumnIndex;
    }

    /**
     * Overriding of the 'equals' method, checks if other position equals this
     * @param P position to check
     * @return boolean value if other position is equal
     */
    public boolean equals(Position P) {
        return (this.getRowIndex() == P.getRowIndex()) && (this.getColumnIndex() == P.getColumnIndex());
    }

    /**
     * Overriding of the 'equals' method, checks if other position equals this
     * @param obj obj to check
     * @return boolean value if other position is equal
     */
    @Override
    public boolean equals(Object obj) {
        Position p = (Position) obj;
        return (this.getRowIndex() == p.getRowIndex()) && (this.getColumnIndex() == p.getColumnIndex());
    }

    /**
     * Overriding of the 'hashCode' method
     * @return boolean value if other position is equal
     */
    @Override
    public int hashCode() {
        return Objects.hash(RowIndex, ColumnIndex);
    }

    /**
     * Checks if other Position is the same or a neighbor of this
     *
     * @param P position to check
     * @return boolean value - true if other Position is a neighbor, false if not
     */
    public Boolean is_neighbor(Position P){
        if (this.equals(P))
            return true;
        else if (this.getRowIndex() == P.getRowIndex() && (this.getColumnIndex() == P.getColumnIndex() + 1 || this.getColumnIndex() == P.getColumnIndex() -1))
            return true;
        else if (this.getColumnIndex() == P.getColumnIndex() && (this.getRowIndex() == P.getRowIndex() + 1 || this.getRowIndex() == P.getRowIndex() -1))
            return true;

        return false;
    }
}
