package Server;

import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.EmptyMazeGenerator;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.SimpleMazeGenerator;
import algorithms.search.ASearchingAlgorithm;
import algorithms.search.BestFirstSearch;
import algorithms.search.BreadthFirstSearch;
import algorithms.search.DepthFirstSearch;

import java.io.*;
import java.util.HashMap;
import java.util.Properties;

public class Configurations {
    private static Configurations instance = null;
    private Properties property;
    private InputStream input;
    private HashMap<String, AMazeGenerator> MazeGeneratorMap;
    private HashMap<String, ASearchingAlgorithm> SearchingAlgoMap;


    /**
     * SingleTone design pattern - returns an instance of the object, creates it if it doesn't exist yet
     * @return returns an instance of the object
     */
    public static Configurations getInstance(){
        if (Configurations.instance == null){
            Configurations.instance = new Configurations();
        }
        return Configurations.instance;
    }

    /**
     * Constructor - SingleTone design pattern
     */
    private Configurations(){


        //Reads the configuration file
        try {
            this.input = new FileInputStream(System.getProperty("user.dir") + File.separator + "resources" + File.separator + "config.properties");
            this.property = new Properties();
            this.property.load(this.input);

        } catch (Exception e) {
            e.printStackTrace();
        }

            //Arrange the data
            this.MazeGeneratorMap = new HashMap<>();
            this.SearchingAlgoMap = new HashMap<>();

            this.MazeGeneratorMap.put("EmptyMazeGenerator", new EmptyMazeGenerator());
            this.MazeGeneratorMap.put("SimpleMazeGenerator", new SimpleMazeGenerator());
            this.MazeGeneratorMap.put("MyMazeGenerator", new MyMazeGenerator());

            this.SearchingAlgoMap.put("BreadthFirstSearch", new BreadthFirstSearch());
            this.SearchingAlgoMap.put("DepthFirstSearch", new DepthFirstSearch());
            this.SearchingAlgoMap.put("BestFirstSearch", new BestFirstSearch());

            //Set an initial value
            setProperty(4, "MyMazeGenerator", "BestFirstSearch");
    }


    //Setters
    public void setProperty(int num_of_threads, String maze_generating_algo, String maze_search_algo) {
        try (OutputStream output = new FileOutputStream(System.getProperty("user.dir") + File.separator + "resources" + File.separator + "config.properties")) {
            this.property.setProperty("threadPoolSize", Integer.toString(num_of_threads));
            this.property.setProperty("mazeGeneratingAlgorithm", maze_generating_algo);
            this.property.setProperty("mazeSearchingAlgorithm", maze_search_algo);

            this.property.store(output, "");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setThreadPoolSize(int num_of_threads){
        this.property.setProperty("threadPoolSize", Integer.toString(num_of_threads));
    }

    public void setMazeGeneratingAlgorithm(String maze_generating_algo){
        this.property.setProperty("mazeGeneratingAlgorithm", maze_generating_algo);
    }

    public void setMazeSearchingAlgorithm(String maze_searching_algo){
        this.property.setProperty("mazeSearchingAlgorithm", maze_searching_algo);
    }

    //Getters
    public int getThreadPoolSize(){return Integer.parseInt(this.property.getProperty("threadPoolSize"));}

    public AMazeGenerator getmazeGeneratingAlgorithm() throws CloneNotSupportedException {return ((AMazeGenerator)this.MazeGeneratorMap.get(this.property.getProperty("mazeGeneratingAlgorithm")).clone() );}

    public ASearchingAlgorithm getmazeSearchingAlgorithm() throws CloneNotSupportedException {return (ASearchingAlgorithm) this.SearchingAlgoMap.get(this.property.getProperty("mazeSearchingAlgorithm")).clone();}




}

