package algorithms.search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

public class DepthFirstSearch extends ASearchingAlgorithm{

    /**
     * Implements the DFS algorithm
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
        HashSet <AState> visited = new HashSet<>();
        Stack<AState> unvisited = new Stack<>();
        Stack<AState> solution_stack = new Stack<>();
        ArrayList<AState> possible_moves;

        unvisited.push(start);
        while (!(unvisited.empty())){
            current = unvisited.pop();
            visited.add(current);

            //Check if we reached goal state
            if (current.equals(goal)){
                break;
            }

            //Gets all Possible states to reach from current state
            possible_moves = domain.getAllSuccessors(current);

            //Add state to unvisited only if it's not already in unvisited or visited
            for (int i=0;i< possible_moves.size();i++){
                if (!(visited.contains(possible_moves.get(i))) && !(unvisited.contains(possible_moves.get(i)))){
                    unvisited.push(possible_moves.get(i));
                }
            }
        }
        //If there is no Solution to the problem - we never reach Goal State
        if (!(current.equals(goal)))
        {
           return new Solution(solution_stack);
        }

        //Current is at goal - we trace back the path using getCameFrom
        while(current.getCameFrom()!=null){
            solution_stack.push(current);
            current = current.getCameFrom();
        }
        solution_stack.push(start);
        setVisitedNodes(visited.size());

        return new Solution(solution_stack);
    }

    @Override
    public String getName() {
        return "Depth First Search";
    }
}
