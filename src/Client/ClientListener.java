package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientListener {
    private final BufferedReader bufferedReader;

    public ClientListener(BufferedReader bufferedReader){
        this.bufferedReader = bufferedReader;
    }

    public String get_result() throws IOException, InterruptedException {
        return this.bufferedReader.readLine();
    }
}
