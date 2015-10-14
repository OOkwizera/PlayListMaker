package application.net;

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


}
