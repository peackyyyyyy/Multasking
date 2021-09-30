package dispatcher;

import Workflow.FirstConnection;
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
    private String name;
    private final ClientSender clientSender;
    private final ClientListener clientListener;
    private final ArrayList<Dispatcher> clients;

    public Dispatcher(Socket socket, ArrayList<Dispatcher> clients) throws IOException {
        this.clientSender = new ClientSender(new ObjectOutputStream(socket.getOutputStream()));
        this.clientListener = new ClientListener(new ObjectInputStream(socket.getInputStream()));
        this.clients = clients;

    }

    public void run() {
        try {
            while (true) {
                MessageClient messageClient = null;
                FirstConnection firstConnection = null;
                Object request = clientListener.get_task();
                try {
                    firstConnection = (FirstConnection) request;
                } catch (Exception ignored){}
                try {
                    messageClient = (MessageClient) request;
                } catch (Exception ignored){}
                if (messageClient != null){
                    for (Dispatcher client: clients){
                        if (messageClient.getReceveur().contains(client.name)){
                            client.clientSender.send_message_serveur(new MessageServeur(messageClient.getMessage(), messageClient.getExpediteur(), new Date()));
                        }
                    }
                }
                if (firstConnection != null){
                    this.name = firstConnection.getName();
                    for (Dispatcher client: clients){
                        if(!Objects.equals(client.name, null)){
                            this.clientSender.send_message_serveur(new MessageServeur(client.name+" is connected", "Serveur", new Date()));
                        }
                        if(!Objects.equals(this.name, client.name) && !Objects.equals(client.name, null))
                            client.clientSender.send_message_serveur(new MessageServeur(name+" is connected", "Serveur", new Date()));
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (this.name != null){
                    for (Dispatcher client: clients) {
                        if(!Objects.equals(this.name, client.name) && !Objects.equals(client.name, null)) {
                            client.clientSender.send_message_serveur(new MessageServeur(name + " is disconnected", "Serveur", new Date()));
                        }
                    }
                    clients.remove(this);
                }
                clientSender.close();
                clientListener.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
