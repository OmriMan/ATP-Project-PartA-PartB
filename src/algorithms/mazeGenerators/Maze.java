package algorithms.mazeGenerators;
import IO.Convertion;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;


/**
 * Class that represents a 2D Maze.
 */
public class Maze implements Serializable {
    private int[][] maze_matrix;
    private Position start_pos;
    private Position goal_pos;

    /**
     *Constructor
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
     * Default constructor with no parameter - in case size of maze not big enough
     */
    public Maze(){
        this.maze_matrix = null;
        this.start_pos = null;
        this.goal_pos = null;
    }

    /**
     * Decompression Constructor - Builds a Maze out of a compressed byte array
     * Meta data on the maze is held in 12 first indexes, the rest is the maze content
     * @param b Byte array of compressed meta data and content of maze
     */
    public Maze(byte[] b){

        //Checks that argument has all the necessary data
        if (b.length < 16){
            try{
                throw new Exception("Cannot decompress maze - data is missing");

            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }

        //Retrieves the meta data in the desired format
        int Start_row = ((b[0]&0xff)*256) + (b[1]&0xff);
        int Start_col = ((b[2]&0xff)*256) + (b[3]&0xff);
        int Goal_row = ((b[4]&0xff)*256) + (b[5]&0xff);
        int Goal_col = ((b[6]&0xff)*256) + (b[7]&0xff);
        int Size_row = ((b[8]&0xff)*256) + (b[9]&0xff);
        int Size_col = ((b[10]&0xff)*256) + (b[11]&0xff);

        //Creates new maze elements that we will assign later to the object itself
        this.start_pos = new Position(Start_row, Start_col);
        this.goal_pos = new Position(Goal_row, Goal_col);
        this.maze_matrix = new int[Size_row][Size_col];

        int row_counter = 0;
        int col_counter = 0;


        //Decompresses the maze content
        for (int i = 12; i < b.length; i++) {

            //Checks if we need to downline (go to next row)
            if (col_counter == Size_col - 1) {
                setMaze_matrix_by_index(row_counter,col_counter,(int)b[i]);
                col_counter = 0;
                row_counter++;
            }

            else{
                setMaze_matrix_by_index(row_counter,col_counter,(int)b[i]);
                col_counter++;
            }

        }


    }
    /**
     * Getters
     * @return start or goal by order(Position)
     */
    public Position getStartPosition() {
        if (this.start_pos == null){
            return null;
        }
        return start_pos;
    }

    public Position getGoalPosition() {
        if (this.goal_pos == null){
            return null;
        }
        return goal_pos;
    }

    public int[][] getMaze_matrix() {
        return maze_matrix;
    }

    public void setMaze_matrix_by_index(int row,int col,int val) {
        this.maze_matrix[row][col] = val;
    }


    /**
     * print the maze by order - maybe better implement tostring() and just call it from here ?
     */
    public void print(){
        if (this.getMaze_matrix() == null){
            System.out.println("Maze does not exist");
            return;
        }

        for(int i=0;i<this.getMaze_matrix().length;i++){
            System.out.print("{ ");
            for(int j=0;j<this.getMaze_matrix()[0].length -1 ;j++){
                if (Is_Start(i,j)){
                    System.out.print("S ");
                }
                else if (Is_Goal(i,j)){
                    System.out.print("E ");
                }
                else
                    System.out.print(this.getMaze_matrix()[i][j] +" ");
            }
            if (Is_Goal(i,this.getMaze_matrix()[0].length -1)) {
                System.out.println("E }");
            }
            else if (Is_Start(i,this.getMaze_matrix()[0].length -1)) {
                System.out.println("S }");
            }
            else {
                System.out.println(this.getMaze_matrix()[i][this.getMaze_matrix()[0].length -1] + " }");
            }
        }
    }

    /**
     * check if its start or goal position
     * @param row - number of rows
     * @param col - number of columns
     * @return Boolean value
     */
    public boolean Is_Start(int row,int col)
    {
        return row == this.getStartPosition().getRowIndex() && col == this.getStartPosition().getColumnIndex();
    }

    public boolean Is_Goal(int row,int col)
    {
        return row == this.getGoalPosition().getRowIndex() && col == this.getGoalPosition().getColumnIndex();
    }


    /**
     * Function that converts the meta data and the maze itself into a byte array.
     * Format of meta data is: (Array[i]*256)+Array[i+1] = number (0 <= i <= 11)
     * @return index 0-11 holds the start position, end position, row size, col size, index 12-end holds content of the maze
     */
    public byte[] toByteArray(){
        //Organizes data

        int[] maze_data = Convertion.MazeToMetaDataArray(this);

        //Adds the data of Start Position, Goal Position and size of matrix in desired format
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        for (int i = 0; i < maze_data.length; i++){
            try{
                out.write(Convertion.ConvertIntToByteFormat(maze_data[i]));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //Converts and adds the 2D matrix of the maze to the byte array
        try {
            out.write(Convertion.ConvertBinaryIntMatrixToByte(this));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return out.toByteArray();
    }






}

