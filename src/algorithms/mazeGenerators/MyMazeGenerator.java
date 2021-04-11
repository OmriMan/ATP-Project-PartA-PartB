package algorithms.mazeGenerators;
import java.util.HashSet;
import java.util.Random;


public class MyMazeGenerator extends AMazeGenerator {

    /**
     * Generate a maze using Prim's algorithm and sets start and goal Position
     * @param row number of rows of the maze
     * @param col number of columns of the maze
     * @return a Maze
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
        int[][] matrix = Create_2D_matrix(row, col);

        // Fills all cells in matrix with state "Blocked"
        for (int i = 0; i<row; i++){
            for (int j = 0; j<col; j++) {
                matrix[i][j] = 1;
            }
        }

        // Picks a random cell on left side of matrix and set it to state "Passage" - this will be the starting cell
        Random rand = new Random();
        int x = rand.nextInt(row);
        int y = 0;
        matrix[x][y] = 0;
        Position start = new Position(x,y);

        //Compute it's frontier cell and adds them to a Set (so no duplicates)
        HashSet<Position> Frontier_set = new HashSet<>(Find_close_cells(start, 1, row, col, matrix));
        Position random_frontier_cell;

        // While the set of Frontier cells is not empty
        while(!Frontier_set.isEmpty()){

            //Picks a random frontier cell from the set and remove it
            random_frontier_cell = Get_random_from_set(Frontier_set);
            Frontier_set.remove(random_frontier_cell);

            //Compute frontier cells of the random frontier cell from the set and adds them to set
            Frontier_set.addAll(Find_close_cells(random_frontier_cell, 1, row, col, matrix));

            // Compute all neighbors of random frontier cell
            HashSet<Position> Neighbor_set = new HashSet<>(Find_close_cells(random_frontier_cell, 0, row, col, matrix));

            //Pick a random Neighbor cell
            Position random_neighbor_cell = Get_random_from_set(Neighbor_set);

            //Connects the frontier cell to the neighbor by setting the cell in between and the frontier in "Passage" state
            matrix = Make_passage(random_neighbor_cell, random_frontier_cell, matrix);

        }

        //Sets goal cell
        Position goal = RND_Goal_Position(row, col, start, matrix);

        //Ensures goal cell will be in "Passage" state (0)
        matrix[goal.getRowIndex()][goal.getColumnIndex()] = 0;

        return new Maze(matrix, start, goal);
    }


    /**
     * Helper function that returns a random object from a given Set
     * @param Set Given set
     * @return Random Position from the given sets
     */
    private Position Get_random_from_set(HashSet<Position> Set){
        Random rand = new Random();
        int rand_int = rand.nextInt(Set.size());
        int i = 0;
        for (Position p : Set){
            if (i == rand_int){
                return p;
            }
            i++;
        }
        return null;
    }


    /**
     * Finds all cells at distance 2 from Position p - finds Frontiers (value in matrix is 1) if val = 1, finds neighbors (value in matrix is 0) if val = 0
     * @param p Position that we check cells at distance 2 from it
     * @param val Determine if function finds Frontiers(1) of neighbors(0)
     * @param row number of rows of the matrix
     * @param col number of columns of the matrix
     * @param matrix 2D int matrix
     * @return Set of Positions of cells at distance 2 which have the correct value (0/1)
     */
    private HashSet<Position> Find_close_cells(Position p, int val, int row, int col, int[][] matrix){
        HashSet<Position> Frontier_set = new HashSet<>();
        Position P;

        //Checks (x-2, y)
        if (p.getRowIndex()-2 >= 0){
            if (matrix[p.getRowIndex()-2][p.getColumnIndex()] == val){
                P = new Position(p.getRowIndex()-2, p.getColumnIndex());
                Frontier_set.add(P);
            }
        }

        //Checks (x+2, y)
        if (p.getRowIndex()+2 <= row-1) {
            if (matrix[p.getRowIndex() + 2][p.getColumnIndex()] == val) {
                P = new Position(p.getRowIndex() + 2, p.getColumnIndex());
                Frontier_set.add(P);
            }
        }

        //Checks (x, y-2)
        if (p.getColumnIndex()-2 >= 0){
            if (matrix[p.getRowIndex()][p.getColumnIndex()-2] == val){
                P = new Position(p.getRowIndex(), p.getColumnIndex()-2);
                Frontier_set.add(P);
            }
        }

        //Checks (x, y+2)
        if (p.getColumnIndex()+2 <= col-1){
            if (matrix[p.getRowIndex()][p.getColumnIndex()+2] == val){
                P = new Position(p.getRowIndex(), p.getColumnIndex()+2);
                Frontier_set.add(P);
            }
        }

        return Frontier_set;

    }


    /**
     * Creates a Passage between two cells that are at distance 2
     * @param cell Current cell
     * @param f_cell Cell to reach and make a passage to it
     * @param matrix 2D int matrix
     * @return 2D int matrix with the new Passage between cell and f_cell
     */
    private int[][] Make_passage(Position cell, Position f_cell, int[][] matrix){

        matrix[f_cell.getRowIndex()][f_cell.getColumnIndex()] = 0;

        //Checks (x,y) and (x-2,y)
        if (cell.getRowIndex() > f_cell.getRowIndex()){
            matrix[cell.getRowIndex() - 1][cell.getColumnIndex()] = 0;
        }

        //Checks (x,y) and (x+2,y)
        else if (cell.getRowIndex() < f_cell.getRowIndex()){
            matrix[cell.getRowIndex() + 1][cell.getColumnIndex()] = 0;
        }

        //Checks (x,y) and (x,y-2)
        else if (cell.getColumnIndex() > f_cell.getColumnIndex()){
            matrix[cell.getRowIndex()][cell.getColumnIndex() - 1] = 0;
        }

        //Checks (x,y) and (x,y+2)
        else if (cell.getColumnIndex() < f_cell.getColumnIndex()){
            matrix[cell.getRowIndex()][cell.getColumnIndex() + 1] = 0;
        }

        return matrix;
    }


    /**
     * Creates a random Goal Position on the right side of the matrix. If number of columns of the matrix is even, then the last column will be filled with 1's.
     * The function will check for a passage to the specific cell.
     * @param row number of rows of the matrix
     * @param col number of columns of the matrix
     * @param start_pos Position of Start
     * @param matrix 2D int matrix
     * @return Goal Position that is on the right side of the matrix and not on the same row as start position
     */
    private Position RND_Goal_Position(int row, int col, Position start_pos, int[][]matrix){
        Position Pos_to_return;
        Random rand = new Random();
        int temp_row = rand.nextInt(row);

        if (col % 2 == 1){ //col is odd, there must be cells on most right columns with 0
            while(temp_row == start_pos.getRowIndex() || matrix[temp_row][col - 1] == 1){
                temp_row = rand.nextInt(row);
            }
            Pos_to_return = new Position(temp_row, col - 1);
            return Pos_to_return;
        }

        else //col is even, all the cells in most right columns are 1's. We check that there is a Passage to the cell from the left.
        {
            while(temp_row == start_pos.getRowIndex() || matrix[temp_row][col - 2] == 1){
                temp_row = rand.nextInt(row);
            }
            Pos_to_return = new Position(temp_row, col - 1);
            return Pos_to_return;
        }
    }
}

