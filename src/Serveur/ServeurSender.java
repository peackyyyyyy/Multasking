package Serveur;

import java.io.PrintWriter;
import java.net.Socket;

public class ServeurSender {
    private final PrintWriter printWriter;

    public ServeurSender(PrintWriter printWriter){
        this.printWriter = printWriter;
    }

    public void send_task(String message){
        this.printWriter.println(message);
    }
}
