package algorithms.search;

import java.util.ArrayList;
import java.util.Stack;

public class Solution {
    private ArrayList<AState> states;

    public Solution(Stack<AState> Stack_states) {
            states = new ArrayList<>();
            while(!Stack_states.empty()){
                states.add(Stack_states.pop());
            }
    }

    public ArrayList<AState> getSolutionPath() {
        if (states.isEmpty()){
            System.out.println("There is not Solution to the maze");
            return null;
        }
        return states;
    }

}
