package chessgame;

import java.awt.Point;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

public class Square extends StackPane {
	// Point data and visuals
    private Point point;
    private SquareRect check;
    // Contains a possible
    private Piece piece;
      
    public Square(int x, int y) {
        this.point = new Point(x, y);
        check = new SquareRect(point);
        this.getChildren().add(check);
        
        onClick();
    }
    
    public void clear() {
    	if (this.getChildren().size() > 1)
        	this.getChildren().remove(1);
        piece = null;
    }
    
    // -------------- MOUSE EVENT HANDLING -------------- //
    // Prepare or swap piece on click.
    private void onClick() {
    	setOnMousePressed(new EventHandler<MouseEvent> () {
			@Override
			public void handle(MouseEvent e) {
				GameManager.getInstance().clickQueue((Square) e.getSource());
			}
    	});
    }
	// -------------------------------------------------- //
    
    // ----------------- JAVAFX EFFECTS ----------------- //
    public void highlight(boolean toggle) {
    	check.highlightFX(toggle);
    }
    
    public void select(boolean toggle) {
    	check.selectFX(toggle);
    }
    // -------------------------------------------------- //
    
    // --------------- GET AND SET METHODS -------------- //
    public Point getPoint() {
    	return point;
    }
    
    public Piece getPiece() {
        return piece;
    }
    
    public void setPiece(Piece piece) {
        clear();
        
        // Promotion
        if (point.getY() == 0 || point.getY() == 7) {
        	if (piece instanceof Pawn) {
        		int player = piece.getPlayer();
        		piece = new Queen(player);
        	}
        }
        
        this.piece = piece;
        this.getChildren().add(piece);
    }
    // -------------------------------------------------- //
    
    // ---------------- OVERRIDE METHODS ---------------- //
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
    	temp += Chess.cbn(point);
        return temp;
    }
}
