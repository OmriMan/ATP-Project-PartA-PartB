package algorithms.mazeGenerators;

public class EmptyMazeGenerator extends AMazeGenerator {
    /**
     * generate an empty maze - random start point and random goal point, each cell in maze table is 0
     * @param row - number of rows
     * @param col - number of columns
     * @return Maze empty maze
     */
    @Override
    public Maze generate(int row, int col) {
        try{
            is_good_size(row,col);
        }
        catch (Exception e){
            row = 5;
            col =5;
        }
        Position start = this.RND_Start_Position(row,col);
        Position goal = this.RND_Goal_Position(row,col,start);
        Maze maze = new Maze(this.Create_2D_matrix(row,col),start,goal);
        return maze;
    }
}

