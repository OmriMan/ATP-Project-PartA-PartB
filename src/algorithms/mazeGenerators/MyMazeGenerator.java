package algorithms.mazeGenerators;
import java.util.HashSet;
import java.util.HashSet;
import java.util.Random;

public class MyMazeGenerator extends AMazeGenerator {


    @Override
    public Maze generate(int row, int col) {
        int[][] matrix = Create_2D_matrix(row, col);

        //(3) Fill all cells in matrix with state "Blocked"
        for (int i = 0; i<row; i++){
            for (int j = 0; j<col; j++) {
                matrix[i][j] = 1;
            }
        }

        //(4) Pick a random cell in matrix and set it to state "Passage"
        Random rand = new Random();
//        Position random_p = new Position(rand.nextInt(row), rand.nextInt(col));
        int x = rand.nextInt(row);
        int y = rand.nextInt(col);
        matrix[x][y] = 0;

        //(4)Compute it's frontier cell and adds them to a List
        HashSet<Position> Frontier_set = new HashSet<>();
        Frontier_set.addAll(Find_frontier_cells(x,y, row, col,matrix));

        //(5) While the list of Frontier cells is not empty
        while(!Frontier_set.isEmpty()){

            //(5.1) Pick a random frontier cell from the list and remove it
            Position random_frontier_cell = Get_random_from_set(Frontier_set);
            Frontier_set.remove(random_frontier_cell);

            //(5.2) Compute frontier cells of the random frontier cell from the list and adds them to list
            Frontier_set.addAll(Find_frontier_cells(random_frontier_cell.getRowIndex(),random_frontier_cell.getColumnIndex(), row, col, matrix));

            //(5.2) Compute all neighbors of random frontier cell
            HashSet<Position> Neighbor_set = new HashSet<>();
            Neighbor_set.addAll(Find_neighbor_cells(random_frontier_cell.getRowIndex(), random_frontier_cell.getColumnIndex(), row, col, matrix));

            //Pick a random Neighbor cell
            Position random_neighbor_cell = Get_random_from_set(Neighbor_set);

            //(5.2) Connects the frontier cell to the neighbor by setting the cell in between and the frontier in "Passage" state
            matrix = Make_passage(random_neighbor_cell, random_frontier_cell, matrix);

        }

        Position start = new Position(2,0);
        Position end = new Position(row-1,6);
        Maze maze = new Maze(matrix, start, end);

        return maze;



    }


    //Helper function that returns a random object from a given Set
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


    //@@@@@@@@@@@@@@@A little code duplication - is there a better way??@@@@@@@@@@@@@2

    //Find all cells at distance 2 from (x,y) that are in "Blocked" state (1)
    private HashSet<Position> Find_frontier_cells(int x, int y, int row, int col, int[][] matrix){
        HashSet<Position> Frontier_set = new HashSet<>();
        Position P;

        //Checks (x-2, y)
        if (x-2 >= 0){
            if (matrix[x-2][y] == 1){
                P = new Position(x-2, y);
                Frontier_set.add(P);
            }
        }

        //Checks (x+2, y)
        if (x+2 <= row-1) {
            if (matrix[x + 2][y] == 1) {
                P = new Position(x + 2, y);
                Frontier_set.add(P);
            }
        }

        //Checks (x, y-2)
        if (y-2 >= 0){
            if (matrix[x][y-2] == 1){
                P = new Position(x, y-2);
                Frontier_set.add(P);
            }
        }

        //Checks (x, y+2)
        if (y+2 <= col-1){
            if (matrix[x][y+2] == 1){
                P = new Position(x, y+2);
                Frontier_set.add(P);
            }
        }

        return Frontier_set;

    }


    //Find all cells at distance 2 that are in "Passage" state (0)
    private HashSet<Position> Find_neighbor_cells(int x, int y, int row, int col, int[][] matrix){
        HashSet<Position> Neighbor_set = new HashSet<>();
        Position P;

        //Checks (x-2, y)
        if (x-2 >= 0){
            if (matrix[x-2][y] == 0){
                P = new Position(x-2, y);
                Neighbor_set.add(P);
            }
        }


        //Checks (x+2, y)
        if (x+2 <= row-1){
            if (matrix[x+2][y] == 0){
                P = new Position(x+2, y);
                Neighbor_set.add(P);
            }
        }

        //Checks (x, y-2)
        if (y-2 >= 0){
            if (matrix[x][y-2] == 0){
                P = new Position(x, y-2);
                Neighbor_set.add(P);
            }
        }

        //Checks (x, y+2)
        if (y+2 <= col-1){
            if (matrix[x][y+2] == 0){
                P = new Position(x, y+2);
                Neighbor_set.add(P);
            }
        }

        return Neighbor_set;

    }


    //@@@@@@@@@@@@maybe returning the whole matrix costs us a lot more in time efficiency?@@@@@@@@@@
    //@@@@@@@@@@@@maybe working on the maze itself and changing it instead of copying the entire matrix and then returning it is more fast?@@@@@@@@@@
    //Creates a Passage between two cells that are frontiers to each other
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
}
