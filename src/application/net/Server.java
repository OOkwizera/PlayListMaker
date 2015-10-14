package application.net;
import java.net.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.io.*;

public class Server {
	private ServerSocket serverSocket;
	private int port = 9800;
	public ArrayBlockingQueue<String> playlist;
	
	public Server() throws IOException {
		serverSocket = new ServerSocket(port);
		
	}
	
}
