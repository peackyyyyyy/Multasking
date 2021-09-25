package Main;
import dispatcher.Dispatcher;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class MainConnectionServeur {
    public static void main(String[] args) throws IOException {
        Dispatcher dispatcher = new Dispatcher();
        ServerSocket serverSocket = new ServerSocket(12333);
        while (true) {
            Socket socket = null;
            try {
                socket = serverSocket.accept();
            } catch (SocketException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(socket != null){
                new ConnectionThread(socket,dispatcher).start();
                System.out.println("socket crée");
            }

        }
    }
}
