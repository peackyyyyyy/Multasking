package Serveur;

import java.io.BufferedReader;
import java.io.IOException;

public class ServeurListener {
    private final BufferedReader bufferedReader;

    public ServeurListener(BufferedReader bufferedReader){
        this.bufferedReader = bufferedReader;
    }

    public void get_task() throws IOException {
        this.bufferedReader.readLine();
    }
}
