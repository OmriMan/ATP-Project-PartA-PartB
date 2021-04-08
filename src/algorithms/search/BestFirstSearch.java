package algorithms.search;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class BestFirstSearch extends BreadthFirstSearch{

    public BestFirstSearch() {
        //unvisited = new PriorityQueue<>();
    }

    private Queue<AState>give_container(){
        Queue<AState> unvisited = new PriorityQueue<>();
        return unvisited;
    }

    @Override
    public String getName() {
        return "Best First Search";
    }
}
