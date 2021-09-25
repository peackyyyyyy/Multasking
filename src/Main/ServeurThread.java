package Main;

import Serveur.Serveur;
import Serveur.ServeurListener;
import Serveur.ServeurSender;
import Workflow.Dispatcher;

import java.io.*;
import java.net.Socket;

public class ServeurThread extends Thread{

    private final Serveur serveur;
    private final Dispatcher dispatcher;

    public ServeurThread(Serveur serveur, Dispatcher dispatcher){
        this.serveur = serveur;
        this.dispatcher = dispatcher;
    }
    public void run() {
        while (true){
            try {
                String result = serveur.getServeurListener().get_task();
                dispatcher.dispatch(result);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
