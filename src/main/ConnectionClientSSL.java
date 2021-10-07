package main;

import Workflow.FirstConnection;
import Workflow.MessageClient;
import client.ConnectionServeur;
import serveur.ClientSender;

import javax.net.ssl.*;
import java.io.*;
import java.net.Socket;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class ConnectionClientSSL {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
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
            connectionServeur = new ConnectionServeur(sslSocket);
        } catch (IOException | KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException | CertificateException | NoSuchProviderException | KeyManagementException e) {
            e.printStackTrace();
        }
        assert sslSocket != null;
        BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));
        ClientSender clientSender = new ClientSender(new ObjectOutputStream(sslSocket.getOutputStream()));

        new Thread(connectionServeur).start();

        String name = null;
        while (true) {
            ArrayList<String> destinataires = new ArrayList<>();
            String task = clavier.readLine();
            if (Objects.equals(task, "send")){
                while (true) {
                    System.out.println("destinataire >");
                    String destinataire = clavier.readLine();
                    if (Objects.equals(destinataire, "next"))break;
                    destinataires.add(destinataire);
                }
                System.out.println("message >");
                String message = clavier.readLine();
                if (Objects.equals(message, "exit"))
                    break;
                clientSender.send_message_client(new MessageClient(message, name, destinataires, new Date()));
            }
            else if (Objects.equals(task, "connexion")){
                System.out.println("name >");
                name = clavier.readLine();
                clientSender.send_connexion(new FirstConnection(name));
            }

        }

        clavier.close();
        clientSender.close();
        sslSocket.close();
    }
}
