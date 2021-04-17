package algorithms.search;

import java.util.PriorityQueue;

/**
 * Solving algorithms that finds the cheapest path from start to goal. Uses a priority queue.
 */
public class BestFirstSearch extends BreadthFirstSearch{

    public BestFirstSearch() {
        unvisited = new PriorityQueue<>();
    }


    @Override
    public String getName() {
        return "Best First Search";
    }
}
