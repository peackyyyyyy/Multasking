package sample;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ServeurSender {
    private final PrintWriter printWriter;

    public ServeurSender(PrintWriter printWriter){
        this.printWriter = printWriter;
    }

    public void send_port(int port){
        this.printWriter.println(port);
    }
    public void send_message(String message){
        this.printWriter.println(message);
    }
    public void close(){
        printWriter.close();
    }
}
