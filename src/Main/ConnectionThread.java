package Main;

import Serveur.Serveur;
import Serveur.ServeurListener;
import Serveur.ServeurSender;
import Workflow.Dispatcher;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Random;

public class ConnectionThread extends Thread{
    private final Socket socket;
    private final Dispatcher dispatcher;

    public ConnectionThread(Socket socket, Dispatcher dispatcher) {
        this.socket = socket;
        this.dispatcher = dispatcher;
    }
    public void run() {
        while (true){
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
                ServeurSender serveurSender = new ServeurSender(printWriter);
                ServeurListener serveurListener = new ServeurListener(bufferedReader);
                Random random = new Random();
                String result = serveurListener.get_task();
                int port = random.nextInt(12000 - 11000) + 11000;
                serveurSender.send_task(port);
                System.out.println("Serveur recoit :" + result);
                bufferedReader.close();
                printWriter.close();
                this.socket.close();
                Socket socket_serveur = null;
                try {
                    ServerSocket serverSocket = new ServerSocket(port);
                    socket_serveur = serverSocket.accept();

                } catch (SocketException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Serveur serveur = new Serveur(socket_serveur);
                this.dispatcher.add_Serveur(serveur);
                new ServeurThread(serveur, this.dispatcher).start();
            }catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }

    }

}
