package algorithms.search;

import algorithms.mazeGenerators.Position;

public class MazeState extends AState implements Comparable<MazeState>{
    Position pos;

    /**
     * Constructors
     * @param cost 10 for regular move(up, down, right, left), 15 for diagonals
     * @param cameFrom State from which we got to current state
     * @param pos Current Position
     */
    public MazeState(double cost, AState cameFrom, Position pos) {
        super(cost, cameFrom);
        this.pos = pos;
    }

    public MazeState(double cost, Position pos) {
        super(cost);
        this.pos = pos;
    }

    public MazeState(Position pos) {
        this.pos = pos;
    }

    /**
     * Getter and Setter
     * @return
     */
    public Position getPos() {
        return pos;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }

    @Override
    public int hashCode() {
        return pos.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        MazeState temp = (MazeState) obj;
        return pos.equals(temp.getPos());
    }

    @Override
    public String toString() {
        return this.getPos().toString();
    }


    @Override
    public int compareTo(MazeState o) {
        return (int) (this.getCost() - o.getCost());
    }
}
