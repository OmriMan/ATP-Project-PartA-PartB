import algorithms.mazeGenerators.*;

public class try1 {
    public static void main(String[] args) {
        MyMazeGenerator mymaze = new MyMazeGenerator();
        long start_time = System.currentTimeMillis();
        Maze my_maze = mymaze.generate(10,10);
        my_maze.print();
        long end_time = System.currentTimeMillis();
        System.out.println("time to build and print Simple Maze 100X100 = " + (end_time - start_time) + " milliseconds");
        if (my_maze.getStartPosition().equals(my_maze.getGoalPosition())){
            System.out.println("Error at RNB's functions");
        }
        long check_time = mymaze.measureAlgorithmTimeMillis(8,8);
        System.out.println("Time to generate Simple Maze is " + check_time + " milliseconds");
        EmptyMazeGenerator try1 = new EmptyMazeGenerator();
        Maze for_c = try1.generate(5,5);
        for_c.print();



    }
}
