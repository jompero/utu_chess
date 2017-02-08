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
    ChessBoard cb;
    
    public GameManager(ChessBoard cb) {
        this.cb = cb;
        defaultStart();
    }
    
    public void defaultStart() {
        String backline= "RNBQKBNR"; 
        
        for (int i = 0; i < backline.length(); i++) {
            // Set player 2 backline
            Square s1 = (Square) cb.getChildren().get(i);
            s1.setPiece(newPiece(backline.charAt(i), 1));
            // Set player 1 backline
            Square s2 = (Square) cb.getChildren().get(i + 7 * 8);
            s2.setPiece(newPiece(backline.charAt(i), 0));
            
            //Set pawns
            Square s3 = (Square) cb.getChildren().get(i + 8);
            s3.setPiece(newPiece('P', 1));
            Square s4 = (Square) cb.getChildren().get(i + 6 * 8);
            s4.setPiece(newPiece('P', 0));
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
