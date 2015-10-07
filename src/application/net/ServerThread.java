package application.net;
import java.io.IOException;
import java.net.*;
import java.io.BufferedReader;
import java.io.*;

/**
 * Created by kvgarimella on 10/6/15.
 */
public class ServerThread extends Thread {
    Socket socket;

    ServerThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            String message = null;
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while ((message = bufferedReader.readLine()) != null) {
                System.out.println("incoming client message: " + message);
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
