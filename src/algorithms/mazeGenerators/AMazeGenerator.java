package algorithms.mazeGenerators;
import java.util.Random;

public abstract class AMazeGenerator implements IMazeGenerator {

    /**
     * Measures 'generate' algorithm time in millisecond.
     *
     * @param row number of rows of the maze.
     * @param col number of columns of the maze.
     * @return the time of the maze generation in milliseconds
     */
    @Override
    public long measureAlgorithmTimeMillis(int row, int col) {
        long start_time = System.currentTimeMillis();
        generate(row, col);
        long end_time = System.currentTimeMillis();
        return end_time - start_time;
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
        int x = rand.nextInt(row-1);

        //Generates a random Position on the sides of the maze
        if (x == 0 || x == row - 1) {
            int y = rand.nextInt(col - 1);
            Position new_RND_Pos = new Position(x,y);
            return new_RND_Pos;
        }

        //Generates a random Position on the top or the bottom of the maze
        else{
            int y = rand.nextInt(1);
            if (y == 0)//Top of maze
            {
                Position new_RND_Pos = new Position(x,0);
                return new_RND_Pos;
            }

            //Bottom of maze
            Position new_RND_Pos = new Position(x,col - 1);
            return new_RND_Pos;
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

}




