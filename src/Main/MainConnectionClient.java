package Main;

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
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String result = in.readLine();
            System.out.println("Client recoit :" +result);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
