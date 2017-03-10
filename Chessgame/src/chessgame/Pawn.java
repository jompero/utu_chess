package chessgame;

import java.awt.Point;
import java.util.ArrayList;

import javafx.scene.image.Image;


public class Pawn extends Piece {
	
    String spriteWhite = "img/pw.png";
    String spriteBlack = "img/pb.png";
    int dir = player == 0 ? -1 : 1;
    int[][] captureMoves;
     
    public Pawn(int player) {
        super(player);
        setRange(2);
        setMoveSet(new int[][] {{0,dir}});
        captureMoves = new int[][]{{1,dir}, {-1,dir}};
        sprite = player == 0 ? new Image(spriteWhite) : new Image(spriteBlack);
        drawSprite();
    }
    
    @Override
    public ArrayList<Point> getMoves(Point point) {
		ArrayList<Point> moves = new ArrayList<>();
		int x = (int) point.getX();
		int y = (int) point.getY();
		
		// Pawnin aloitus siirto
		if(y != 6 && y != 1){
			setRange(1);
		}
		
		for (int[] move : captureMoves) {
				Point  p = new Point(x + move[0], y + (move[1]));
				int squareState = cb.squareState(p);
				if (squareState > -1) {
					if (gm.getTurn() != squareState) {
                        moves.add(p);
                    }
				}
				
		}
		
		for (int[] move : moveSet) {
			for (int i = 0; i < range; i++) {
				Point  p = new Point(x + (move[0] * (i + 1)), y + (move[1] * (i + 1)));
				int squareState = cb.squareState(p);
				if (squareState > -1) {
					break;
				}
				else if (squareState < -1) {
					break;
				}
				moves.add(p);
			}
		}
		return moves;
	}
    
    @Override
    public String toString() {
        return player == 0 ? "P" : "p";
    }  
}
