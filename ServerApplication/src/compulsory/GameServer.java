package compulsory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {

    private static final int PORT = 8100;
    private boolean running;

    public GameServer() throws IOException {
        running = true;
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
            while (running) {
                System.out.println("Waiting for a client ...");
                Socket socket = serverSocket.accept();
                new ClientThread(socket, this).start();
            }
        } catch (IOException e) {
            System.err.println("Ooops..." + e);
        } finally {
            serverSocket.close();
        }
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}

