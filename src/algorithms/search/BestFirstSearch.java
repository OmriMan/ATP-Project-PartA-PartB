package algorithms.search;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class BestFirstSearch extends BreadthFirstSearch{

    public BestFirstSearch() {
        //unvisited = new PriorityQueue<>();
    }

    public Queue<AState>give_container(){
        return new PriorityQueue<>();
    }

    @Override
    public String getName() {
        return "Best First Search";
    }
}
