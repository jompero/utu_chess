package chessgame;

import java.awt.Point;
import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

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
    
    private void refreshMoves() {
    	availableMoves.clear();
    	ArrayList<Point> moves = piece.getMoves((int) point.getX(), (int) point.getY());
    	if (moves != null) {
			for (Point move : moves) {
				for (Square s : ChessBoard.getBoard()) {
					if (move.equals(s.getPoint())) {
						availableMoves.add(s);
					}
				}
			}
		}
    }
    
    public void clear() {
        if (this.getChildren().size() > 1)
        	this.getChildren().remove(1);
    }
    
    // MOUSE EVENT HANDLING
    // Prepare or swap piece on click.
    private void onClick() {
    	setOnMousePressed(new EventHandler<MouseEvent> () {
			@Override
			public void handle(MouseEvent e) {
				GameManager.getInstance().clickQueue((Square) e.getSource());
			}
    	});
    }
    
    // Highlight possible moves on mouse enter.
    private void onMouseEnter() {
    	setOnMouseEntered(new EventHandler<MouseEvent> () {
			@Override
			public void handle(MouseEvent e) {
				highlightMoves();
			}
    	});
    }
    
    // Disable highlight on mouse exit.
    private void onMouseExit() {
    	setOnMouseExited(new EventHandler<MouseEvent> () {
			@Override
			public void handle(MouseEvent e) {
				highlightMoves();
			}
    	});
    }
	// -------------------------------------------------- //
    
    // JAVAFX EFFECTS
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
    // -------------------------------------------------- //
    
    // GET AND SET METHODS
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
        clear();
        this.piece = piece;
        this.getChildren().add(piece);
        refreshMoves();
    }
 // -------------------------------------------------- //
    
    // OVERRIDE METHODS
    @Override
    public boolean equals(Object o) {
    	Square s = (Square) o;
		if (s.getPoint().equals(this.point))
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
