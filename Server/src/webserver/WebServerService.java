package webserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import values.constant.Values;

public class WebServerService {
	private boolean serverOn = false;

	public static void startServer(int portNumber) throws IOException {
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

		while (true) {
			// The server should run forever
			try {
				clientSocket = serverSocket.accept();
			} catch (IOException e) {
				System.out.println("I/O error: " + e);
			}
			// new thread for a client
			new WebServerThreadClient(clientSocket).start();
		}
	}

	public static void startServer() throws IOException {
		int portNumber = Values.WebServer.DEFAULT_PORT_NUMBER;
		startServer(portNumber);

	}
}
