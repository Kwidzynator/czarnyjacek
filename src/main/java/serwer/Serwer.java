package serwer;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Serwer {

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(7777);

        do {
            Socket socket = server.accept();
            Watek w = new Watek(socket);
            w.start();
        } while (true);

    }
}
