package chessgame;

import javafx.scene.image.Image;


public class Pawn extends Piece {
	
    String spriteWhite = "img/pw.png";
    String spriteBlack = "img/pb.png";
    int dir = player == 0 ? -1 : 1;
     
    public Pawn(int player) {
        super(player);
        setRange(1);
        setMoveSet(new int[][] {{0,dir}});
        sprite = player == 0 ? new Image(spriteWhite) : new Image(spriteBlack);
        drawSprite();
    }
    @Override
    public String toString() {
        return player == 0 ? "P" : "p";
    }  
}
