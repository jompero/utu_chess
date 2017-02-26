package chessgame;

import java.awt.Point;
import java.util.ArrayList;
import javafx.scene.layout.GridPane;

public class ChessBoard extends GridPane {

    private static final ArrayList<Square> board = new ArrayList<>(); // Reference to squares on board, call with getBoard from other objects
    
    private static ChessBoard instance;
    
    
    private ChessBoard() {
    	instance = this;
        createBoard();
        drawBoard();
    }
    
    /**
     * Only one instance of ChessBoard can be active at a time. It will be instanced by the main class through this method instead of a constructor.
     * @return The Singleton instance of ChessBoard. If one is not active, it will be created.
     */
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
    
    public ArrayList<Square> getBoard() {
    	return board;
    }
    
    private void drawBoard() {
        for (Square s : board) {
            this.add(s, (int) s.getPoint().getX(), (int) s.getPoint().getY());
        }
    }
    
    public void clearBoard() {
        for (Square square : board) {
        	square.highlight(false);
        	square.clear();
        }
    }
    
    public Square getSquare(Point p) {
    	for (Square s : board) {
    		if (s.getPoint().equals(p)) {
    			return s;
    		}
    	}
    	return null;
    }
    
	public Square getKing(int player) {
		Piece p;
		for (Square s : board) {
			p = s.getPiece();
			if (p != null) {
				if (p instanceof King && p.getPlayer() == player) {
					return s;
				}
			}
		}
		return null;
	}
    
    /**
     * @param point Point on board
     * @param board Chess board to refer to
     * @return Returns -1 for empty, 0 for player 1, 1 for player 2 and -2 for no square
     */
    public int squareState(Point point) {
    	Square s = getSquare(point);
		if (s != null) {
			if (s.getPiece() != null) {
				return s.getPiece().getPlayer();
			}
			return -1;
		}
    	return -2;
    }
    
    @Override
    public String toString() {
        String temp = "";
        for (Square s : board) {
        	String piece = "";
        	if (s.getPiece() != null) {
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
