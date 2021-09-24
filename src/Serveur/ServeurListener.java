package Serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

public class ServeurListener{
    private final BufferedReader bufferedReader;
    private Socket socket;
    private int port;

    public ServeurListener(int port, BufferedReader bufferedReader, Socket socket) throws IOException {
        this.port = port;
        this.socket = socket;
        this.bufferedReader = bufferedReader;
        String fromClient;
        while (!(fromClient = bufferedReader.readLine()).equals("exit")){
            System.out.println("port : " + port + " msg : " + fromClient);
        }
        bufferedReader.close();
        socket.close();
        System.out.println("Connexion fermé");
        return;
    }

    public String get_task() throws IOException {
        return this.bufferedReader.readLine();
    }
}
