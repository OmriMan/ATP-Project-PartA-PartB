package test;
import IO.MyCompressorOutputStream;
import IO.MyDecompressorInputStream;
import IO.SimpleCompressorOutputStream;
import IO.SimpleDecompressorInputStream;
import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import java.io.*;
import java.util.Arrays;

public class RunCompressDecompressMaze {

    public static void main(String[] args) throws Exception {
        String mazeFileName = "savedMaze.txt";
        AMazeGenerator mazeGenerator = new MyMazeGenerator();
        Maze maze = mazeGenerator.generate(1000, 1000); //Generate new maze
        maze.print();
        System.out.println("Maze Size = {"+maze.getMaze_matrix().length+","+maze.getMaze_matrix()[0].length+"}");
        System.out.println("start : "+maze.getStartPosition());
        System.out.println("end : "+maze.getGoalPosition());
        System.out.println("-----------");
        try {
            // save maze to a file
            OutputStream out = new SimpleCompressorOutputStream(new FileOutputStream(mazeFileName));
            out.write(maze.toByteArray());
            out.flush();
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        byte savedMazeBytes[] = new byte[0];

        try {
            //read maze from file
            InputStream in = new SimpleDecompressorInputStream(new FileInputStream(mazeFileName));
            int blrblr = maze.toByteArray().length;
            savedMazeBytes = new byte[maze.toByteArray().length];
            in.read(savedMazeBytes);
            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        Maze loadedMaze = new Maze(savedMazeBytes);
        loadedMaze.print();
/*        System.out.println(loadedMaze.getMaze_matrix().length+","+loadedMaze.getMaze_matrix()[0].length);
        System.out.println("start :" + loadedMaze.getStartPosition());
        System.out.println("end :"+loadedMaze.getGoalPosition());*/
        boolean areMazesEquals = Arrays.equals(loadedMaze.toByteArray(),maze.toByteArray());
        System.out.println(String.format("Mazes equal: %s",areMazesEquals));

        //maze should be equal to loadedMaze

        if(!areMazesEquals){
            for(int i=0;i<maze.getMaze_matrix().length;i++)
            {
                for(int j=0;j<maze.getMaze_matrix()[0].length;j++)
                {
                    if(maze.getMaze_matrix()[i][j] != loadedMaze.getMaze_matrix()[i][j]){
                        System.out.println("Error at :"+i+","+j);
                        System.out.println(maze.getMaze_matrix()[i][j] - loadedMaze.getMaze_matrix()[i][j]);
                    }
                }
            }
        }
        else{
            // set output size
            final int N = 6;

            for (int i = 0; i < N; i++)
            {
                for (int j = 0; j <= 4 * N; j++)
                {
                    double d1 = Math.sqrt(Math.pow(i - N, 2) + Math.pow(j - N, 2));
                    double d2 = Math.sqrt(Math.pow(i - N, 2) + Math.pow(j - 3 * N, 2));

                    if (d1 < N + 0.5 || d2 < N + 0.5) {
                        System.out.print('*');
                    }
                    else {
                        System.out.print(' ');
                    }
                }
                System.out.print(System.lineSeparator());
            }

            for (int i = 1; i < 2 * N; i++)
            {
                for (int j = 0; j < i; j++) {
                    System.out.print(' ');
                }

                for (int j = 0; j < 4 * N + 1 - 2 * i; j++) {
                    System.out.print('*');
                }

                System.out.print(System.lineSeparator());
            }

        }
    }
}