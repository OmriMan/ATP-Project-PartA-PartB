package algorithms.mazeGenerators;
import java.util.ArrayList;
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
        ArrayList<Position> Frontier_list = new ArrayList<>();
        Frontier_list.addAll(Find_frontier_cells(x,y, row, col,matrix));

        //(5) While the list of Frontier cells is not empty
        while(!Frontier_list.isEmpty()){

            //(5.1) Pick a random frontier cell from the list and remove it
            Position random_frontier_cell = Frontier_list.remove(rand.nextInt(Frontier_list.size()));

            //(5.2) Compute frontier cells of the random frontier cell from the list and adds them to list
            Frontier_list.addAll(Find_frontier_cells(random_frontier_cell.getRowIndex(),random_frontier_cell.getColumnIndex(), row, col, matrix));

            //(5.2) Compute all neighbors of random frontier cell
            ArrayList<Position> Neighbor_list = new ArrayList<>();
            Neighbor_list.addAll(Find_neighbor_cells(random_frontier_cell.getRowIndex(), random_frontier_cell.getColumnIndex(), row, col, matrix));

            //Pick a random Neighbor cell
            Position random_neighbor_cell = Neighbor_list.get(rand.nextInt(Neighbor_list.size()));

            //(5.2) Connects the frontier cell to the neighbor by setting the cell in between and the frontier in "Passage" state
            matrix = Make_passage(random_neighbor_cell, random_frontier_cell, matrix);

        }

        Position start = new Position(2,0);
        Position end = new Position(row-1,6);
        Maze maze = new Maze(matrix, start, end);

        return maze;



    }


    //@@@@@@@@@@@@@@@A little code duplication - is there a better way??@@@@@@@@@@@@@2

    //Find all cells at distance 2 from (x,y) that are in "Blocked" state (1)
    private ArrayList<Position> Find_frontier_cells(int x, int y, int row, int col, int[][] matrix){
        ArrayList<Position> frontier_list = new ArrayList<>();
        Position P;

        //Checks (x-2, y)
        if (x-2 >= 0){
            if (matrix[x-2][y] == 1){
                P = new Position(x-2, y);
                frontier_list.add(P);
            }
        }

        //Checks (x+2, y)
        if (x+2 <= row-1) {
            if (matrix[x + 2][y] == 1) {
                P = new Position(x + 2, y);
                frontier_list.add(P);
            }
        }

        //Checks (x, y-2)
        if (y-2 >= 0){
            if (matrix[x][y-2] == 1){
                P = new Position(x, y-2);
                frontier_list.add(P);
            }
        }

        //Checks (x, y+2)
        if (y+2 <= col-1){
            if (matrix[x][y+2] == 1){
                P = new Position(x, y+2);
                frontier_list.add(P);
            }
        }

        return frontier_list;

    }


    //Find all cells at distance 2 that are in "Passage" state (0)
    private ArrayList<Position> Find_neighbor_cells(int x, int y, int row, int col, int[][] matrix){
        ArrayList<Position> neighbor_list = new ArrayList<>();
        Position P;

        //Checks (x-2, y)
        if (x-2 >= 0){
            if (matrix[x-2][y] == 0){
                P = new Position(x-2, y);
                neighbor_list.add(P);
            }
        }


        //Checks (x+2, y)
        if (x+2 <= row-1){
            if (matrix[x+2][y] == 0){
                P = new Position(x+2, y);
                neighbor_list.add(P);
            }
        }

        //Checks (x, y-2)
        if (y-2 >= 0){
            if (matrix[x][y-2] == 0){
                P = new Position(x, y-2);
                neighbor_list.add(P);
            }
        }

        //Checks (x, y+2)
        if (y+2 <= col-1){
            if (matrix[x][y+2] == 0){
                P = new Position(x, y+2);
                neighbor_list.add(P);
            }
        }

        return neighbor_list;

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
