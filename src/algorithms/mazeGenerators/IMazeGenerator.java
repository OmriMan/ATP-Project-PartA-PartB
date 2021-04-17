package algorithms.mazeGenerators;

/**
 * Interface of Maze Generators
 */
public interface IMazeGenerator {
    Maze generate(int row, int col) throws Exception;
    long measureAlgorithmTimeMillis(int row, int col) throws Exception;
}
