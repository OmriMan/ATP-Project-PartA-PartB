package algorithms.mazeGenerators;

import java.util.Random;

public class SimpleMazeGenerator extends AMazeGenerator{
    /**
     *  create Simple maze - build "walls"(1) or 0 by random and make sure there is a path from start to goal
     * @param row - number of rows in maze matrix
     * @param col - number of columns in maze matrix
     * @return maze with at least one path from start position to goal position
     */
    @Override
    public Maze generate(int row, int col) {
        Position start = this.RND_Start_Position(row,col);
        Position goal = this.RND_Goal_Position(row,col,start);
        Maze maze = new Maze(this.Create_2D_matrix(row,col),start,goal);
        Build_RNB_Walls(row,col,maze);
        Build_Simple_Path(start,goal,maze);
        return maze;
    }

    /**
     * fill maze table with zero or one by random
     * @param row - number of row's
     * @param col - number of column's
     * @param maze
     */
    private void Build_RNB_Walls(int row,int col,Maze maze)
    {
        Random rand = new Random();
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if (maze.Is_Start(i,j) || maze.Is_Goal(i,j)){
                    continue;
                }
                maze.setMaze_matrix_by_index(i,j,rand.nextInt(2));
            }
        }
    }

    /**
     * build simple path from start to end
     * @param start - start position
     * @param goal - end position
     * @param maze
     */
    private void Build_Simple_Path(Position start,Position goal,Maze maze)
    {
        //start going on rows
        int i=start.getRowIndex();
        while(i<goal.getRowIndex())
        {
            maze.setMaze_matrix_by_index(i+1,start.getColumnIndex(),0);
            i++;
        }
        while (i>goal.getRowIndex()){
            maze.setMaze_matrix_by_index(i-1,start.getColumnIndex(),0);
            i--;
        }
        //go on columns
        i = start.getColumnIndex();
        while(i<goal.getColumnIndex())
        {
            maze.setMaze_matrix_by_index(goal.getRowIndex(), i+1,0);
            i++;
        }
        while (i>goal.getColumnIndex()){
            maze.setMaze_matrix_by_index(goal.getRowIndex(),i-1,0);
            i--;
        }
    }


}
