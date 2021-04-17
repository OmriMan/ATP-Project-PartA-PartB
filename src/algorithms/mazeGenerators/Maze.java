package algorithms.mazeGenerators;


/**
 * Class that represents a 2D Maze.
 */
public class Maze {
    private int[][] maze_matrix;
    private Position start_pos;
    private Position goal_pos;

    /**
     *Constructor
     * @param maze_matrix - 2d array of int
     * @param start_pos - start Position
     * @param goal_pos - goal Position
     */
    public Maze(int[][] maze_matrix, Position start_pos, Position goal_pos) {
        this.maze_matrix = maze_matrix;
        this.start_pos = start_pos;
        this.goal_pos = goal_pos;
    }

    /**
     * Default constructor with no parameter - in case size of maze not big enough
     */
    public Maze(){
        this.maze_matrix = null;
        this.start_pos = null;
        this.goal_pos = null;
    }

    /**
     * Getters
     * @return start or goal by order(Position)
     */
    public Position getStartPosition() {
        if (this.start_pos == null){
            return null;
        }
        return start_pos;
    }

    public Position getGoalPosition() {
        if (this.goal_pos == null){
            return null;
        }
        return goal_pos;
    }

    public int[][] getMaze_matrix() {
        return maze_matrix;
    }

    public void setMaze_matrix_by_index(int row,int col,int val) {
        this.maze_matrix[row][col] = val;
    }


    /**
     * print the maze by order - maybe better implement tostring() and just call it from here ?
     */
    public void print(){
        if (this.getMaze_matrix() == null){
            System.out.println("Maze does not exist");
            return;
        }

        for(int i=0;i<this.getMaze_matrix().length;i++){
            System.out.print("{ ");
            for(int j=0;j<this.getMaze_matrix()[0].length -1 ;j++){
                if (Is_Start(i,j)){
                    System.out.print("S ");
                }
                else if (Is_Goal(i,j)){
                    System.out.print("E ");
                }
                else
                    System.out.print(this.getMaze_matrix()[i][j] +" ");
            }
            if (Is_Goal(i,this.getMaze_matrix()[0].length -1)) {
                System.out.println("E }");
            }
            else if (Is_Start(i,this.getMaze_matrix()[0].length -1)) {
                System.out.println("S }");
            }
            else {
                System.out.println(this.getMaze_matrix()[i][this.getMaze_matrix()[0].length -1] + " }");
            }
        }
    }

    /**
     * check if its start or goal position
     * @param row - number of rows
     * @param col - number of columns
     * @return Boolean value
     */
    public boolean Is_Start(int row,int col)
    {
        return row == this.getStartPosition().getRowIndex() && col == this.getStartPosition().getColumnIndex();
    }

    public boolean Is_Goal(int row,int col)
    {
        return row == this.getGoalPosition().getRowIndex() && col == this.getGoalPosition().getColumnIndex();
    }

}

