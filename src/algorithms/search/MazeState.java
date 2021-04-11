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


    public MazeState(Position pos) {
        this.pos = pos;
    }

    /**
     * Getter of Position
     * @return Position of this
     */
    public Position getPos() {
        return pos;
    }


    @Override
    public int hashCode() {
        return pos.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof MazeState)) {
            return false;
        }
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
