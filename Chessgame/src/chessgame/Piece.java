package chessgame;

import java.awt.Point;
import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

abstract class Piece extends ImageView {
    protected Image sprite;
    protected int player;
    protected int range = 8;
    protected int[][] moveSet = {{1,-1},{1,0},{1,1},{0,-1},{0,1},{-1,-1},{-1,0},{-1,1}};
    
    public Piece(int player) {
        this.player = player;
    }
        
    public void drawSprite() {
        super.setFitHeight(Chess.SQUARESIZE);
        super.setFitWidth(Chess.SQUARESIZE);
        this.setImage(sprite);
    }
    
    public ArrayList<Point> getMoves(int x, int y){
    	ArrayList<Point> moves = new ArrayList<>();
    	ArrayList<Square> board = ChessBoard.getBoard();
    	
    	for (int[] move : moveSet){
    		for(int i=0;i<range;i++){
    			moves.add(new Point(x + (move[0]*(i+1)), y + (move[1]*(i+1))));
    		}
        }
    	return moves;
    }
    
    public int getPlayer() {
    	return player;
    }
    
    /**
     * @param range >= 0
     */
    protected void setRange(int range) {
    	this.range = range;
    }
    protected void setMoveSet(int[][] moveSet) {
    	this.moveSet = moveSet;
    }
}
