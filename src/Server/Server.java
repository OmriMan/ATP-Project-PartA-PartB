package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private int port;
    private IServerStrategy strategy;
    private int listeningIntervalMS;
    private boolean stop;
    private ExecutorService threadPool;


    /**
     * Constructor - creates a new Server
     * @param port Port number to connect to
     * @param listeningIntervalMS Intervals between connection attempts
     * @param strategy Strategy of the server
     */
    public Server(int port,int listeningIntervalMS, IServerStrategy strategy){
        this.port = port;
        this.listeningIntervalMS = listeningIntervalMS;
        this.strategy = strategy;
        this.threadPool = Executors.newCachedThreadPool();
    }


    /**
     * Function that creates a new thread for the server and call run function
     */
    public void start(){
        new Thread(()-> {run();}).start();

    }

    /**
     * Function that turns on the server which looks for client connections to it
     */
    private void run(){
        //Creates a new Socket
        try{
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(listeningIntervalMS);
            System.out.println("Starting server at port = " + this.port);

            //Iterates until boolean variable stop changes to true
            //At each iteration checks for a new client connection
            while(!stop){
                try{
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("Client accepted: " + clientSocket.toString());

                    //If a client connects, he gets a new thread from the thread pool to handle him
                    this.threadPool.submit(()->{handleClient(clientSocket);});

                }catch (SocketTimeoutException e){
                    System.out.println("Socket timeout");
                }

            }
            //Closes the socket and thread pool NOW - disconnects clients that are still connected
            serverSocket.close();
            this.threadPool.shutdownNow();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Function that receives a socket and applies a strategy to it
     * @param clientSocket the socket
     */
    private void handleClient(Socket clientSocket){
        try {
            strategy.applyStrategy(clientSocket.getInputStream(), clientSocket.getOutputStream());
            System.out.println("Done handling client: " + clientSocket.toString());
            clientSocket.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Function that stops the server (by changing the stop variable to true) and disconnects all clients
     */
    public void stop(){
        System.out.println("Stopping server...");
        stop = true;
    }
}
