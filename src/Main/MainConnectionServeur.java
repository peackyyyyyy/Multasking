package Main;
import Workflow.Dispatcher;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class MainConnectionServeur {
    public static void main(String[] args) {
        while (true) {
            Socket socket = null;
            Dispatcher dispatcher = new Dispatcher(null);
            try {
                ServerSocket serverSocket = new ServerSocket(12333);
                socket = serverSocket.accept();

            } catch (SocketException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            new ConnectionThread(socket, dispatcher).start();

        }
    }
}
