package compulsory;


import optional.Game;
import optional.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Handles communication with the client
 */
public class ClientThread extends Thread {

    private Socket socket = null;
    private GameServer gameServer;
    private Game game;
    private Player player;

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
                    response = handleRequest(request, in);
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

    /**
     * Handles client request such as create game, join game, submit move and check winner
     * @param request
     * @param bufferedReader
     * @return
     * @throws IOException
     */
    public String handleRequest(String request, BufferedReader bufferedReader) throws IOException {
        if(request == "create game") {
            this.gameServer.createGame();
            return "Game created";
        } else if(request == "join game") {
            this.game = this.gameServer.joinGame();
            player = gameServer.getLastPlayer();
            return "Joined game as " + player.getName();
        } else if(request == "submit move") {
            int line = Integer.parseInt(bufferedReader.readLine());
            int column = Integer.parseInt(bufferedReader.readLine());
            return this.game.submitMove(line,column,player);
        } else  if(request == "check winner") {
            return this.game.checkWinner();
        }
        return "Server received the request " + request;
    }
}
