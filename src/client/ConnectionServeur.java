package client;

import Workflow.MessageServeur;
import serveur.ClientListener;
import Workflow.MessageClient;

import java.io.*;
import java.net.Socket;

public class ConnectionServeur extends Thread{
    private final Socket socket;
    private final ClientListener clientListener;

    public ConnectionServeur(Socket socket) throws IOException {
        this.socket = socket;
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        this.clientListener = new ClientListener(objectInputStream);
    }
    public void run() {
        try {
            while (true) {
                MessageServeur reponse = clientListener.get_message();
                if (reponse == null)break;
                System.out.println("Serveur > " + reponse);
            }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    this.clientListener.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    }

}
