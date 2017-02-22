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
public class King extends Piece {
    String spriteWhite = "img/kw.png";
    String spriteBlack = "img/kb.png";
    
    
    public King(int player) {
        super(player);
        sprite = player == 0 ? new Image(spriteWhite) : new Image(spriteBlack);
        setRange(1);
        drawSprite();
    }
    
    public boolean isValid() {
        return false;
    }
    
    @Override
    public String toString() {
        return player == 0 ? "K" : "k";
    }
    
}
