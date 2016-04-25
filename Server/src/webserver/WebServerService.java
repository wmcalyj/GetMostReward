package webserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import values.constant.Values;

public class WebServerService {
	private boolean serverOn = false;

	public static boolean startServer(int portNumber) throws IOException {
		// TODO
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(portNumber);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Could not listen on port: " + portNumber);
			System.exit(1);
		}

		// In order to handle multiple clients, using thread
		// http://stackoverflow.com/questions/10131377/socket-programming-multiple-client-to-one-server
		Socket clientSocket = null;
		try {
			clientSocket = serverSocket.accept();
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Accept failed.");
			System.exit(1);
		}

		InputStream clientInputStream = clientSocket.getInputStream();
		ObjectInputStream objectClientInputStream = new ObjectInputStream(
				clientInputStream);
		// TODO

		return false;
	}
	 

	public static boolean startServer() throws IOException {
		int portNumber = Values.WebServer.DEFAULT_PORT_NUMBER;
		return startServer(portNumber);

	}
}
