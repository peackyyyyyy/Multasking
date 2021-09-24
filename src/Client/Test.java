package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Test {

	public static void main(String[] args) throws UnknownHostException, IOException {
		ClientSender client = new ClientSender();
	    client.startConnection("127.0.0.1", 6666);
	    String response = client.sendMessage("hello server");
		System.out.println(response);
		assertEquals("hello client", response);
	}

}
