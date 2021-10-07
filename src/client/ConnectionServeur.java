package client;

import Workflow.MessageServeur;
import serveur.ClientListener;
import Workflow.MessageClient;

import javax.swing.*;
import java.io.*;
import java.net.Socket;

public class ConnectionServeur extends Thread{
    private final Socket socket;
    private final ClientListener clientListener;
    private JTextPane jTextPane;

    public ConnectionServeur(Socket socket, JTextPane jTextPane) throws IOException {
        this.socket = socket;
        this.jTextPane = jTextPane;
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        this.clientListener = new ClientListener(objectInputStream);
    }
    public void run() {
        try {
            while (true) {
                MessageServeur reponse = clientListener.get_message();
                if (reponse == null)break;
                jTextPane.setText(jTextPane.getText() + "\n");
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
