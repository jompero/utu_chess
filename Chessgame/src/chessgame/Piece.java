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
    protected int[][] moveSet;
    
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
    	int[][] moveSet = {{1,-1},{1,0},{1,1},{0,-1},{0,1},{-1,-1},{-1,0},{-1,1}};
    	range = 8;
    	
    	for (int[] move : moveSet){
    		for(int i=0;i<range;i++){
    			moves.add(new Point(x + (move[i]*(i+1)), y + (move[i]*(i+1))));
    		}
        }
    	return moves;
    }
}
