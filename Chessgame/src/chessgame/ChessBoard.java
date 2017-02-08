/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessgame;

import java.util.ArrayList;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Dani Jompero
 */
public class ChessBoard extends GridPane {

    private static final ArrayList<Square> board = new ArrayList<>(); // Reference to squares on board, call with getBoard from other objects
    private Piece piece;
    private Square square;
    
    private static ChessBoard instance;
    
    
    private ChessBoard() {
        createBoard();
        drawBoard();
    }
    
    // Singleton instance of ChessBoard
    public static ChessBoard getInstance() {
    	if (instance == null) {
    		instance = new ChessBoard();
    	}
    	return instance;
    }
    
    public void storePiece(Piece piece, Square square) {
    	this.square = square;
    	this.piece = piece;
    }
    
    public Piece takePiece() {
    	Piece temp = piece;
    	piece = null;
    	square.clearPiece();
    	unSelectAll();
    	return temp;
    }
    
    public void unSelectAll() {
    	for (Square s : board) {
    		s.unSelect();
    	}
    }
    
    public void unhighlightAl() {
    	for (Square s : board) {
    		s.unHighlight();
    	}
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
            this.add(s, s.getRank(), s.getFile());
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
        	if ((s.getRank() + 1) % Chess.BOARDSIZE == 0) {
        		temp += "\n";
        	}
        }
        return temp;
    }
}
