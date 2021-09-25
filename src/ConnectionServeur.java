import java.io.*;
import java.net.Socket;

public class ConnectionServeur extends Thread{
    private final Socket socket;
    private final ServeurSender serveurSender;
    private final ServeurListener serveurListener;

    public ConnectionServeur(Socket socket) throws IOException {
        this.socket = socket;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
        this.serveurSender = new ServeurSender(printWriter);
        this.serveurListener = new ServeurListener(bufferedReader);
    }
    public void run() {
        try {
            while (true) {
                String reponse = serveurListener.get_task();
                if (reponse == null)break;
                System.out.println("Serveur > " + reponse);
            }
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    this.serveurListener.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    }

}
