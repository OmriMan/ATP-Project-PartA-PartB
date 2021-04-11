package algorithms.search;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Stack;

public class Solution {
    private ArrayList<AState> states;

    /**
     * Constructor
     * @param Stack_states Stack of Astate that represents the path of the solution
     */
    public Solution(Stack<AState> Stack_states) {
            states = new ArrayList<>();
            while(!Stack_states.empty()){
                states.add(Stack_states.pop());
            }
    }

    /**
     * Gets the solution path as an ArrayList of Astates
     * @return ArrayList of Astates
     */
    public ArrayList<AState> getSolutionPath() {
        if (states.isEmpty()){
            System.out.println("There is not Solution to the maze");
            return new ArrayList<>();
        }
        return states;
    }

}
