package algorithms.mazeGenerators;


public class Maze {
    private int[][] maze_matrix;
    private Position start_pos;
    private Position goal_pos;

    protected Maze(int[][] maze_matrix, Position start_pos, Position goal_pos) {
        this.maze_matrix = maze_matrix;
        this.start_pos = start_pos;
        this.goal_pos = goal_pos;
    }


    public Position getStartPosition() {
        return start_pos;
    }

    public Position getGoalPosition() {
        return goal_pos;
    }
}

