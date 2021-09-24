package Main;

import Client.ClientListener;
import Client.ClientSender;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class MainClient {
    public static void main(String[] args) throws IOException {
        try {
            ServerSocket sockettest = new ServerSocket(0);
            sockettest.setReuseAddress(true);
            int port =  sockettest.getLocalPort();
            Socket socket = new Socket("localhost", port);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            ClientSender clientSender = new ClientSender(printWriter);
            ClientListener clientListener = new ClientListener(bufferedReader);
            socket.close();
            bufferedReader.close();
            printWriter.close();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
