package client;

import Workflow.MessageServeur;
import serveur.ClientListener;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ConnectionServeur extends Thread{
    private final Socket socket;
    private final ClientListener clientListener;
    private JTextPane jTextPane;
    private ArrayList<JCheckBox> listcheck;
    public JPanel listeuser;

    public ConnectionServeur(Socket socket, JTextPane jTextPane) throws IOException {
        this.socket = socket;
        this.jTextPane = jTextPane;
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
                        System.out.println(status[0]);
                        JCheckBox checkbox = new JCheckBox(status[2]);
                        //listeuser.add(checkbox);
                        checkbox.setSelected(true);
                        //listcheck.add(checkbox);
                    }
                    else{
                        System.out.println(status[0] + "disconnect");
                        //
                        //checkbox delete
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
