package application.net;



import java.io.IOException;
import java.net.*;
import java.util.Random;

/**
 * Created by kvgarimella on 10/6/15.
 */
public class Server {
	Random r = new Random();
	int port = 4445;
	public static void main (String[] args) throws IOException {
		new Server().runServer();
	}

	public void runServer() throws IOException {
		int port = 9400;
		ServerSocket serverSocket = new ServerSocket(port);
		InetAddress ip = serverSocket.getInetAddress();
		System.out.println("this is my ip: " + ip);
		System.out.println("Server up & Ready for connections at port.. " + port);
		while (true) {
			Socket socket = serverSocket.accept();
			new ServerThread(socket).start();
		}



	}
}
