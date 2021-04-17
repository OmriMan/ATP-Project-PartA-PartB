package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.Position;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BestFirstSearchTest {

    BestFirstSearch algo = new BestFirstSearch();



    //Checks that getName returns the correct name
    @Test
    void getName(){
        assertEquals("Best First Search", algo.getName());
    }

    //Checks the cost of path for a known small maze
    @Test
    void Check_cost_4x4() throws Exception{
        //Generates the maze
        int[][] matrix = new int[4][4];
        Position start_p = new Position(0,0);
        Position end_p = new Position(1,3);
        Maze maze = new Maze(matrix, start_p, end_p);

        //Designs it to have the desired path
        maze.setMaze_matrix_by_index(0,2,1);
        maze.setMaze_matrix_by_index(0,3,1);
        maze.setMaze_matrix_by_index(2,1,1);
        maze.setMaze_matrix_by_index(2,3,1);
        maze.setMaze_matrix_by_index(3,3,1);

        //Solves the maze using BestFirstSearch then checks the cost
        SearchableMaze s_maze = new SearchableMaze(maze);
        Solution sol = algo.solve(s_maze);
        ArrayList<AState> array_sol = sol.getSolutionPath();
        assertEquals(35, array_sol.get(array_sol.size()-1).getCost());
    }

    //Checks the cost of path for a known big maze
    @Test
    void Check_cost_50x4() throws Exception{
        //Generates the maze
        int[][] matrix = new int[50][4];
        Position start_p = new Position(0,0);
        Position end_p = new Position(1,3);
        Maze maze = new Maze(matrix, start_p, end_p);

        //Designs it to have the desired path
        maze.setMaze_matrix_by_index(0,2,1);
        maze.setMaze_matrix_by_index(0,3,1);
        maze.setMaze_matrix_by_index(2,1,1);
        maze.setMaze_matrix_by_index(2,3,1);
        maze.setMaze_matrix_by_index(3,3,1);

        //Solves the maze using BestFirstSearch then checks the cost
        SearchableMaze s_maze = new SearchableMaze(maze);
        Solution sol = algo.solve(s_maze);
        ArrayList<AState> array_sol = sol.getSolutionPath();
        assertEquals(35, array_sol.get(array_sol.size()-1).getCost());
    }

    //Checks that a 1000x1000 maze is solved in under a minute using BestFirstSearch
    @Test
    void Check_time_1000x1000() throws Exception{
        Maze maze = new MyMazeGenerator().generate(1000,1000);
        SearchableMaze s_maze = new SearchableMaze(maze);
        long start_time = System.currentTimeMillis();
        algo.solve(s_maze);
        long end_time = System.currentTimeMillis();
        long tot_time = end_time-start_time;
        assertTrue(tot_time<=60000);
    }

    //Checks that the last state in the solution equals the goal position of the maze
    @Test
    void Check_Goal() throws Exception{
       Maze maze = new MyMazeGenerator().generate(1000,1000);
       Position end_p = maze.getGoalPosition();
       SearchableMaze s_maze = new SearchableMaze(maze);
       Solution sol = algo.solve(s_maze);
       ArrayList<AState> array_sol = sol.getSolutionPath();
       MazeState solver_goal_state = (MazeState) array_sol.get(array_sol.size()-1);
       assertTrue(end_p.equals((solver_goal_state.getPos())));
    }

    //Checks that solve method throws Exception if argument is null
    @Test
    void Check_Null(){
        try{
            Solution sol = algo.solve(null);
        }
        catch (Exception e){
            assertTrue(true);
            return;

        }
        fail();
    }

}