package Main;

import Serveur.Serveur;
import Serveur.ServeurListener;
import Serveur.ServeurSender;
import dispatcher.Dispatcher;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class ConnectionThread extends Thread{
    private final Socket socket;
    private Dispatcher dispatcher;
    private PrintWriter out;
    private BufferedReader in;

    public ConnectionThread(Socket socket, Dispatcher dispatcher) {
        this.socket = socket;
        this.dispatcher = dispatcher;
    }
    public void run() {
        try {
            Random random = new Random();
            int port = random.nextInt(12000 - 11000) + 11000;

            System.out.println("Nouveau serveur en " + port);
            new Serveur(port,dispatcher).start();
            out = new PrintWriter(socket.getOutputStream(), true);
            out.println(port);
            System.out.println("Socket " + port + " fermée");
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

}
