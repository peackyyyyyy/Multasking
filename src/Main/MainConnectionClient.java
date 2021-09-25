package Main;

import Client.ClientListener;
import Client.ClientSender;
import test.testclient;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class MainConnectionClient {
    public static void main(String[] args) throws IOException {
        try {
            Socket socket = new Socket("localhost", 12333);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String result = in.readLine();
            System.out.println("Client recoit :" +result);
            testclient t = new testclient();
            socket.close();
            t.startConnection("localhost", Integer.parseInt(result));
            String msg = null;
            Scanner scan = new Scanner(System.in);
            do{
                msg = scan.nextLine();
                t.sendMessage(msg);
            } while(!msg.equals("exit"));
            t.stopConnection();
            System.out.println("fin connexion");
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
