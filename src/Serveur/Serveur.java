package Serveur;

import dispatcher.Dispatcher;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class Serveur extends Thread{

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private ServeurSender serveurSender;
    private ServeurListener serveurListener;
    private Dispatcher dispatcher;
    private int port;

    public Serveur(int port, Dispatcher dispatcher) throws IOException {
        this.port = port;
        this.dispatcher = dispatcher;
    }

    public void run(){
        this.dispatcher = dispatcher;
        try {
            serverSocket = new ServerSocket(port);
            clientSocket = serverSocket.accept();
            serveurSender = new ServeurSender(new PrintWriter(clientSocket.getOutputStream(),true));
            serveurListener = new ServeurListener(port, new BufferedReader(new InputStreamReader(clientSocket.getInputStream())),clientSocket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
