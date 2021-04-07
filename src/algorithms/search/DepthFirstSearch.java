package algorithms.search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

public class DepthFirstSearch extends ASearchingAlgorithm{
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
        ArrayList<AState> possible_moves = new ArrayList<>();

        unvisited.push(start);
        while (!(unvisited.empty())){
            current = unvisited.pop();
            visited.add(current);

            //Check if we reached goal state
            if (current.equals(goal)){
                break;
            }

            //Gets all Possible states to reach
            possible_moves = domain.getAllSuccessors(current);

            //Add state to unvisited if it is not in visited
            for (int i=0;i< possible_moves.size();i++){
                if (!(visited.contains(possible_moves.get(i)))){
                    unvisited.push(possible_moves.get(i));
                }
            }
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
