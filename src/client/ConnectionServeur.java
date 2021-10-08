package client;

import Workflow.MessageServeur;
import serveur.ClientListener;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class ConnectionServeur extends Thread{
    private final Socket socket;
    private final ClientListener clientListener;
    private JTextPane jTextPane;
    private HashMap<String,JCheckBox> listcheck;
    public JPanel listeuser;

    public ConnectionServeur(Socket socket, JTextPane jTextPane, JPanel listeuser,HashMap<String,JCheckBox> listcheck) throws IOException {
        this.socket = socket;
        this.jTextPane = jTextPane;
        this.listeuser=listeuser;
        this.listcheck=listcheck;
        listeuser.setLayout(new BoxLayout(listeuser, BoxLayout.Y_AXIS));
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        this.clientListener = new ClientListener(objectInputStream);
    }
    public void run() {
        try {
            while (true) {
                MessageServeur reponse = clientListener.get_message();
                if (reponse == null)break;
                if(reponse.getExpediteur().equals("Serveur")){
                    String stat = reponse.getMessage();
                    String[] status = stat.split(" ");
                    System.out.println(status[2]);
                    if(status[2].equals("connected")){
                        JCheckBox checkbox = new JCheckBox(status[0]);
                        listeuser.add(checkbox);
                        checkbox.setSelected(true);
                        listcheck.put(status[0],checkbox);
                    }
                    else{
                        System.out.println(status[0] + "disconnect");
                        JCheckBox check= listcheck.get(status[0]);
                        listeuser.remove(check);
                        listcheck.remove(status[0]);
                    }

                }

                String msg = reponse.getDate() + " : " + reponse.getExpediteur() + " > " + reponse.getMessage();
                jTextPane.setText(jTextPane.getText() + "\n"+ msg);
            }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    this.clientListener.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    }

}
