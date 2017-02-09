/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessgame;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
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
    boolean selected;
    boolean highlighted;
    Piece piece;
    ArrayList<Square> availableMoves = new ArrayList<Square>();
        
    public Square(int x, int y) {
    	ChessBoard cb = ChessBoard.getInstance();
    	
        this.x = x;
        this.y = y;
        this.cbn = Chess.cbn(x, y);
        check = new Check(x, y);
        this.getChildren().add(check);
        
        onMouseEnter();
        onMouseExit();
    }
    
    // Click to select a square. Method makes sure to disable other selections.
    private void onMouseClick(boolean enabled) {
    	EventHandler<MouseEvent> eh = new EventHandler<MouseEvent> () {
			@Override
			public void handle(MouseEvent e) {
				if (piece != null && piece.getPlayer() == 0) {
					storePiece();
					select();
				} 
				else {
					movePiece();
				}
			}
    	};
    	if (enabled) {
        	this.setOnMouseClicked(eh);
    	} else {
    		this.removeEventHandler(MouseEvent.MOUSE_CLICKED, eh);
    	}
    }
    
    // Disable highlight on mouse exit.
    private void onMouseExit() {
    	this.setOnMouseExited(new EventHandler<MouseEvent> () {
			@Override
			public void handle(MouseEvent e) {
				if (!selected) {
					for (Square s : availableMoves) {
						s.unHighlight();
					}
				}
			}
    	});
    }
    
    // Highlight possible moves on mouse enter.
    private void onMouseEnter() {
    	this.setOnMouseEntered(new EventHandler<MouseEvent> () {
			@Override
			public void handle(MouseEvent e) {
				if (piece != null && !selected) {
					highlightAvailableMoves();
				}
			}
    	});
    }
    
    private void storePiece() {
    	if (piece != null) {
    		ChessBoard.getInstance().storePiece(piece, this);
    	}
    }
    
    private void movePiece() {
    	this.getChildren().removeAll();
    }
    
    public void highlight() {
    	highlighted = !highlighted;
    	check.highlight();
    	if (piece == null)
    		onMouseClick(true);
    }
    
    public void unHighlight() {
    	highlighted = !highlighted;
    	check.unHighlight();
    	if (piece == null)
        	onMouseClick(false);
    }
    
    private void highlightAvailableMoves() {
		for (Square s : availableMoves) {
			s.highlight();
		}
    }
    
    public void select() {
    	selected = true;
    	check.select();
    }
    
    public void unSelect() {
    	selected = false;
    	check.unSelect();
    }
    
    public void setPiece(Piece piece) {
        this.piece = piece;
        this.getChildren().add(piece);
        refreshMoves();
        onMouseClick(true);
    }
    
    public void refreshMoves() {
    	if (piece != null) {
        	ArrayList<String> moves = piece.getMoves(x, y);
        	if (moves != null) {
    			for (String move : moves) {
    				for (Square s : ChessBoard.getBoard()) {
    					if (move.equals(s.toString())) {
    						availableMoves.add(s);
    					}
    				}
    			}
    			return;
    		}
    	}
    	availableMoves.clear();
    }
    
    public Piece getPiece() {
        return piece;
    }
    
    public void clearPiece() {
    	piece = null;
		for (Square s : availableMoves) {
			s.unHighlight();
		}
    	refreshMoves();
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
