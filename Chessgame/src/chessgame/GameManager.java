/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessgame;

import java.util.ArrayList;

/**
 *
 * @author Dani Jompero
 */
public class GameManager {
	
    int round = 0;
    Square s;
    
    static GameManager instance;
    
    private GameManager() {
        defaultStart();
    }
    
    public static GameManager getInstance() {
    	if (instance == null) {
    		instance = new GameManager();
    	}
    	return instance;
    }
    
    public void clickQueue(Square s) {
    	if (this.s != null) {
        	if (this.s.getPiece() != null) {
        		swapContent(this.s, s);
        		return;
        	}
    	}
    	this.s = s;
    }
    
    public void swapContent(Square from, Square to) {
    	to.setPiece(from.getPiece());
    	from.clearPiece();
    }
    
    public void defaultStart() {
        String backline= "RNBQKBNR"; 
        
        for (int i = 0; i < backline.length(); i++) {
            ArrayList<Square> s = ChessBoard.getBoard();
            
            // Set player 2 backline
            s.get(i).setPiece(newPiece(backline.charAt(i), 1));
            // Set player 1 backline
            s.get(i + 7 * Chess.BOARDSIZE).setPiece(newPiece(backline.charAt(i), 0));
            
            //Set pawns
            s.get(i + Chess.BOARDSIZE).setPiece(newPiece('P', 1));
            s.get(i + 6 * Chess.BOARDSIZE).setPiece(newPiece('P', 0));
        }
    }
    
    private Piece newPiece(char piece, int player) {
        switch (piece) {
            case 'R':   return new Rook(player);
            case 'N':   return new Knight(player);           
            case 'B':   return new Bishop(player);
            case 'Q':   return new Queen(player);
            case 'K':   return new King(player);
            case 'P':   return new Pawn(player);
            default:    return null;
        }
    }
}
