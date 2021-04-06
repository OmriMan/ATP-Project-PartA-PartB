package algorithms.search;

import algorithms.mazeGenerators.Position;

public class MazeState extends AState{
    Position pos;

    /**
     * Constructors
     * @param cost
     * @param cameFrom
     * @param pos
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
}
