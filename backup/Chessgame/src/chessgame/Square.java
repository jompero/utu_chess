/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessgame;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

/**
 *
 * @author Dani Jompero
 */
public class Square extends StackPane {
    int x;
    int y;
    String cbn;
    Check check;	//To render the square
    Piece piece;
    ArrayList<Square> availableMoves = new ArrayList<Square>();
        
    public Square(int x, int y) {
        this.x = x;
        this.y = y;
        this.cbn = Chess.cbn(x, y);
        check = new Check(x, y);
        this.getChildren().add(check);
        
        onMouseEnter();
        onMouseExit();
    }
    
    // Highlight possible moves on mouse enter.
    private void onMouseEnter() {
    	this.setOnMouseEntered(new EventHandler<MouseEvent> () {
			@Override
			public void handle(MouseEvent e) {
				if (piece != null) {
					for (Square s : availableMoves) {
						s.highlight();
					}
			      	System.out.println("This is square " + cbn + " and contains " + getPiece());
			      	System.out.println("Available moves for " + getPiece() + " are: " + availableMoves);
				} else {
					System.out.println("This is square " + cbn + " and contains no piece.");
				}
			}
    	});
    }
    
    // Disable higlight on mouse exit.
    private void onMouseExit() {
    	this.setOnMouseExited(new EventHandler<MouseEvent> () {
			@Override
			public void handle(MouseEvent e) {
				if (piece != null) {
					for (Square s : availableMoves) {
						s.highlight();
					}
				}
			}
    	});
    }
    
    public void highlight() {
    	check.toggleHighlight();
    }
    
    public void setPiece(Piece piece) {
        this.piece = piece;
        this.getChildren().add(piece);
        refreshMoves();
    }
    
    public void refreshMoves() {
    	ArrayList<String> moves = piece.getMoves(x, y);
    	if (moves != null) {
			for (String move : moves) {
				for (Square s : ChessBoard.getBoard()) {
					if (move.equals(s.toString())) {
						availableMoves.add(s);
					}
				}
			}
		} else {
			availableMoves.clear();
		}
    }
    
    public Piece getPiece() {
        return piece;
    }
    
    public int getRank() {
        return x;
    }
    
    public int getFile() {
        return y;
    }
    
    @Override
    public String toString() {
        return cbn;
    }
}
