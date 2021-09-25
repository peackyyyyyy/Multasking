package sample;

import Client.Client;
import Client.ClientListener;
import Client.ClientSender;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Objects;

public class MainConnectionClient {
    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = null;
        ConnectionServeur connectionServeur = null;
        try {
            socket = new Socket("localhost", 9090);
            connectionServeur = new ConnectionServeur(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert socket != null;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
        ClientSender clientSender = new ClientSender(printWriter);
        ClientListener clientListener = new ClientListener(bufferedReader);

        new Thread(connectionServeur).start();

        while (true) {
            System.out.println("message >");
            String message = clavier.readLine();
            if (Objects.equals(message, "exit"))break;
            clientSender.send_task(message);
        }
    }
}
