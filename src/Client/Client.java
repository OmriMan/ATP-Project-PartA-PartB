package Client;

import java.net.InetAddress;
import java.net.Socket;
import java.security.PrivateKey;

public class Client{
    private InetAddress serverIP;
    private int serverPort;
    private IClientStrategy strategy;

    public Client(InetAddress serverIP, int serverPort, IClientStrategy strategy) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
        this.strategy = strategy;
    }

    public void communicateWithServer(){
        try{
            Socket serverSocket = new Socket(this.serverIP, this.serverPort);
            System.out.println("Connected to server - IP = " + this.serverIP + ", Port = " + this.serverPort);
            this.strategy.clientStrategy(serverSocket.getInputStream(), serverSocket.getOutputStream());
            serverSocket.close();
        }catch (Exception e){
            System.out.println("did not connect to server");
        }
    }
}
