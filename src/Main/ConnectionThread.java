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
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            String str =in.readLine();
            System.out.println(str);
            out.println(port);
            new Serveur(port,dispatcher).run();

        }catch (IOException e) {
            e.printStackTrace();
        }

    }

}
