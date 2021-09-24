package Serveur;

import java.io.BufferedReader;
import java.io.IOException;

public class ServeurListener {
    private final BufferedReader bufferedReader;

    public ServeurListener(BufferedReader bufferedReader){
        this.bufferedReader = bufferedReader;
    }

    public String get_task() throws IOException {
        return this.bufferedReader.readLine();
    }
}
