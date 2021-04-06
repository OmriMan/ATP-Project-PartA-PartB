package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;

public class SearchableMaze implements ISearchable{
    private Maze maze;
    public SearchableMaze(Maze m) {
        maze = m;

    }

    @Override
    public AState getStartState() {
        return null;
    }

    @Override
    public AState getGoalState() {
        return null;
    }

    /**
     *
     * @param s
     * @return ArrayList of all the "passage" AState that near s
     */
    @Override
    public ArrayList<AState> getAllSuccessors(AState s) {
        //need to implement
        ArrayList<AState> to_return = new ArrayList<>();
        MazeState m = (MazeState) s;
        Position curr_position = m.getPos();
        //limit of matrix
        int row = maze.getMaze_matrix().length;
        int col = maze.getMaze_matrix()[0].length;
        //helpers
        boolean up=false;
        boolean down=false;
        boolean right=false;
        boolean left = false;
        //up
        if (curr_position.getRowIndex()-1>=0 && maze.getMaze_matrix()[curr_position.getRowIndex()-1][curr_position.getColumnIndex()] == 0){
            //MazeState new_s = new MazeState( 10,m,new Position(curr_position.getRowIndex()-1,curr_position.getColumnIndex()));
            to_return.add(new MazeState( 10,m,new Position(curr_position.getRowIndex()-1,curr_position.getColumnIndex())));
            up = true;
        }
        //down
        if (curr_position.getRowIndex()+1<=row &&maze.getMaze_matrix()[curr_position.getRowIndex()+1][curr_position.getColumnIndex()] == 0){
            //MazeState new_s = new MazeState( 10,m,new Position(curr_position.getRowIndex()+1,curr_position.getColumnIndex()));
            to_return.add(new MazeState( 10,m,new Position(curr_position.getRowIndex()-1,curr_position.getColumnIndex())));
            down = true;
        }
        //right
        if (curr_position.getColumnIndex()+1<=col && maze.getMaze_matrix()[curr_position.getRowIndex()][curr_position.getColumnIndex()+1] == 0){
            //MazeState new_s = new MazeState( 10,m,new Position(curr_position.getRowIndex()-1,curr_position.getColumnIndex()));
            to_return.add(new MazeState( 10,m,new Position(curr_position.getRowIndex(),curr_position.getColumnIndex()+1)));
            right = true;
        }
        //left
        if (curr_position.getColumnIndex()-1>=0 && maze.getMaze_matrix()[curr_position.getRowIndex()][curr_position.getColumnIndex()-1] == 0){
            //MazeState new_s = new MazeState( 10,m,new Position(curr_position.getRowIndex()-1,curr_position.getColumnIndex()));
            to_return.add(new MazeState( 10,m,new Position(curr_position.getRowIndex(),curr_position.getColumnIndex()-1)));
            left = true;
        }

        //diagonals

        //up
        if (up){
            //check up right
            if (curr_position.getColumnIndex()+1<=col && maze.getMaze_matrix()[curr_position.getRowIndex()-1][curr_position.getColumnIndex()+1]==0){
                to_return.add(new MazeState( 10,m,new Position(curr_position.getRowIndex()-1,curr_position.getColumnIndex()+1)));

            }

            //check up left
            if (curr_position.getColumnIndex()-1>=0 && maze.getMaze_matrix()[curr_position.getRowIndex()-1][curr_position.getColumnIndex()-1]==0){
                to_return.add(new MazeState( 10,m,new Position(curr_position.getRowIndex()-1,curr_position.getColumnIndex()-1)));
            }
        }
        //down
        if (down){

        }



        return null;
    }
}
