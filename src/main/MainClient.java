package main;

import javax.swing.*;
import java.io.IOException;

public class MainClient {

    public static void main(String[] args) throws IOException {

        JFrame jco = new JFrame();
        Connexion co = new Connexion(jco);
        jco.setContentPane(co.panel1);
        jco.setVisible(true);
        jco.setSize(600,400);
        jco.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }
}
