package Serveur;

import java.io.PrintWriter;
import java.net.Socket;

public class ServeurSender {
    private final PrintWriter printWriter;

    public ServeurSender(PrintWriter printWriter){
        this.printWriter = printWriter;
    }

    public void send_task(int port){
        this.printWriter.println(port);
    }
}
