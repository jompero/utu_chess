/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessgame;

import java.awt.Point;
import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

/**
 *
 * @author Dani Jompero
 */
public class Square extends StackPane {
    Point point;
    String cbn;
    ArrayList<Square> availableMoves = new ArrayList<Square>();
    
    // Visual components
    Check check;
    Piece piece;
    
    private boolean highlighted;
	private boolean selected;
        
    public Square(int x, int y) {
        this.point = new Point(x, y);
        this.cbn = Chess.cbn(point);
        check = new Check(point);
        this.getChildren().add(check);
        
        onClick();
        onMouseEnter();
        onMouseExit();
    }
    
    // Prepare or swap piece on click.
    private void onClick() {
    	this.setOnMousePressed(new EventHandler<MouseEvent> () {
			@Override
			public void handle(MouseEvent e) {
				handleClick();
			}
    	});
    }
    
    private void handleClick() {
    	GameManager.getInstance().clickQueue(this);
    }
    
    // Highlight possible moves on mouse enter.
    private void onMouseEnter() {
    	this.setOnMouseEntered(new EventHandler<MouseEvent> () {
			@Override
			public void handle(MouseEvent e) {
				highlightMoves();
			}
    	});
    }
    
    // Disable highlight on mouse exit.
    private void onMouseExit() {
    	this.setOnMouseExited(new EventHandler<MouseEvent> () {
			@Override
			public void handle(MouseEvent e) {
				highlightMoves();
			}
    	});
    }
    
    public void highlightMoves() {
    	if (piece != null) {
			for (Square s : availableMoves) {
				s.highlight();
			}
		}
    }
    
    public void highlight() {
    	highlighted = !highlighted;
    	check.highlightFX(highlighted);
    }
    
    public void select() {
    	selected = !selected;
    	check.selectFX(selected);
    }

    public void refreshMoves() {
    	availableMoves.clear();
    	ArrayList<Point> moves = piece.getMoves((int) point.getX(), (int) point.getY());
    	System.out.println(piece.toString() + " getMoves() " + moves);
    	if (moves != null) {
			for (Point move : moves) {
				for (Square s : ChessBoard.getBoard()) {
					if (move.equals(s.getPoint())) {
						availableMoves.add(s);
					}
				}
			}
		}
    	System.out.println(cbn + " availableMoves " + availableMoves);
    }
    
    public ArrayList<Square> getAvailableMoves() {
    	return availableMoves;
    }
    
    public Point getPoint() {
    	return point;
    }
    
    public Piece getPiece() {
        return piece;
    }
    
    public void setPiece(Piece piece) {
        if (this.getChildren().size() > 1)
        	this.getChildren().remove(1);
        this.piece = piece;
        this.getChildren().add(piece);
        refreshMoves();
    }
    
    @Override
    public boolean equals(Object o) {
		if (o.toString().equals(this.toString()))
			return true;
    	return false;
    }
    
    @Override
    public String toString() {
    	String temp = "";
    	if (piece != null) { temp = this.piece.toString(); }
    	temp += cbn;
        return temp;
    }
}
