package main;

import Workflow.MessageClient;
import serveur.ClientSender;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Date;

public class Messagerie {
    public JTextPane lstmsg;
    public JPanel panel1;
    private JTextField msgaenvoyer;
    private JButton envoyerButton;
    private JTextField destinataire;
    public JPanel listeuser;
    private JCheckBox checkBox1;
    public ClientSender clientSender;
    public String pseudo;

    public Messagerie() {
        envoyerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dest = destinataire.getText();
                String lstdest[] = dest.split(";");
                ArrayList<String> arraydest =new ArrayList<>();
                for(int i = 0; i < lstdest.length; i++){
                    arraydest.add(lstdest[i]);
                }
                try {
                    clientSender.send_message_client(new MessageClient(msgaenvoyer.getText(),pseudo,arraydest,new Date()));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
