package Main;

import Serveur.ServeurListener;
import Serveur.ServeurSender;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class ConnectionThread extends Thread{
    private final Socket socket;

    public ConnectionThread(Socket socket) {
        this.socket = socket;
    }
    public void run() {
        while (true){
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
                ServeurSender serveurSender = new ServeurSender(printWriter);
                ServeurListener serveurListener = new ServeurListener(bufferedReader);
                Random random = new Random();
                String result = serveurListener.get_task();
                int port = random.nextInt(12000 - 11000) + 11000;
                serveurSender.send_task(port);
                System.out.println("Serveur recoit :" + result);
                bufferedReader.close();
                printWriter.close();
                this.socket.close();
            }catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }

    }

}
