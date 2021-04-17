package algorithms.search;

/**
 * Interface of Searching Algorithms
 */
public interface ISearchingAlgorithm {
    Solution solve(ISearchable domain) throws Exception;
    int getNumberOfNodesEvaluated();
    String getName();
}
