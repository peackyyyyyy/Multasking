package Serveur;

import ServeurListener;
import ServeurSender;

import java.io.*;
import java.net.Socket;

public class Serveur {
    private final ServeurSender serveurSender;
    private final ServeurListener serveurListener;

    public Serveur(Socket socket) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
        this.serveurSender = new ServeurSender(printWriter);
        this.serveurListener = new ServeurListener(bufferedReader);
    }

    public ServeurListener getServeurListener() {
        return serveurListener;
    }

    public ServeurSender getServeurSender() {
        return serveurSender;
    }

    @Override
    public String toString() {
        return "Serveur{" +
                "serveurSender=" + serveurSender +
                ", serveurListener=" + serveurListener +
                '}';
    }
}
