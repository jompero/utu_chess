package chessgame;

import java.awt.Point;
import java.util.ArrayList;
import javafx.scene.image.Image;


public class Bishop extends Piece {
    
    String spriteWhite = "img/bw.png";
    String spriteBlack = "img/bb.png";
    
    public Bishop(int player) {
        super(player);
        setRange(8);
        setMoveSet(new int[][] {{1,1},{-1,1},{1,-1},{-1,-1}});
        sprite = player == 0 ? new Image(spriteWhite) : new Image(spriteBlack);
        drawSprite();
    }
    
    @Override
    public String toString() {
        return player == 0 ? "B" : "b";
    }
}
