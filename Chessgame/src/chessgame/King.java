/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessgame;

import java.util.ArrayList;
import javafx.scene.image.Image;

/**
 *
 * @author Dani Jompero
 */
public class King extends Piece {
    String spriteWhite = "img/kw.png";
    String spriteBlack = "img/kb.png";
    
    // Offset of all possible moves
    int[][] moveSet = {
        {1,-1},
        {1,0},
        {1,1},
        {0,-1},
        {0,1},
        {-1,-1},
        {-1,0},
        {-1,1}
    };
    
    public King(int player) {
        super(player);
        sprite = player == 0 ? new Image(spriteWhite) : new Image(spriteBlack);
        isKing = true;
        drawSprite();
    }

    public void setPosition(Square square) {
        
    }
    
    @Override
    public ArrayList<String> getMoves(int x, int y) {
        ArrayList<String> moves = new ArrayList<>();
        return null;
    }
    
    public boolean isValid() {
        return false;
    }
    
    @Override
    public String toString() {
        return player == 0 ? "K" : "k";
    }
    
}
