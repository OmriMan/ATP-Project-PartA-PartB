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
        Maze empty_maze = empty.generate(5,7);
        empty_maze.print();
        if (empty_maze.getStartPosition().equals(empty_maze.getGoalPosition())){
            System.out.println("Error at RNB's functions");
        }
        long check_time = empty.measureAlgorithmTimeMillis(1000,1000);
        System.out.println("Time to generate Empty Maze is " + check_time + " milliseconds");




    }
}
