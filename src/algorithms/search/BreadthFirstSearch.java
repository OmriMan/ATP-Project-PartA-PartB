package algorithms.search;

import java.util.*;


public class BreadthFirstSearch extends ASearchingAlgorithm {
    //protected Queue<AState> unvisited;

    /**
     * Constructor
     */
//    public BreadthFirstSearch() {
//
//    }
    private Queue<AState>give_container(){
        //Queue<AState> unvisited = new LinkedList<>();
        return new LinkedList<>();
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
        AState current = start;

        //Initializes containers
        HashSet<AState> visited = new HashSet<>();
        Stack<AState> solution_stack = new Stack<>();
        ArrayList<AState> Possible_moves;
        Queue<AState> unvisited = give_container();

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
            for (AState possible_move : Possible_moves) {
                if ((visited.contains(possible_move))) {
                    continue;
                }
                unvisited.add(possible_move);
            }
            //            while(!Possible_moves.isEmpty()){
//                AState temp = Possible_moves.remove(0);
//
//                //Add state to unvisited if it is not in visited
//                if (!visited.contains(temp)){
//                    unvisited.add(temp);
//                }
//            }
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
