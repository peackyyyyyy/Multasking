package Client;
import java.io.PrintWriter;

public class ClientSender {
    private final PrintWriter printWriter;


    public ClientSender(PrintWriter printWriter){
        this.printWriter = printWriter;
    }

    public void send_task(String message){
        this.printWriter.println(message);
    }
}
