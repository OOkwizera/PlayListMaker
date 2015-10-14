package application.net;

<<<<<<< HEAD
import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class Connection {
	String IP;
	ArrayBlockingQueue<String> information = new ArrayBlockingQueue<>(20);
	Server server;
	int port;
	
	public Connection() {
		port = 9800;
		new Thread(() -> {
			try {
				server = new Server(port, information);
				server.runServer();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
	}
	
	public void handShake(String info, String IP) {
		new Thread(() -> {
			try {
				Socket socket = new Socket(InetAddress.getByName((IP)), port);
				send(socket, info);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
	}
	
	void send(Socket target, String info) throws IOException { 
		PrintWriter sockout = new PrintWriter(target.getOutputStream());
		sockout.println(info);
		sockout.flush();
	}
	
	public String retrieve() throws InterruptedException {
		return information.take();
	}
	
	public Boolean hasMessage() {
		return !information.isEmpty();
	}
	
=======
import java.io.IOException;
import java.net.*;
import java.io.*;
public class Connection {
    public static void main(String[] args) throws UnknownHostException, IOException {
        String name = args[0];
        String port = args[1];
        int intport = Integer.parseInt(port);
        PrintWriter printWriter;
        Socket socket = new Socket(InetAddress.getByName("ozark.hendrix.edu"), 9400);
        printWriter = new PrintWriter(socket.getOutputStream(), true);
        //message line.
        BufferedReader bufferedReader = new java.io.BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String readerInput = bufferedReader.readLine();
            printWriter.println(name + ": " + readerInput);
        }

    }


>>>>>>> 23295189c6174e2c75251df5071a02bf465cc386
}
