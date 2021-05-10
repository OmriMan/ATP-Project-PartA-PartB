package Server;

import IO.Convertion;
import algorithms.mazeGenerators.Maze;
import algorithms.search.*;
import java.io.*;

public class ServerStrategySolveSearchProblem implements IServerStrategy{


    /**
     * Function that implements the solving of a maze
     * @param inFromClient Input stream from the client of the maze to solve
     * @param outToClient Output stream to the client which holds the solution of the maze
     */
    @Override
    public void ServerStrategy(InputStream inFromClient, OutputStream outToClient) {
        ObjectInputStream fromClient;
        ObjectOutputStream toClient;


        try {
            //Gets the maze from the input stream
            fromClient = new ObjectInputStream(inFromClient);
            toClient = new ObjectOutputStream(outToClient);
            Maze maze =(Maze) fromClient.readObject();

            //Gets the solving properties from the configuration file
            Configurations config = Configurations.getInstance();
            ISearchable problem_to_solve = new SearchableMaze(maze);
            ISearchingAlgorithm solver = config.getmazeSearchingAlgorithm();

            //Searches on disk if this maze has already been solved before (memoization)
            Solution sol = searchSolInMyDisc(maze);

            //If this specific maze hasn't been solved in the past
            if(sol==null)
            {
                //Solves it
                sol = solver.solve(problem_to_solve);

                //Saves the solution in System.getProperty("java.io.tmpdir")
                save(maze,sol);
            }

            //Sends solution back to the client using OutputStream
            toClient.writeObject(sol);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Function that searches for a given maze on the disk for it's solution
     * @param maze Given maze that we want to find on the disk
     * @return Returns the solution of given maze if it exists - null if it doesn't exist
     */
    private Solution searchSolInMyDisc(Maze maze)
    {
        //Each maze is saved into a new folder at "temp dir" location.
        //Each folder's name is the maze's meta data in the byte format that Convertion.ConvertIntToByteFormat() returns (for each part of the meta data).
        //If there are multiple mazes with the same meta data, they will be saved in the same folder, and each maze will receives a unique number (starting from 0).

        //For example:
        //A maze that start position is {3,0} and goal position {8,10}, with size 10X10 will be saved -->
        //  in a folder named:  _0_3_0_0_0_8_0_10_0_10_0_10_, and the file will be named "0"
        //If a second maze with the same meta data is created (but different maze matrix), it will be created inside that same folder and receive the name "1"

        //The file itself contains 2 objects. First object is the maze.toByteArray(). Second object is the maze's solution

        Solution sol=null;

        //Finds the path to the adequate folder and appends to it the maze's meta data in the correct format (as shown in the example above)
        int[] meta_data = Convertion.MazeToMetaDataArray(maze);
        String path = PathCreatorFromMetaData(meta_data);

        //Creates the file and directory (only if doesn't exist)
        File file = new File(path);
        file.mkdirs();

        try {
            //Checks if the folder already exists
            if (file.exists()) {

                //If it exists - we have to check every single file inside the folder and compare their maze's grid to the given maze's grid
                String[] pathnames = file.list();
                for (String pathname : pathnames) {

                    //Reads the first object from each file
                    String real_path_in_folder = path+System.getProperty("file.separator")+pathname;
                    ObjectInputStream in = new ObjectInputStream(new FileInputStream(real_path_in_folder));
                    Object[] grid = (Object[]) in.readObject();

                    //Compares the given maze's grid to each file
                    if(((byte[])grid[0]).equals(maze.toByteArray()))
                    {
                        //If found - updates the solution and returns it
                        sol = (Solution)grid[1];
                        return sol;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //If given maze hasn't been solved in the past - returns null
        return null;
    }


    /**
     * Create and save a new file - fill this file with an array Object[] size 2 in the following format:
     * Index 0 - maze.toByteArray()
     * Index 1 - Solution object of the maze
     * @param maze given maze to save
     * @param sol given solution to save
     */
    private void save(Maze maze,Solution sol)
    {

        //Extracts the meta data from the maze
        int[] metadata = Convertion.MazeToMetaDataArray(maze);

        //Creates a new path
        String path = PathCreatorFromMetaData(Convertion.MazeToMetaDataArray(maze));

        //Creates the directory
        File file = new File(path);
        file.mkdirs();

        try {

            if (file.exists()) {

                int serial_number = file.list().length;//the serial number of this solution

                String sol_name = path +System.getProperty("file.separator")+ serial_number;

                //Creates the file and write to it the maze's grid and the solution
                File sol_file = new File(sol_name);
                try {
                    sol_file.createNewFile();
                    ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(sol_file));
                    Object[] to_write = new Object[2];
                    to_write[0]=maze.toByteArray();
                    to_write[1] = sol;
                    out.writeObject(to_write);
                    out.flush();
                    out.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }



    /**
     * Function that converts a given maze's meta data into a path with the meta data in Byte array format (Convertion.ConvertIntToByteFormat)
     * @param meta_data given meta data
     * @return A path to System.getProperty("java.io.tmpdir")_(meta_data in required format)
     */

    private String PathCreatorFromMetaData(int[] meta_data)
    {
        String dir_path = System.getProperty("java.io.tmpdir");
        String fileSeparator = System.getProperty("file.separator");
        String path = dir_path + fileSeparator+ fileSeparator+"_";
        for(int i=0;i<6;i++)
        {
            byte[] temp =  Convertion.ConvertIntToByteFormat(meta_data[i]);

            for (int j = 0; j<temp.length; j++){
                String s = Integer.toString((int) temp[j]);
                path+= s +"_";
            }
        }
        return path;
    }
}
