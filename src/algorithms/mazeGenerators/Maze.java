package algorithms.mazeGenerators;


public class Maze {
    private int[][] maze_matrix;
    private Position start_pos;
    private Position goal_pos;

    /**
     *
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
     * Getters
     * @return start or goal by order(Position)
     */
    public Position getStartPosition() {
        return start_pos;
    }

    public Position getGoalPosition() {
        return goal_pos;
    }

    public int[][] getMaze_matrix() {
        return maze_matrix;
    }

    public void setMaze_matrix_by_index(int row,int col,int val) {
        this.maze_matrix[row][col] = val;
    }

    public void setMaze_matrix_by_Position(Position p,int val){
        this.maze_matrix[p.getRowIndex()][p.getColumnIndex()] = val;
    }

    /**
     * print the maze by order - maybe better implement tostring() and just call it from here ?
     */
    public void print(){
        //mark start and goal points with 'S'(89) and 'E'(63) - not effective but make it readable
//        this.getMaze_matrix()[this.getStartPosition().getRowIndex()][this.getStartPosition().getColumnIndex()] = 'S';
//        this.getMaze_matrix()[this.getGoalPosition().getRowIndex()][this.getGoalPosition().getColumnIndex()] = 'E';
        for(int i=0;i<this.getMaze_matrix().length;i++){
            System.out.print("{");
            for(int j=0;j<this.getMaze_matrix()[0].length -1 ;j++){
                if (Is_Start(i,j)){
                    System.out.print("S ,");
                }
                else if (Is_Goal(i,j)){
                    System.out.print("E ,");
                }
                else
                    System.out.print(this.getMaze_matrix()[i][j] +" ,");
            }
            if (Is_Goal(i,this.getMaze_matrix()[0].length -1)) {
                System.out.println("E}");
            }
            else if (Is_Start(i,this.getMaze_matrix()[0].length -1)) {
                System.out.println("S}");
            }
            else {
                System.out.println(this.getMaze_matrix()[i][this.getMaze_matrix()[0].length -1] + "}");
            }

        }
    }

    /**
     * check if its start or goal position
     * @param row
     * @param col
     * @return true or false
     */
    public boolean Is_Start(int row,int col)
    {
        if (row==this.getStartPosition().getRowIndex() && col== this.getStartPosition().getColumnIndex()){
            return true;
        }
        return false;
    }

    public boolean Is_Goal(int row,int col)
    {
        if (row==this.getGoalPosition().getRowIndex() && col== this.getGoalPosition().getColumnIndex()){
            return true;
        }
        return false;
    }
// argument here its position
//    public boolean Is_Start(Position p)
//    {
//        if (p.getRowIndex()==this.getStartPosition().getRowIndex() && p.getColumnIndex()== this.getStartPosition().getColumnIndex()){
//            return true;
//        }
//        return false;
//    }
//
//    public boolean Is_Goal(Position p)
//    {
//        if (p.getRowIndex()==this.getGoalPosition().getRowIndex() && p.getColumnIndex()== this.getGoalPosition().getColumnIndex()){
//            return true;
//        }
//        return false;
//    }
}

