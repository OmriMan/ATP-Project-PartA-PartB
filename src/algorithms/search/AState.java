package algorithms.search;

import java.io.Serializable;

/**
 * Abstract class of States in solvable problems
 */
public abstract  class AState implements Serializable {
    private double cost;
    private AState cameFrom;

    /**
     *  Constructor
     * @param cost cost to reach current state
     * @param cameFrom what state sent to current state
     */
    public AState(double cost, AState cameFrom) {
        this.cost = cost;
        this.cameFrom = cameFrom;
    }

    /**
     * Default Constructor
     */
    public AState() {
        this.cost=0;
        this.cameFrom = null;
    }


    /**
     * getter for cost
     * @return the cost of this state
     */
    public double getCost() {
        return cost;
    }


    /**
     * Getter for CameFrom
     * @return the Astate from where we came from
     */
    public AState getCameFrom() {
        return cameFrom;
    }

}
