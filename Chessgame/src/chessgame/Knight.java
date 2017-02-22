/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessgame;

import java.awt.Point;
import java.util.ArrayList;
import javafx.scene.image.Image;

/**
 *
 * @author Dani Jompero
 */
public class Knight extends Piece {
    String spriteWhite = "img/jw.png";
    String spriteBlack = "img/jb.png";
      
    public Knight(int player) {
        super(player);
        setRange(1);
        setMoveSet(new int[][] {{1, 2}, {-1, 2}, {2, 1}, {-2, 1}, {1,-2}, {-1,-2}, {2,-1}, {-2,-1}});
        sprite = player == 0 ? new Image(spriteWhite) : new Image(spriteBlack);
        drawSprite();
    }
    
    @Override
    public String toString() {
        return player == 0 ? "N" : "n";
    }
}