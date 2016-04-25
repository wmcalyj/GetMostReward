package webserver;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

public class WebServerThreadClient extends Thread {
	// http://stackoverflow.com/questions/10131377/socket-programming-multiple-client-to-one-server
	protected Socket clientSocket;

	public WebServerThreadClient(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}

	public void run() {
		// TODO
		InputStream inputStream = null;
		ObjectInputStream objectInputStream = null;
		DataOutputStream out = null;
		try {
			inputStream = clientSocket.getInputStream();
			objectInputStream = new ObjectInputStream(inputStream);
			out = new DataOutputStream(clientSocket.getOutputStream());
		} catch (IOException e) {
			return;
		}
	}
}
