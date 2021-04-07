package algorithms.search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

public class DepthFirstSearch extends ASearchingAlgorithm{
    @Override
    public Solution solve(ISearchable domain) {
        HashSet <AState> visited = new HashSet<>();
        Stack<AState> unvisited = new Stack<>();
        Stack<AState> solution_stack = new Stack<>();
        ArrayList<AState> possible_moves = new ArrayList<>();
        AState start = domain.getStartState();
        AState goal = domain.getGoalState();
        AState current = start;
        unvisited.push(start);
        while (!(unvisited.empty())){
            current = unvisited.pop();
            visited.add(current);
            if (current.equals(goal)){
                break;
            }
            possible_moves = domain.getAllSuccessors(current);
            //add the unvisited Successors to unvisited
            for (int i=0;i< possible_moves.size();i++){
                if (!(visited.contains(possible_moves.get(i)))){
                    unvisited.push(possible_moves.get(i));
                }
            }
        }
        //when we get here' current its goal
        while(current.getCameFrom()!=null){
            solution_stack.push(current);
            current = current.getCameFrom();
        }
        solution_stack.push(start);
        return new Solution(solution_stack);
    }

    @Override
    public String getName() {
        return "Depth First Search";
    }
}
