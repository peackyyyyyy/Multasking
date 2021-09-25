package Main;

import Client.Client;
import Client.ClientListener;
import Client.ClientSender;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class MainConnectionClient {
    public static void main(String[] args) throws IOException {
        try {
            Socket socket = new Socket("localhost", 12333);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            ClientSender clientSender = new ClientSender(printWriter);
            ClientListener clientListener = new ClientListener(bufferedReader);
            clientSender.send_task("Client : connection ?");
            String result = clientListener.get_result();
            System.out.println("Client recoit :" + result);
            socket.close();
            bufferedReader.close();
            printWriter.close();
            Socket socket_client = new Socket("localhost", Integer.parseInt(result));
            new ClientThread(new Client(new ClientSender(new PrintWriter(new OutputStreamWriter(socket_client.getOutputStream()),
                    true)),new ClientListener(new BufferedReader(new InputStreamReader(socket_client.getInputStream())))))
                    .start();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
