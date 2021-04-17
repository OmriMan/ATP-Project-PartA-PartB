package algorithms.search;

/**
 * Abstract class of all Searching Algorithms
 */
public abstract class ASearchingAlgorithm implements ISearchingAlgorithm{
    private int visitedNodes;

    /**
     * Constructor
     */
    public ASearchingAlgorithm() {
        visitedNodes=0;
    }

    @Override
    public int getNumberOfNodesEvaluated() {
        return visitedNodes;
    }

    public void setVisitedNodes(int visitedNodes) {
        this.visitedNodes = visitedNodes;
    }
}
