package Client;

public class Client {
    private final ClientSender clientSender;
    private final ClientListener clientListener;
    public Client(ClientSender clientSender, ClientListener clientListener){
        this.clientListener = clientListener;
        this.clientSender = clientSender;
    }

    public ClientListener getClientListener() {
        return clientListener;
    }

    public ClientSender getClientSender() {
        return clientSender;
    }
}
