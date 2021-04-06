package algorithms.search;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm{
    private int visitedNodes;

    /**
     * Constructor
     */
    public ASearchingAlgorithm() {
        visitedNodes=0;
    }

    @Override
    public int GetNumberOfVisitedNodes() {
        return visitedNodes;
    }
}
