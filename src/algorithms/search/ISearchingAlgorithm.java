package algorithms.search;

public interface ISearchingAlgorithm {
    Solution solve(ISearchable s);
    int GetNumberOfVisitedNodes();
    String getName();
}
