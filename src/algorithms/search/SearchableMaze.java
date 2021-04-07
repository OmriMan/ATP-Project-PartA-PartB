package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;

public class SearchableMaze implements ISearchable{
    private Maze maze;

    /**
     * Constructor
     * @param m Given maze
     */
    public SearchableMaze(Maze m) {
        maze = m;
    }

    @Override
    public AState getStartState() { return new MazeState(maze.getStartPosition());
    }

    @Override
    public AState getGoalState() {
        return new MazeState(maze.getGoalPosition());
    }

    /**
     *
     * @param s Current state to check
     * @return ArrayList of all the "passage" AState that near s
     */
    @Override
    public ArrayList<AState> getAllSuccessors(AState s) {
        //Return list
        ArrayList<AState> states_list = new ArrayList<>();

        //Given state properties
        MazeState curr_state = (MazeState) s;
        Position curr_position = curr_state.getPos();
        int curr_row = curr_position.getRowIndex();
        int curr_col = curr_position.getColumnIndex();

        //limit of matrix
        int row = maze.getMaze_matrix().length;
        int col = maze.getMaze_matrix()[0].length;

        //Boolean helpers
        boolean up_right=false;
        boolean right_down=false;
        boolean down_left=false;
        boolean left_up= false;
        boolean up = false;


        //Checks up
        if (curr_row-1>=0 && maze.getMaze_matrix()[curr_row-1][curr_col] == 0){
            //MazeState new_s = new MazeState( 10,m,new Position(curr_row-1,curr_col));
            states_list.add(new MazeState( 10,curr_state,new Position(curr_row-1,curr_col)));
            up = true;

            //Checks up-right
            if (curr_col+1<col && maze.getMaze_matrix()[curr_row-1][curr_col+1] == 0){
                states_list.add(new MazeState(15, curr_state, new Position(curr_row-1, curr_col+1 )));
                up_right = true;
            }
        }


        //Checks right
        if (curr_col + 1 < col && maze.getMaze_matrix()[curr_row][curr_col + 1] == 0) {
            
            //Checks right-up
                if (!up_right && curr_row-1>=0 && maze.getMaze_matrix()[curr_row-1][curr_col+1] == 0){
                    //Adds right-up
                    states_list.add(new MazeState( 15,curr_state,new Position(curr_row-1,curr_col+1)));

            }
            //Adds right
            states_list.add(new MazeState(10, curr_state, new Position(curr_row, curr_col+1)));
            
            //Checks right-down
            if (curr_row + 1 < row && maze.getMaze_matrix()[curr_row+1][curr_col+1]==0){
                right_down = true;
                //Adds right-down
                states_list.add(new MazeState(15, curr_state, new Position(curr_row+1, curr_col+1)));
            }
        }


        //Down
        if (curr_row + 1 < row && maze.getMaze_matrix()[curr_row + 1][curr_col] == 0){

            //Checks down-right
            if (!right_down && curr_col +1 < col && maze.getMaze_matrix()[curr_row+1][curr_col+1] == 0){
                //Adds right-down
                states_list.add(new MazeState(15, curr_state, new Position(curr_row+1, curr_col+1)));
            }

            //Adds down
            states_list.add(new MazeState(10, curr_state, new Position(curr_row+1, curr_col)));


            //Checks down-left
            if (curr_col-1 >= 0 && maze.getMaze_matrix()[curr_row+1][curr_col-1]==0){
                down_left = true;
                //Adds down-left
                states_list.add(new MazeState(15, curr_state, new Position(curr_row+1, curr_col-1)));
            }
        }


        //Left
        if (curr_col - 1 >= 0 && maze.getMaze_matrix()[curr_row][curr_col-1] == 0){

            //Checks left-down
            if (!down_left && curr_row + 1 < row && maze.getMaze_matrix()[curr_row + 1][curr_col - 1] == 0){
                //Adds down-left
                states_list.add(new MazeState(15, curr_state, new Position(curr_row+1, curr_col-1)));
            }

            //Adds left
            states_list.add(new MazeState(10, curr_state, new Position(curr_row, curr_col - 1)));

            //Checks left-up
            if (curr_row - 1 >= 0 && maze.getMaze_matrix()[curr_row - 1][curr_col - 1] == 0){
                left_up = true;
                //Adds left-up
                states_list.add(new MazeState(15, curr_state, new Position(curr_row-1, curr_col-1)));
            }
        }


        //Checks up-left
        if (!left_up && up && curr_col-1 >= 0 && maze.getMaze_matrix()[curr_row-1][curr_col-1] == 0){
            states_list.add(new MazeState(15, curr_state, new Position(curr_row-1, curr_col-1)));
        }

        return states_list;
    }
}






