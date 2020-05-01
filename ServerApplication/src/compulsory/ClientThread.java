package compulsory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {

    private Socket socket = null;
    private GameServer gameServer;

    public ClientThread(Socket socket, GameServer gameServer) {
        this.socket = socket;
        this.gameServer = gameServer;
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String request;
            String response;
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            while (gameServer.isRunning()) {
                request = in.readLine();
                if (request == "stop") {
                    response = "Server stopped";
                } else {
                    response = "Server received the request: " + request;
                }
                out.println(response);
                out.flush();
                if (request == "stop")
                    gameServer.setRunning(false);
            }
        } catch (IOException e) {
            System.err.println("Communication error... " + e);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }
}
