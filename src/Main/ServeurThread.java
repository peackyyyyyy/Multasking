package Main;

import Serveur.Serveur;
import sample.Dispatcher;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServeurThread extends Thread{
    private final Dispatcher dispatcher;
    private final Integer port;

    public ServeurThread(Dispatcher dispatcher, Integer port){
        this.dispatcher = dispatcher;
        this.port = port;
    }
    public synchronized void run() {
        Socket socket_serveur = null;
        while (socket_serveur == null) {
            try {
                ServerSocket serverSocket = new ServerSocket(port);
                socket_serveur = serverSocket.accept();
                System.out.println("okkkkkkkkkkk");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Serveur serveur = null;
        try {
            serveur = new Serveur(socket_serveur);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String result = null;
        try {
            result = serveur.getServeurListener().get_task();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Serveur recoit : " + result);

    }
}
