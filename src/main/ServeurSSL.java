package main;

import dispatcher.Dispatcher;

import javax.net.ssl.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServeurSSL {
    private static ArrayList<Dispatcher> dispatchers = new ArrayList<>();
    private static ExecutorService parralelisme = Executors.newFixedThreadPool(4);
    public static void main(String[] args){
        try {
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            String password = "abcdefg";
            InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream("resources/server/certificate-server.p12");
            keyStore.load(inputStream, password.toCharArray());

            // TrustManagerFactory
            String password2 = "aabbcc";
            KeyStore trustStore = KeyStore.getInstance("PKCS12");
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("PKIX", "SunJSSE");
            InputStream inputStream1 = ClassLoader.getSystemClassLoader().getResourceAsStream("resources/client/certificate-client.p12");
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

            SSLServerSocketFactory serverSocketFactory = sslContext.getServerSocketFactory();
            SSLServerSocket serverSocket = (SSLServerSocket) serverSocketFactory.createServerSocket(4444);
            serverSocket.setNeedClientAuth(true);
            serverSocket.setEnabledProtocols(new String[]{"TLSv1.2"});
            while (true) {
                SSLSocket socket = (SSLSocket) serverSocket.accept();
                System.out.println("new connection");
                Dispatcher dispatcher = new Dispatcher(socket, dispatchers);
                dispatchers.add(dispatcher);
                parralelisme.execute(dispatcher);
            }
        } catch (KeyStoreException | IOException | NoSuchAlgorithmException | CertificateException | NoSuchProviderException | NullPointerException | UnrecoverableKeyException | KeyManagementException e) {
            e.printStackTrace();
        }
    }
}
