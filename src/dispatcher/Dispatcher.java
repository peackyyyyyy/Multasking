package dispatcher;

import Workflow.MessageServeur;
import serveur.ClientListener;
import serveur.ClientSender;
import Workflow.MessageClient;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class Dispatcher extends Thread{
    private final Integer name;
    private final ClientSender clientSender;
    private final ClientListener clientListener;
    private final ArrayList<Dispatcher> clients;

    public Dispatcher(Socket socket, ArrayList<Dispatcher> clients, Integer name) throws IOException {
        this.clientSender = new ClientSender(new ObjectOutputStream(socket.getOutputStream()));
        this.clientListener = new ClientListener(new ObjectInputStream(socket.getInputStream()));
        this.clients = clients;
        this.name = name;

    }

    public void run() {
        try {
            for (Dispatcher client: clients){
                if(!Objects.equals(this.name, client.name))client.clientSender.send_message_serveur(new MessageServeur(name+" is connected", "Serveur", new Date()));
            }
            while (true) {
                MessageClient request = clientListener.get_task();
                for (Dispatcher client: clients){
                    if (request.getReceveur().contains(client.name)){
                        client.clientSender.send_message_serveur(new MessageServeur(request.getMessage(), request.getExpediteur(), new Date()));
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSender.close();
                clientListener.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
