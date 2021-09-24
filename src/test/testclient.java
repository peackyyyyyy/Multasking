package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class testclient {

    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        System.out.println("Socket crée");
    }

    public void sendMessage(String msg) throws IOException {
        out.println(msg);
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }

    public static void main(String[] args) throws IOException {
        testclient t = new testclient();
        t.startConnection("127.0.0.1", 11684);
        String msg = null;
        Scanner scan = new Scanner(System.in);
        do{
            msg = scan.nextLine();
            t.sendMessage(msg);
        } while(!msg.equals("exit"));
        t.stopConnection();
        System.out.println("fin connexion");
    }
}
