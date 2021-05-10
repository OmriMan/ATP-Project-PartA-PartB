package test;

import Client.Client;
import Client.IClientStrategy;
import IO.MyDecompressorInputStream;
import IO.SimpleDecompressorInputStream;
import Server.Server;
import Server.Configurations;
import Server.ServerStrategyGenerateMaze;
import Server.ServerStrategySolveSearchProblem;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.search.AState;
import algorithms.search.Solution;

import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.ServerError;
import java.util.ArrayList;
import java.util.Properties;

public class RunCommunicateWithServers {
    public static void main(String[] args) {

            try(InputStream input = new FileInputStream(System.getProperty("user.dir") + File.separator +  "resources" + File.separator + "config.properties")) {

                Properties prop = new Properties();
                prop.load(input);

            prop.setProperty("threadPoolSize", "4");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        Initializing servers
        Server mazeGeneratingServer = new Server(5400, 1000, new ServerStrategyGenerateMaze());
        Server solveSearchProblemServer = new Server(5401, 1000, new ServerStrategySolveSearchProblem());

//        Starting servers
        solveSearchProblemServer.start();
        mazeGeneratingServer.start();

//        Communicating with servers
        CommunicateWithServer_MazeGenerating();
        CommunicateWithServer_SolveSearchProblem();

//        Stopping all servers
        mazeGeneratingServer.stop();
        solveSearchProblemServer.stop();

    }


    private static void CommunicateWithServer_MazeGenerating() {
        try {
            Client client = new Client(InetAddress.getLocalHost(), 5400, new IClientStrategy(){
                @Override
                public void clientStrategy(InputStream inFromServer, OutputStream outToServer) {
                    try {

                        ObjectOutputStream toServer = new ObjectOutputStream(outToServer);

                        toServer.flush();
                        int[] mazeDimensions = new int[]{1000, 1000};
                        toServer.writeObject(mazeDimensions);
                        //send maze dimensions to server
                        toServer.flush();
                        ObjectInputStream fromServer = new ObjectInputStream(inFromServer);
                        byte[] compressedMaze = (byte[]) fromServer.readObject(); //read generated maze (compressed with MyCompressor) from server

                        InputStream is = new MyDecompressorInputStream(new ByteArrayInputStream(compressedMaze));
                        byte[] decompressedMaze = new byte[1000*1000 + 12 /*CHANGE SIZE ACCORDING TO YOU MAZE SIZE*/]; //allocating byte[] for the decompressed maze -
                        is.read(decompressedMaze); //Fill decompressedMaze with bytes


                        Maze maze = new Maze(decompressedMaze);


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            });

            client.communicateWithServer();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    private static void CommunicateWithServer_SolveSearchProblem() {
        try {
            Client client = new Client(InetAddress.getLocalHost(), 5401,new
                    IClientStrategy(){
                        @Override
                        public void clientStrategy(InputStream inFromServer, OutputStream outToServer) {
                            try {
                                ObjectOutputStream toServer = new ObjectOutputStream(outToServer);
                                ObjectInputStream fromServer = new ObjectInputStream(inFromServer);
                                toServer.flush();
                                MyMazeGenerator mg = new MyMazeGenerator();
                                Maze maze = mg.generate(700, 700);

                                toServer.writeObject(maze); //send maze to server
                                toServer.flush();

                                Solution mazeSolution = (Solution) fromServer.readObject();

                                //read generated maze (compressed with MyCompressor) from server

                                //Print Maze Solution retrieved from the server
                                System.out.println(String.format("Solution steps: %s", mazeSolution));
                                ArrayList<AState> mazeSolutionSteps = mazeSolution.getSolutionPath();
                                for (int i = 0; i < mazeSolutionSteps.size(); i++) {
                                    System.out.println(String.format("%s. %s", i,
                                            mazeSolutionSteps.get(i).toString()));
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
            client.communicateWithServer();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}


