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

    /**
     * print the maze by order - maybe better implement tostring() and just call it from here ?
     */
    public void print(){
        //mark start and goal points with 'S'(89) and 'E'(63) - not effective but make it readable
        this.getMaze_matrix()[this.getStartPosition().getRowIndex()][this.getStartPosition().getColumnIndex()] = 'S';
        this.getMaze_matrix()[this.getGoalPosition().getRowIndex()][this.getGoalPosition().getColumnIndex()] = 'E';
        for(int i=0;i<this.getMaze_matrix().length;i++){
            System.out.print("{");
            for(int j=0;j<this.getMaze_matrix()[0].length -1 ;j++){
                if (this.getMaze_matrix()[i][j] =='S'){
                    System.out.print("S ,");
                }
                else if (this.getMaze_matrix()[i][j] =='E'){
                    System.out.print("E ,");
                }
                else
                    System.out.print(this.getMaze_matrix()[i][j] +" ,");
            }
            if (this.getMaze_matrix()[i][this.getMaze_matrix()[0].length -1] ==0) {
                System.out.println(this.getMaze_matrix()[i][this.getMaze_matrix()[0].length -1] + "}");
            }
            else if (this.getMaze_matrix()[i][this.getMaze_matrix()[0].length -1] =='S') {
                System.out.println(" S}");
            }
            else
                System.out.println(" E}");

        }
    }
}

