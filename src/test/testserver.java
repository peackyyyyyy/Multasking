package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class testserver {

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("En ecoute");
        clientSocket = serverSocket.accept();
        System.out.println("conexxion start");
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String msg ;
        while(!(msg = in.readLine()).equals("exit")){
            System.out.println("msg : " + msg);
        }
        in.close();
        out.close();
        clientSocket.close();
        System.out.println("fin connexion");
    }

    public void stop() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }
    public static void main(String[] args) throws IOException {
        testserver server=new testserver();
        server.start(6666);
    }
}
