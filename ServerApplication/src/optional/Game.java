package optional;

/**
 * A game instance
 */
public class Game {

    private Board board;
    private Player player1 = null, player2 = null;
    private int playerTurn;

    public Game() {
        board = new Board();
        playerTurn = 1;
    }

    /**
     * Will try to place a piece on the table
     * @param line
     * @param column
     * @param player
     * @return If the specified tile already has a piece on it or if its not the player's turn returns "Invalid move", otherwise "Successful move"
     */
    public String submitMove(int line, int column, Player player) {
        if(board.getTile(line,column) != 0)
            return "Invalid move";
        if(playerTurn == 1 && player == player1) {
            board.setTile(line,column,1);
            playerTurn = 2;
            return "Successful move";
        } else if(playerTurn == 2 && player == player2) {
            board.setTile(line,column,2);
            playerTurn = 1;
            return "Successful move";
        } else {
            return "Invalid move";
        }
    }

    /**
     * Check who won by interrogating the board
     * @return either "Player 1" or "Player 2"
     */
    public String checkWinner() {
        if(board.checkWinner() == 1)
            return "Player 1 won";
        else
            return "Player 2 won";
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public int getPlayerTurn() {
        return playerTurn;
    }

    public void setPlayerTurn(int playerTurn) {
        this.playerTurn = playerTurn;
    }
}
