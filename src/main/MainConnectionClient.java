package main;
import Workflow.FirstConnection;
import Workflow.MessageClient;
import client.ConnectionServeur;
import serveur.ClientSender;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class MainConnectionClient {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        Socket socket = null;
        ConnectionServeur connectionServeur = null;
        try {
            socket = new Socket("localhost", 4444);
            connectionServeur = new ConnectionServeur(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert socket != null;
        BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));
        ClientSender clientSender = new ClientSender(new ObjectOutputStream(socket.getOutputStream()));

        new Thread(connectionServeur).start();


        while (true) {
            String name = null;
            ArrayList<String> destinataires = new ArrayList<>();
            String task = clavier.readLine();
            if (Objects.equals(task, "send")){
                while (true) {
                    System.out.println("destinataire >");
                    String destinataire = clavier.readLine();
                    if (Objects.equals(destinataire, "next"))break;
                    destinataires.add(destinataire);
                }
                System.out.println("message >");
                String message = clavier.readLine();
                if (Objects.equals(message, "exit"))
                    break;
                clientSender.send_message_client(new MessageClient(message, name, destinataires, new Date()));
            }
            else if (Objects.equals(task, "connexion")){
                System.out.println("name >");
                name = clavier.readLine();
                clientSender.send_connexion(new FirstConnection(name));
            }

        }

        clavier.close();
        clientSender.close();
        socket.close();
    }
}
