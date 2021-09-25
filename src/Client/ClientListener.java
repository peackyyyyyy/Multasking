package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientListener {
    private final BufferedReader bufferedReader;

    public ClientListener(BufferedReader bufferedReader){
        this.bufferedReader = bufferedReader;
    }

    public String get_result() throws IOException {
        return this.bufferedReader.readLine();
    }
}

/*public class ClientListener {
    /*private final BufferedReader bufferedReader;
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public ClientListener(BufferedReader bufferedReader){
        this.bufferedReader = bufferedReader;
    }

    public String get_result() throws IOException {
        return this.bufferedReader.readLine();
    }*/
	/*private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        clientSocket = serverSocket.accept();
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String greeting = in.readLine();
            if ("hello server".equals(greeting)) {
                out.println("hello client");
            }
            else {
                out.println("unrecognised greeting");
            }
    }

    public void stop() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }
    public static void main(String[] args) throws IOException {
    	ClientListener server=new ClientListener();
        server.start(6666);
    }
}*/
