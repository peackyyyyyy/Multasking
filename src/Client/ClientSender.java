package Client;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientSender {
    private final PrintWriter printWriter;


    public ClientSender(PrintWriter printWriter){
        this.printWriter = printWriter;
    }

    public void send_task(String message){
        this.printWriter.println(message);
    }
}
