package chessgame;

import java.awt.Point;
import java.util.ArrayList;
import javafx.scene.image.Image;

public class Pawn extends Piece {
    String spriteWhite = "img/pw.png";
    String spriteBlack = "img/pb.png";
    
    boolean hasMoved = false;
    
    int[][] moveSet = new int[][] {{0, 1},{0, 2}};
    int[][] moveSet2 = new int[][] {{0, 1}};
    
    public Pawn(int player) {
        super(player);
        sprite = player == 0 ? new Image(spriteWhite) : new Image(spriteBlack);
        drawSprite();
    }
    
    @Override
    public ArrayList<Point> getMoves(Point point) {
    	int x = (int) point.getX();
    	int y = (int) point.getY();
    	
    	if (y != 6 - (player * 5)) {
    		hasMoved = true;
    	}
    	
		ArrayList<Point> moves = new ArrayList<>();
		if (player == 1) {
			if (hasMoved == false) {
				for (int[] move : moveSet) {
					moves.add(new Point(x + move[0], y + move[1]));
				}
			} else {
				moves.add(new Point(x + moveSet[0][0], y + moveSet[0][1]));
			}

		} else {
			if (hasMoved == false) {
				for (int[] move : moveSet) {
					moves.add(new Point(x - move[0], y - move[1]));
				}
			} else {
				moves.add(new Point(x - moveSet[0][0], y - moveSet[0][1]));
			}
		}
		return moves;
	}
    
    @Override
    public String toString() {
        return player == 0 ? "P" : "p";
    }
    
}
