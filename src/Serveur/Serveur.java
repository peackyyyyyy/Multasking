package Serveur;

import java.io.*;
import java.net.Socket;

public class Serveur {
    private final ServeurSender serveurSender;
    private final ServeurListener serveurListener;
    private final Socket socket;
    public Serveur(Socket socket) throws IOException {
        this.socket = socket;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(this.socket.getOutputStream()), true);
        this.serveurSender = new ServeurSender(printWriter);
        this.serveurListener = new ServeurListener(bufferedReader);
    }

    public ServeurListener getServeurListener() {
        return serveurListener;
    }

    public ServeurSender getServeurSender() {
        return serveurSender;
    }
}
