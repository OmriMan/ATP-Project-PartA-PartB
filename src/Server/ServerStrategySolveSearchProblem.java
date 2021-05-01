package Server;

import IO.SimpleDecompressorInputStream;
import algorithms.mazeGenerators.Maze;
import algorithms.search.*;

import java.io.*;

public class ServerStrategySolveSearchProblem implements IServerStrategy{


    /**
     * Function that implements the solving of a maze
     * @param inFromClient
     * @param outToClient
     */
    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) {
        ObjectInputStream fromClient;
        ObjectOutputStream toClient;
//        String tempDirectoryPath = System.getProperty("java.io.tmpdir");

        try {
            fromClient = new ObjectInputStream(inFromClient);
            toClient = new ObjectOutputStream(outToClient);
            Maze maze =(Maze) fromClient.readObject();

            ISearchable problem_to_solve = new SearchableMaze(maze);
            ISearchingAlgorithm solver = new BestFirstSearch();//@@@@@@@@@ change by config file@@@@@

            Solution sol = solver.solve(problem_to_solve);
            toClient.writeObject(sol);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
