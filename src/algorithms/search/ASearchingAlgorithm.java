package algorithms.search;
import java.lang.Cloneable;
/**
 * Abstract class of all Searching Algorithms
 */
public abstract class ASearchingAlgorithm implements ISearchingAlgorithm, Cloneable{
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

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
