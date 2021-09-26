package serveur;

import Workflow.FirstConnection;
import Workflow.MessageClient;
import Workflow.MessageServeur;

import java.io.IOException;
import java.io.ObjectInputStream;

public class ClientListener {
    private final ObjectInputStream objectInputStream;

    public ClientListener(ObjectInputStream objectInputStream){
        this.objectInputStream = objectInputStream;
    }

    public MessageServeur get_message() throws IOException, ClassNotFoundException {
        return (MessageServeur) this.objectInputStream.readObject();
    }
    public MessageClient get_task() throws IOException, ClassNotFoundException {
        return (MessageClient) this.objectInputStream.readObject();
    }
    public FirstConnection get_first_connexion() throws IOException, ClassNotFoundException {
        return (FirstConnection) this.objectInputStream.readObject();
    }
    public void close() throws IOException {
        objectInputStream.close();
    }
}
