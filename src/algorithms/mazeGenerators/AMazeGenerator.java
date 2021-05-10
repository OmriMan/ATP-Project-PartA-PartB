package algorithms.mazeGenerators;
import java.util.Random;
import java.lang.Cloneable;

/**
 * Abstract class of all Maze Generator algorithms
 */
public abstract class AMazeGenerator implements IMazeGenerator, Cloneable{

    /**
     * Measures 'generate' algorithm time in millisecond.
     *
     * @param row number of rows of the maze.
     * @param col number of columns of the maze.
     * @return the time of the maze generation in milliseconds
     */
    @Override
    public long measureAlgorithmTimeMillis(int row, int col) throws Exception {
        long start_time = System.currentTimeMillis();
        generate(row, col);
        long end_time = System.currentTimeMillis();
        return end_time - start_time;
    }

    /**
     * Create empty table (full of zero's) size rowXcol
     * @param row - number of rows
     * @param col - number of columns
     * @return 2D array of int
     */
    public int[][] Create_2D_matrix(int row,int col)
    {
        return new int[row][col];

    }

    public void is_good_size(int row,int col) throws Exception {
        if (row<2 || col<2){
            throw new Exception("Maze size too small :( Should be at least 2x2");
        }
    }
    /**
     * Generates a random Position on the frame of the Maze.
     *
     * @param row number of rows of the maze.
     * @param col number of columns of the maze.
     * @return a random Position
     */
    public Position RND_Position(int row, int col) {
        Random rand = new Random();
        int x = rand.nextInt(row);

        //Generates a random Position on the sides of the maze
        if (x == 0 || x == row -1) {
            int y = rand.nextInt(col);
            return new Position(x,y);

        }

        //Generates a random Position on the top or the bottom of the maze
        else{
            int y = rand.nextInt(2);
            if (y == 0)//Top of maze
            {
                return new Position(x,0);
            }

            //Bottom of maze
            return new Position(x,col-1);
        }
    }


    /**
     * Generates a random start Position on the frame of the maze
     *
     * @param row number of rows of the maze.
     * @param col number of columns of the maze.
     * @return a random start Position
     */
    public Position RND_Start_Position(int row, int col){
        return RND_Position(row, col);
    }


    /**
     * Generates a random goal Position on the frame of the maze and checks that it's not equal to start Position or a neighbor
     *
     * @param row number of rows of the maze.
     * @param col number of columns of the maze.
     * @return a random goal Position
     */
    public Position RND_Goal_Position(int row, int col, Position start_pos) {
        Position Pos_to_return;
        do {
            Pos_to_return = RND_Position(row, col);
        }while(Pos_to_return.is_neighbor(start_pos));

        return Pos_to_return;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}




