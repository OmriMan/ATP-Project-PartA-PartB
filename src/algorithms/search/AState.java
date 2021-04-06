package algorithms.search;

public abstract  class AState {
    private double cost;
    private AState cameFrom;

    /**
     *  Constructor
     * @param cost
     * @param cameFrom
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

    public AState(double cost) {
        this.cost = cost;
        this.cameFrom = null;
    }

    /**
     * add cost to this state
     * @param to_add
     */
    public void AddCost(double to_add){
        cost+=to_add;
    }
    /**
     * getter and setters
     * @return
     */
    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public AState getCameFrom() {
        return cameFrom;
    }

    public void setCameFrom(AState cameFrom) {
        this.cameFrom = cameFrom;
    }

}
