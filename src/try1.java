import algorithms.mazeGenerators.*;

public class try1 {
    public static void main(String[] args) {
        SimpleMazeGenerator empty = new SimpleMazeGenerator();
        long start_time = System.currentTimeMillis();
        Maze empty_maze = empty.generate(100,100);
        empty_maze.print();
        long end_time = System.currentTimeMillis();
        System.out.println("time to build and print Simple Maze 100X100 = " + (end_time - start_time)/1000 + " seconds");
        if (empty_maze.getStartPosition().equals(empty_maze.getGoalPosition())){
            System.out.println("Error at RNB's functions");
        }
        long check_time = empty.measureAlgorithmTimeMillis(1000,1000);
        System.out.println("Time to generate Simple Maze is " + check_time/1000 + " seconds");




    }
}
