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
            //if this solution already exists it will return this' if not - return null
            Solution sol = searchSolInMyDisc(maze);
            if(sol==null)
            {//if we are here - we have not yet solved this maze
/*                System.out.println("sol not found - create new one");*/
                //solve it now !
                sol = solver.solve(problem_to_solve);
                //save this solution in System.getProperty("java.io.tmpdir")
                save(maze,sol);
            }
            else
                System.out.println("not create new sol, just read it from file ;-)");
            //Sends answer(solution) back to the client using OutputStream
            toClient.writeObject(sol);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private Solution searchSolInMyDisc(Maze maze)
    {//מחזיר פתרון אם שמור כזה בתיקיה הזמנית ואם לא קיים מחזיר נאל
        /*save maze as new file at temp dir in format :_byteformat as we did in maze.toByteArray()
        for maze that start {3,0} and end{8,10} size 10X10 -->
        it name will be _0_3_0_0_0_8_0_10_0_10_0_10_\serial_number_of_this_solution
        in the folder System.getProperty("java.io.tmpdir")\_0_3_0_0_0_8_0_10_0_10_0_10_
        the solution file is array Object[] in size 2
        at [0] - maze.toByteArray() as string with "," that separates the elements
        at [1] - solution as Solution Object*/
        Solution sol=null;

        //help function
        String path = FromtoByteArrayToPathAsString_Meta(maze);
        //if folder exists it doesnt open
        File file = new File(path);

        //Creating the directory if needed
        file.mkdirs();
        try {
            if (file.exists()) { //if file.exists()==True -->that mean that there is file(folder) with that name
                //need to check if its that same maze return this sol
                //System.out.println(path + "this file allready exist");
                //Populates the array with names of files and directories
                String[] pathnames = file.list();


/*                System.out.println("need to print list of files in size :"+pathnames.length);
                for(int i=0;i< pathnames.length;i++)
                {
                    System.out.println(pathnames[i]);
                }
                */
                // For each pathname in the pathnames array

                for (String pathname : pathnames) {
                    //check the files that in this folder

                    String real_path_in_folder = path+System.getProperty("file.separator")+pathname;

                    //read this file because we want to check if its the same maze
                    ObjectInputStream in = new ObjectInputStream(new FileInputStream(real_path_in_folder));
                    Object[] line = (Object[]) in.readObject();
                    //line[0] should be maze.toByteArray() as string - that how we recognize if its the same maze
                    if(((String)line[0]).equals(ByteArray_To_String(maze.toByteArray())))
                    {//this is the same maze - we already solved it! need to get the solution (solution is line[1])
                        sol = (Solution)line[1];
/*                        System.out.println("sol found !-----:-)");
                        System.out.println("Yooooooooo ani beshok ze ashkara oved !!!!!");*/
                        return sol;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            //System.out.println("Error in searchSolInMyDisc");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            //System.out.println("Error in searchSolInMyDisc");
        }
        //we didnt solve this maze yet, return null
        return null;
    }

    private String ByteArray_To_String(byte[] mazeAsByteArr)
    {
        /**
         * return maze.toByteArray() as string with "," that separates the elements
         */
        String maze_as_string ="";
        for(int i=0;i<mazeAsByteArr.length;i++)
        {
            String s = Integer.toString((int)mazeAsByteArr[i]);
            maze_as_string+= s +",";

        }
        return maze_as_string;
    }

    private String FromtoByteArrayToPathAsString_Meta(Maze maze)
    {
        /**
         * reaturn path to System.getProperty("java.io.tmpdir")/meta_data_of_maze
         * and it will be the path to the folder that
         * contains the solution of the mazes with this meta data
         */
        String dir_path = System.getProperty("java.io.tmpdir");
        String fileSeparator = System.getProperty("file.separator");
        byte[] mazeAsByteArr = maze.toByteArray();
        String path = dir_path + fileSeparator+ fileSeparator+"_";
        for(int i=0;i<12;i++)
        {
            String s = Integer.toString((int)mazeAsByteArr[i]);
            path+= s +"_";

        }
        return path;
    }

    private void save(Maze maze,Solution sol)
    {
        /**
         * create and save a new file - fill this file with an array Object[] in size 2
         * in the format :
         * at [0] - maze.toByteArray() as string with "," that separates the elements
         * at [1] - solution as Solution Object
         */
        String path = FromtoByteArrayToPathAsString_Meta(maze);
        File file = new File(path);
        //Creating the directory
        file.mkdirs();
        try {
            if (file.exists()) { //if file.exists()==True -->that mean that there is file(folder) with that name
                int serial_number = file.list().length;//the serial number of this solution
                String sol_name = FromtoByteArrayToPathAsString_Meta(maze) +System.getProperty("file.separator")+ serial_number;
//                System.out.println(sol_name);

                //creates the file itself
                File sol_file = new File(sol_name);

                try {
                    sol_file.createNewFile();
                    ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(sol_file));
                    Object[] to_write = new Object[2];
                    to_write[0]=ByteArray_To_String(maze.toByteArray());
                    to_write[1] = sol;
                    out.writeObject(to_write);
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    System.out.println("problem in ServerStrategySolveSearchProblem.saze 1" + e.getMessage());
                    e.printStackTrace();
                }
            }
        }catch (Exception e)
        {
            System.out.println("problem in ServerStrategySolveSearchProblem.saze 2" + e.getMessage());
        }
    }
}
