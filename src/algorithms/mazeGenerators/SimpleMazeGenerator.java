package algorithms.mazeGenerators;

import java.util.Random;

public class SimpleMazeGenerator extends AMazeGenerator{
    /**
     *  create Simple maze - build "walls"(1) or passage (0) by random probability (2/3 for walls)
     *  and then creates at least 2 paths from Start to goal
     * @param row - number of rows in maze matrix
     * @param col - number of columns in maze matrix
     * @return maze with at least two paths from start position to goal position
     */
    @Override
    public Maze generate(int row, int col) {
        try{
            is_good_size(row,col);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            row = 5;
            col =5;
        }
        Position start = this.RND_Start_Position(row,col);
        Position goal = this.RND_Goal_Position(row,col,start);
        Maze maze = new Maze(this.Create_2D_matrix(row,col),start,goal);
        Build_RNB_Walls(row,col,maze);

        //Finds a random cell and connects start to goal via this random cell
        Random rand = new Random();
        Position random_p = new Position(rand.nextInt(row), rand.nextInt(col));
        Build_Simple_Path(random_p,goal,maze);
        Build_Simple_Path(start, random_p,maze);

        //Second path
        random_p = new Position(rand.nextInt(row), rand.nextInt(col));
        Build_Simple_Path(random_p,goal,maze);
        Build_Simple_Path(start, random_p,maze);
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

                //Creates a "wall" (1) with a probability of 2/3
                if (rand.nextInt(3) % 2 == 0)
                {
                    maze.setMaze_matrix_by_index(i,j,1);
                }


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
