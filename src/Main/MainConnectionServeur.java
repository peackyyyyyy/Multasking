package Main;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class MainConnectionServeur {
    public static void main(String[] args) {
        while (true) {
            Socket socket = null;
            try {
                ServerSocket serverSocket = new ServerSocket(12333);
                socket = serverSocket.accept();

            } catch (SocketException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            new ConnectionThread(socket).start();

        }
    }
}
