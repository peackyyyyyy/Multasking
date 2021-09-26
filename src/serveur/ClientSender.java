package serveur;

import Workflow.FirstConnection;
import Workflow.MessageClient;
import Workflow.MessageServeur;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class ClientSender {
    private final ObjectOutputStream objectOutputStream;

    public ClientSender(ObjectOutputStream objectOutputStream){
        this.objectOutputStream = objectOutputStream;
    }

    public void send_message_client(MessageClient messageClient) throws IOException {
        this.objectOutputStream.writeObject(messageClient);
    }
    public void send_message_serveur(MessageServeur messageServeur) throws IOException {
        this.objectOutputStream.writeObject(messageServeur);
    }
    public void send_connexion(FirstConnection firstConnection) throws IOException {
        this.objectOutputStream.writeObject(firstConnection);
    }
    public void close() throws IOException {
        objectOutputStream.close();
    }
}
