package algorithms.search;

import java.util.ArrayList;
import java.util.Stack;

public class Solution {
    private ArrayList<AState> states;

    public Solution(ArrayList<AState> states) {
        this.states = states;
    }

    public ArrayList<AState> getSolutionPath() {
        return states;
    }

    public void setStates(Stack<AState> Stack_states) {
        while(!Stack_states.empty()){
            states.add(Stack_states.pop());
        }
    }
}
