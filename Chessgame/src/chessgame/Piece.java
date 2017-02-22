package chessgame;

import java.awt.Point;
import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

abstract class Piece extends ImageView {
    protected Image sprite;
    protected int player;
    protected boolean isKing;
    protected Square square;
    protected int range;
    protected int direction;
    int[][] moveSet;
    
    public Piece(int player) {
        this.player = player;
    }
    
    public int getPlayer() {
    	return player;
    }
    
    public void drawSprite() {
        super.setFitHeight(Chess.SQUARESIZE);
        super.setFitWidth(Chess.SQUARESIZE);
        this.setImage(sprite);
    }
    
    public ArrayList<Point> getMoves(int x, int y){
    	ArrayList<Point> moves = new ArrayList<>();
    	ArrayList<Square> board = ChessBoard.getBoard();
    	int[][] moveSet = new int[][] {{1, 2}, {-1, 2}, {2, 1}, {-2, 1}, {1,-2}, {-1,-2}, {2,-1}, {-2,-1}};
    	
    	for (int[] move : moveSet){
            moves.add(new Point(x + move[0], y + move[1]));
        }
    	return moves;
    }
}
