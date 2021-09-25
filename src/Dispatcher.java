import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Dispatcher extends Thread{
    private final ServeurSender serveurSender;
    private final ServeurListener serveurListener;
    private final ArrayList<Dispatcher> clients;

    public Dispatcher(Socket socket, ArrayList<Dispatcher> clients) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
        this.serveurSender = new ServeurSender(printWriter);
        this.serveurListener = new ServeurListener(bufferedReader);
        this.clients = clients;

    }
    public void run() {
        try {
            while (true) {
                String request = serveurListener.get_task();
                serveurSender.send_message(request);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            serveurSender.close();
            try {
                serveurListener.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
