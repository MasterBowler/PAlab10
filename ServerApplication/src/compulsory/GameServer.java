package compulsory;

import optional.Game;
import optional.Player;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages the games and players
 */
public class GameServer {

    private static final int PORT = 8100;
    private boolean running;
    private List<Game> games = null;
    private List<Player> players = null;

    public Player getLastPlayer() {
        return players.get(players.size() - 1);
    }

    private int noOfPlayers = 0;

    public GameServer() throws IOException {
        running = true;
        games = new ArrayList<>();
        players = new ArrayList<>();
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

    /**
     * creates a new game
     */
    public void createGame() {
        this.games.add(new Game());
    }

    /**
     * Joins the newest game created
     * Also assigns the players id and name
     * @return the game joined by the player
     */
    public Game joinGame() {
        Game currentGame = games.get(games.size() - 1);
        players.add(new Player(noOfPlayers, "anon" + noOfPlayers));
        ++noOfPlayers;
        if (currentGame.getPlayer1() == null) {
            currentGame.setPlayer1(players.get(players.size() - 1));
        } else if (currentGame.getPlayer2() == null) {
            currentGame.setPlayer2(players.get(players.size() - 1));
        }
        return currentGame;
    }
}

