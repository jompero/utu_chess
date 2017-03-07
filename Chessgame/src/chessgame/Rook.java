package chessgame;

import javafx.scene.image.Image;


public class Rook extends Piece {
	
    String spriteWhite = "img/rw.png";
    String spriteBlack = "img/rb.png";
    
    public Rook(int player) {
        super(player);
        setRange(8);
        setMoveSet(new int[][] {{1,0},{0,1},{-1,0},{0,-1}});
        sprite = player == 0 ? new Image(spriteWhite) : new Image(spriteBlack);
        drawSprite();
    }
    
    @Override
    public String toString() {
        return player == 0 ? "R" : "r";
    }
}
