package Client;
import java.net.InetAddress;
import java.net.Socket;


public class Client{
    private InetAddress serverIP;
    private int serverPort;
    private IClientStrategy strategy;


    /**
     * Constructor
     * @param serverIP serverIP
     * @param serverPort serverPort
     * @param strategy strategy that will be used
     */
    public Client(InetAddress serverIP, int serverPort, IClientStrategy strategy) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
        this.strategy = strategy;
    }

    /**
     * Function that applies the given strategy to the client with the server
     */
    public void communicateWithServer(){
        try{
            Socket serverSocket = new Socket(this.serverIP, this.serverPort);
//            System.out.println("Connected to server - IP = " + this.serverIP + ", Port = " + this.serverPort);
            this.strategy.clientStrategy(serverSocket.getInputStream(), serverSocket.getOutputStream());
            serverSocket.close();
        }catch (Exception e){
            e.printStackTrace();
//            System.out.println("did not connect to server");
        }
    }
}
