package application.net;



import java.io.IOException;
import java.net.*;
import java.util.Random;

/**
 * Created by kvgarimella on 10/6/15.
 */
public class Server {
	private int port = 9400;
	public void runServer() throws IOException {
		ServerSocket serverSocket = new ServerSocket(port);
		System.out.println("Server up & Ready for connections at port.. " + port);
		while (true) {
			Socket socket = serverSocket.accept();
			new ServerThread(socket).start();
		}


	}

	public int getPortNumber() {return port;}
}
