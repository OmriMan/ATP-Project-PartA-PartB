package algorithms.search;

import java.util.*;


public class BreadthFirstSearch extends ASearchingAlgorithm {
    protected Queue<AState> unvisited;

    /**
     * Constructor
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
    public Solution solve(ISearchable domain) {
        //Initialize states
        AState start = domain.getStartState();
        AState goal = domain.getGoalState();
        AState current = null;

        //Initializes containers
        HashSet<AState> visited = new HashSet<>();
        Stack<AState> solution_stack = new Stack<>();
        ArrayList<AState> Possible_moves;

        unvisited.add(start);
        while(!unvisited.isEmpty()){
            current = unvisited.remove();
            visited.add(current);

            //Check if we reached goal state
            if (current.equals(goal)){
                break;
            }

            //Gets all Possible states to reach
            Possible_moves = domain.getAllSuccessors(current);

            while(!Possible_moves.isEmpty()){
                AState temp = Possible_moves.remove(0);

                //Add state to unvisited if it is not in visited
                if (!visited.contains(temp)){
                    unvisited.add(temp);
                }
            }
        }

        //If there is no Solution to the problem - we never reach Goal State
        if (!(current.equals(goal)))
        {
            return new Solution(solution_stack);
        }

        //Current is at goal - we trace back the path using getCameFrom
        while(current.getCameFrom() != null){
            solution_stack.add(current);
            current = current.getCameFrom();
        }
        solution_stack.add(current);
        setVisitedNodes(visited.size());

        return new Solution(solution_stack);
    }

    @Override
    public String getName() {
        return "Breadth First Search";
    }
}
