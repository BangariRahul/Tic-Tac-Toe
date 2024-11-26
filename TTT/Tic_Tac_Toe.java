package TTT;

import TTT.model.*;

import java.util.*;

public class Tic_Tac_Toe {
    Deque<Player> players;
    Board gameBoard;


    void initializeGame(){

        players = new LinkedList<>();

        PlayingPiece playingPieceO = new PlayingPieceO();
        Player player1 = new Player("player1" , playingPieceO );

        players.add(player1);

        PlayingPiece playingPieceX = new PlayingPieceX();
        Player player2 = new Player("player2" , playingPieceX);

        players.add(player2);

        gameBoard = new Board(3);
    }

    public String startGame(){
        boolean noWinner = true;
        while(noWinner){


            Player playerTurn = players.removeFirst();

            gameBoard.printBoard();
            List<List<Integer>> freecells = new ArrayList<>();
            freecells = gameBoard.getFreeCells();
            if(freecells.size() == 0){
                noWinner= false;
                continue;
            }
            System.out.print("Player:" + playerTurn.name + " Enter row,column: ");
            Scanner sc = new Scanner(System.in);
            String coords =  sc.nextLine();

            String[] values = coords.split(",");
            int inputRow = Integer.valueOf(values[0]);
            int inputColumn = Integer.valueOf(values[1]);

            boolean pieceAdded = gameBoard.addPiece(inputRow, inputColumn, playerTurn.playingPiece);

            if(!pieceAdded){
                System.out.println("Incorredt possition chosen, try again");
                players.addFirst(playerTurn);
                continue;
            }

            players.addLast(playerTurn);
            boolean winner = isThereWinner(inputRow, inputColumn, playerTurn.playingPiece.pieceType);
            if(winner) {
                return playerTurn.name;
            }


        }
        return "Tie";
    }


    public boolean isThereWinner(int row, int column, PieceType pieceType) {

        boolean rowMatch = true;
        boolean columnMatch = true;
        boolean diagonalMatch = true;
        boolean antiDiagonalMatch = true;

        //need to check in row
        for(int i=0;i<gameBoard.size;i++) {

            if(gameBoard.board[row][i] == null || gameBoard.board[row][i].pieceType != pieceType) {
                rowMatch = false;
            }
        }

        //need to check in column
        for(int i=0;i<gameBoard.size;i++) {

            if(gameBoard.board[i][column] == null || gameBoard.board[i][column].pieceType != pieceType) {
                columnMatch = false;
            }
        }

        //need to check diagonals
        for(int i=0, j=0; i<gameBoard.size;i++,j++) {
            if (gameBoard.board[i][j] == null || gameBoard.board[i][j].pieceType != pieceType) {
                diagonalMatch = false;
            }
        }

        //need to check anti-diagonals
        for(int i=0, j=gameBoard.size-1; i<gameBoard.size;i++,j--) {
            if (gameBoard.board[i][j] == null || gameBoard.board[i][j].pieceType != pieceType) {
                antiDiagonalMatch = false;
            }
        }

        return rowMatch || columnMatch || diagonalMatch || antiDiagonalMatch;
    }


}

