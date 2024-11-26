package TTT.model;


import java.util.ArrayList;
import java.util.List;

public class Board {

    public int size;
    public PlayingPiece[][] board;

    public Board(int size){
        this.size = size;
        this.board = new PlayingPiece[size][size];
    }

    public boolean addPiece(int row , int col, PlayingPiece playingPiece){
        if(board[row][col] != null){
            return false;
        }
        board[row][col] = playingPiece;
        return true;
    }

    public List<List<Integer>> getFreeCells() {
        List<List<Integer>> freeCells = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == null) {
                    List<Integer> rowColumn = new ArrayList<>();
                    rowColumn.add(i); // Add row index
                    rowColumn.add(j); // Add column index
                    freeCells.add(rowColumn);
                }
            }
        }

        return freeCells;
    }


    public void printBoard(){
        for(int i=0 ; i<size ; i++){
            for(int j=0; j<size ; j++){
                if(board[i][j] != null){
                    System.out.print(board[i][j].pieceType.name() + "  ");
                }else{
                    System.out.print("    ");
                }
                System.out.print(" | ");
            }

            System.out.println();
        }
    }


}
