package Main;

import Client.ClientListener;
import Client.ClientSender;
import Serveur.ServeurListener;
import Serveur.ServeurSender;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Random;

public class MainServeur {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12333);
            Socket socket = serverSocket.accept();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            ServeurSender serveurSender = new ServeurSender(printWriter);
            ServeurListener serveurListener = new ServeurListener(bufferedReader);
            String result = serveurListener.get_task();
            Random random = new Random();
            int port = random.nextInt(12000 - 11000) + 11000;
            serveurSender.send_task(port);
            System.out.println("Serveur recoit :" + result);
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
