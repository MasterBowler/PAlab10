package optional;

/**
 * The board of the game, has a size of 15x15
 */
public class Board {

    private int board[][];

    public Board() {
        this.board = new int[15][15];
        int i, j;
        for (i = 0; i < 15; ++i)
            for (j = 0; j < 15; ++j)
                this.board[i][j] = 0;
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public int getTile(int i, int j) {
        return this.board[i][j];
    }

    public void setTile(int i, int j, int value) {
        this.board[i][j] = value;
    }

    /**
     * will look for an unbroken chain of five stones either horizontally, vertically or diagonally
     * @return the winner as 1 for player 1 or 2 for player 2, 0 if there is no winner
     */
    public int checkWinner() {
        int winner = 0;
        winner = checkHorizontal();
        if (winner == 0)
            winner = checkVertical();
        if (winner == 0)
            winner = checkDiagonal1();
        if (winner == 0)
            winner = checkDiagonal2();
        return winner;
    }

    private int checkDiagonal2() {
        int i, j, k, piece;
        boolean unbrokenChain;
        for (i = 14; i > 3; --i) {
            for (j = 0; j < 11; ++j) {
                piece = board[i][j];
                unbrokenChain = true;
                for (k = 1; k < 5; ++k) {
                    if(board[i-k][j+k] != piece)
                        unbrokenChain = false;
                }
                if(unbrokenChain == true)
                    return piece;
            }
        }
        return 0;
    }

    private int checkDiagonal1() {
        int i, j, k, piece;
        boolean unbrokenChain;
        for (i = 0; i < 11; ++i) {
            for (j = 0; j < 11; ++j) {
                piece = board[i][j];
                unbrokenChain = true;
                for (k = 1; k < 5; ++k) {
                    if(board[i+k][j+k] != piece)
                        unbrokenChain = false;
                }
                if(unbrokenChain == true)
                    return piece;
            }
        }
        return 0;
    }

    private int checkVertical() {
        int i, j, k, piece;
        boolean unbrokenChain;
        for (i = 0; i < 11; ++i) {
            for (j = 0; j < 15; ++j) {
                piece = board[i][j];
                unbrokenChain = true;
                for (k = i + 1; k < i + 5; ++k) {
                    if(board[k][j] != piece)
                        unbrokenChain = false;
                }
                if(unbrokenChain == true)
                    return piece;
            }
        }
        return 0;
    }

    public int checkHorizontal() {
        int i, j, k, piece;
        boolean unbrokenChain;
        for (i = 0; i < 15; ++i) {
            for (j = 0; j < 11; ++j) {
                piece = board[i][j];
                unbrokenChain = true;
                for (k = j + 1; k < j + 5; ++k) {
                    if(board[i][k] != piece)
                        unbrokenChain = false;
                }
                if(unbrokenChain == true)
                    return piece;
            }
        }
        return 0;
    }
}
