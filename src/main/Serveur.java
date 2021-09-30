package main;

import dispatcher.Dispatcher;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Serveur {
    private static ArrayList<Dispatcher> dispatchers = new ArrayList<>();
    private static ExecutorService parralelisme = Executors.newFixedThreadPool(4);
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(4444);
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("new connection");
            Dispatcher dispatcher = new Dispatcher(socket, dispatchers);
            dispatchers.add(dispatcher);
            parralelisme.execute(dispatcher);
        }
    }
}
