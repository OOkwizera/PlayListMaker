package application.net;
import java.net.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.io.*;

public class Server {
	private ServerSocket serverSocket;
	public int port = 9800;
	public ArrayBlockingQueue<String> information;
	
	public Server(int port, ArrayBlockingQueue<String> information) throws IOException {
		serverSocket = new ServerSocket(port);
		this.information = information;
	}
	
	public void runServer() throws IOException {
<<<<<<< HEAD
		System.out.println("Server up & Ready for connections at port.. " + port);
=======
		System.out.println("Server up at port: " + port);
>>>>>>> 23295189c6174e2c75251df5071a02bf465cc386
		while(true) {
			Socket socket = serverSocket.accept();
			new ServerThread(socket).start();
		}
	} 
	
	private class ServerThread extends Thread {
		Socket socket;
		
		ServerThread(Socket socket) {
			this.socket = socket;
		}
		
		public void run() {
	        try {
	            String message;
	            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	            while (!(message = bufferedReader.readLine()).equals("")) {
<<<<<<< HEAD
	            	information.add(message);
	                System.out.println("incoming client message: " + message);     
=======
	            	information.add(message); 
>>>>>>> 23295189c6174e2c75251df5071a02bf465cc386
	            }
	            socket.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
}
