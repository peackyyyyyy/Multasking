package main;

import Workflow.FirstConnection;
import client.ConnectionServeur;
import serveur.ClientSender;

import javax.net.ssl.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.HashMap;

public class Connexion {
    public JPanel panel1;
    private JTextField pseudoText;
    private JButton connexionButton;
    private JPanel listeuser;
    private String pseudo;
    private boolean check = false;
    JFrame messagerie;
    Messagerie msg;
    ClientSender clientSender;

    public Connexion(JFrame jFrame) throws IOException {

        connexionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!pseudoText.getText().equals("")) {
                    pseudo = pseudoText.getText();
                    messagerie.setVisible(true);
                    msg.pseudo = pseudo;
                    try {
                        clientSender.send_connexion(new FirstConnection(pseudo));
                        System.out.println("pseudo send");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    jFrame.dispose();
                }
            }
        });

        messagerie = new JFrame("Messagerie");
        HashMap<String,JCheckBox> map=new HashMap<>();
        msg = new Messagerie(map);
        listeuser= msg.listeuser;
        messagerie.setContentPane(msg.panel1);
        messagerie.setVisible(false);
        messagerie.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        messagerie.setSize(1000,1000);

        SSLSocket sslSocket = null;
        ConnectionServeur connectionServeur = null;
        try {
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            String password = "aabbcc";
            InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream("resources/client/certificate-client.p12");
            keyStore.load(inputStream, password.toCharArray());

            // TrustManagerFactory ()
            KeyStore trustStore = KeyStore.getInstance("PKCS12");
            String password2 = "abcdefg";
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("PKIX", "SunJSSE");
            InputStream inputStream1 = ClassLoader.getSystemClassLoader().getResourceAsStream("resources/server/certificate-server.p12");
            trustStore.load(inputStream1, password2.toCharArray());
            trustManagerFactory.init(trustStore);
            X509TrustManager x509TrustManager = null;
            for (TrustManager trustManager : trustManagerFactory.getTrustManagers()) {
                if (trustManager instanceof X509TrustManager) {
                    x509TrustManager = (X509TrustManager) trustManager;
                    break;
                }
            }

            if (x509TrustManager == null) throw new NullPointerException();

            // KeyManagerFactory ()
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509", "SunJSSE");
            keyManagerFactory.init(keyStore, password.toCharArray());
            X509KeyManager x509KeyManager = null;
            for (KeyManager keyManager : keyManagerFactory.getKeyManagers()) {
                if (keyManager instanceof X509KeyManager) {
                    x509KeyManager = (X509KeyManager) keyManager;
                    break;
                }
            }
            if (x509KeyManager == null) throw new NullPointerException();

            // set up the SSL Context
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(new KeyManager[]{x509KeyManager}, new TrustManager[]{x509TrustManager}, null);

            SSLSocketFactory socketFactory = sslContext.getSocketFactory();
            sslSocket = (SSLSocket) socketFactory.createSocket("localhost", 4444);
            sslSocket.setEnabledProtocols(new String[]{"TLSv1.2"});
            connectionServeur = new ConnectionServeur(sslSocket,msg.lstmsg, listeuser, map);
        } catch (IOException | KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException | CertificateException | NoSuchProviderException | KeyManagementException e) {
            e.printStackTrace();
        }
        assert sslSocket != null;
        BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));
        clientSender = new ClientSender(new ObjectOutputStream(sslSocket.getOutputStream()));

        new Thread(connectionServeur).start();

        msg.clientSender = clientSender;




    }
}
