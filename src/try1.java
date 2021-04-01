import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;
import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.EmptyMazeGenerator;
public class try1 {
    public static void main(String[] args) {
        System.out.println("commit number 3");
        Position x = new Position(2,4);
        Position y = new Position(3,4);
        System.out.println(x.is_neighbor(y));
        EmptyMazeGenerator empty = new EmptyMazeGenerator();
        long start_time = System.currentTimeMillis();
        Maze empty_maze = empty.generate(1000,1000);
        empty_maze.print();
        long end_time = System.currentTimeMillis();
        System.out.println("time to build and print 1000X1000 = " + (end_time - start_time)/1000 + " seconds");
        if (empty_maze.getStartPosition().equals(empty_maze.getGoalPosition())){
            System.out.println("Error at RNB's functions");
        }
        long check_time = empty.measureAlgorithmTimeMillis(1000,1000);
        System.out.println("Time to generate Empty Maze is " + check_time/1000 + " seconds");




    }
}
