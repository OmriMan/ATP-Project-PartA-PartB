package algorithms.search;

import java.util.*;

/**
 * Solving algorithm of BFS - finds the shortest path from start to goal.
 */
public class BreadthFirstSearch extends ASearchingAlgorithm {
    protected Queue<AState> unvisited;

    /**
     * Constructor - Creates a new Queue as a LinkedList
     */
    public BreadthFirstSearch() {
        this.unvisited = new LinkedList<>();

    }

    /**
     * Implements the BFS algorithm
     * @param domain The problem we want to solve
     * @return A Solution object
     */
    @Override
    public Solution solve(ISearchable domain) throws Exception {
        if (domain == null){
            throw new Exception("There is no problem to solve");
        }

        //Initialize states
        AState start = domain.getStartState();
        AState goal = domain.getGoalState();
        AState current = start;

        //Initializes containers
        HashSet<AState> visited = new HashSet<>();
        Stack<AState> solution_stack = new Stack<>();
        ArrayList<AState> Possible_moves;

        //Unvisited is initialized in BFS/BestFirstSearch constructor
        unvisited.add(start);
        while(!unvisited.isEmpty()){
            current = unvisited.remove();
            visited.add(current);

            //Check if we reached goal state
            if (current.equals(goal)){
                break;
            }

            //Gets all Possible states to reach from current state
            Possible_moves = domain.getAllSuccessors(current);

            //Add state to unvisited only if it's not already in unvisited or visited
            for (AState possible_move : Possible_moves) {
                if ((visited.contains(possible_move)) || unvisited.contains(possible_move)) {
                    continue;
                }
                unvisited.add(possible_move);
            }
        }

        //If there is no Solution to the problem - we never reach Goal State
        if (!current.equals(goal))
        {
            return new Solution(solution_stack);
        }

        //Current is at goal - we trace back the path using getCameFrom
        while(current.getCameFrom() != null){
            solution_stack.push(current);
            current = current.getCameFrom();
        }
        solution_stack.push(start);
        setVisitedNodes(visited.size());

        return new Solution(solution_stack);
    }

    @Override
    public String getName() {
        return "Breadth First Search";
    }
}
