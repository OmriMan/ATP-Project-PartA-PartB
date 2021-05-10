package Server;

import IO.SimpleCompressorOutputStream;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.*;

import java.io.*;

public class ServerStrategyGenerateMaze implements IServerStrategy{

    /**
     * Function that implements the generation of a maze using a given size, compresses it into a temporary byte array, then sends that byte array back to the client
     * @param inFromClient Input stream from the client that holds an int array (size 2) - represents the row and column size of the matrix
     * @param outToClient Output stream to the client - will get a byte array that represents a compressed maze
     */
    @Override
    public void ServerStrategy(InputStream inFromClient, OutputStream outToClient) throws IOException {
        //Creates all the streams
        ObjectOutputStream toClient = new ObjectOutputStream(outToClient);//Input stream
        ObjectInputStream fromClient = new ObjectInputStream(inFromClient);//Output stream
        ByteArrayOutputStream temp_byte_array = new ByteArrayOutputStream();//Temporary helper byte array
        SimpleCompressorOutputStream toClientCompressor = new SimpleCompressorOutputStream(temp_byte_array);//Byte array Compressor

        //Extracts the row and column size from the input stream
        int[] arr = new int[2];
        try{
            arr =(int[]) fromClient.readObject();
        }catch (Exception e){
            System.out.println("Input size of maze is not legal");
        }

        //Generates a new maze
        IMazeGenerator mazeGenerator = new MyMazeGenerator();
        Maze maze;
        try {
            maze = mazeGenerator.generate(arr[0], arr[1]);
            toClientCompressor.write(maze.toByteArray()); //Compresses the maze and sends it to the temporary byte array
            toClient.writeObject(temp_byte_array.toByteArray());//Sends the temporary byte array (compressed maze) back to the client using OutputStream
            toClient.flush();

        } catch (Exception e) {
            System.out.println("Couldn't generate the maze");
        }

    }
}
