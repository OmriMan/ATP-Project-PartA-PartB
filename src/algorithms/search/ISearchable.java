package algorithms.search;

import java.util.ArrayList;

/**
 * Interface for object adapter of a problem that can be solved using a searching algorithm
 */
public interface ISearchable {
    AState getStartState();
    AState getGoalState();
    ArrayList<AState> getAllSuccessors(AState s);
}
