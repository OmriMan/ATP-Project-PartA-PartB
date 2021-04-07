package algorithms.search;

public interface ISearchingAlgorithm {
    Solution solve(ISearchable domain);
    int GetNumberOfVisitedNodes();
    String getName();
}
