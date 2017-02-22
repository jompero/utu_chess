package chessgame;

import javafx.scene.image.Image;

public class Queen extends Piece {
    String spriteWhite = "img/qw.png";
    String spriteBlack = "img/qb.png";
    
    public Queen(int player) {
        super(player);
        sprite = player == 0 ? new Image(spriteWhite) : new Image(spriteBlack);
        drawSprite();
    }

    @Override
    public String toString() {
        return player == 0 ? "Q" : "q";
    }
}
