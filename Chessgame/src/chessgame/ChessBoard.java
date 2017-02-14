package chessgame;

import java.util.ArrayList;
import javafx.scene.layout.GridPane;

public class ChessBoard extends GridPane {

    private static final ArrayList<Square> board = new ArrayList<>(); // Reference to squares on board, call with getBoard from other objects
    
    private static ChessBoard instance;
    
    
    private ChessBoard() {
        createBoard();
        drawBoard();
    }
    
    // Singleton instance of ChessBoard
    public static ChessBoard getInstance() {
    	if (instance == null) {
    		return new ChessBoard();
    	}
    	return instance;
    }
    
    // Creates chess board in memory
    private void createBoard() {
        for (int file = 0; file < Chess.BOARDSIZE; file++) {
            for (int rank = 0; rank < Chess.BOARDSIZE; rank++) {
                board.add(new Square(rank, file));
            }
        }
    }
    
    public static ArrayList<Square> getBoard() {
    	return board;
    }
    
    private void drawBoard() {
        for (Square s : board) {
            this.add(s, (int) s.getPoint().getX(), (int) s.getPoint().getY());
        }
    }
    
    @Override
    public String toString() {
        String temp = "";
        for (Square s : board) {
        	String piece = "";
        	if (s.piece != null) {
        		piece = s.getPiece().toString();
        	}
        	temp += "[" + piece + s.toString() + "]";
        	if ((s.getPoint().getX() + 1) % Chess.BOARDSIZE == 0) {
        		temp += "\n";
        	}
        }
        return temp;
    }
}
