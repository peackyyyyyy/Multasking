package Main;

import Client.*;

import java.io.*;
import java.net.Socket;

public class ClientThread extends Thread{
    private final Client client;
    public ClientThread(Integer port) throws IOException {
        Socket socket_client = new Socket("localhost", port);
        this.client = new Client(new ClientSender(new PrintWriter(new OutputStreamWriter(socket_client.getOutputStream()),
                true)), new ClientListener(new BufferedReader(new InputStreamReader(socket_client.getInputStream()))));
    }

    public void run(){
        this.client.getClientSender().send_task("Task envoyé ");
        System.out.println("Tache  envoyé au serveur ");
        try {
            String reponse = this.client.getClientListener().get_result();
            System.out.println("Client recoit :"+reponse);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}
