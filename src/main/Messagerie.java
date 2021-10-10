package main;

import Workflow.MessageClient;
import serveur.ClientSender;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;

public class Messagerie {
    public JTextPane lstmsg;
    public JPanel panel1;
    private JTextField msgaenvoyer;
    private JButton envoyerButton;
    public JPanel listeuser;
    public ClientSender clientSender;
    public String pseudo;
    private HashMap<String,JCheckBox> map;

    public Messagerie(HashMap<String, JCheckBox> map) {
        this.map=map;
        envoyerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //arraylist
                ArrayList<String> arraydest = new ArrayList<>();
                for (Map.Entry<String,JCheckBox> check:map.entrySet()) {
                    if(check.getValue().isSelected())
                        arraydest.add(check.getKey());
                }
                try {
                    clientSender.send_message_client(new MessageClient(msgaenvoyer.getText(),pseudo,arraydest,new Date()));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                finally {
                    msgaenvoyer.setText("");
                    destinataire.setText("");
                    envoyerButton.setSelected(false);
                }
            }
        });
    }
}
