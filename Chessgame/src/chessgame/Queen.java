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
public class Queen extends Piece {
    String spriteWhite = "img/qw.png";
    String spriteBlack = "img/qb.png";
    
    public Queen(int player) {
        super(player);
        sprite = player == 0 ? new Image(spriteWhite) : new Image(spriteBlack);
        drawSprite();
    }

    public ArrayList<Point> getMoves(int x, int y) {
        ArrayList<Point> moves = new ArrayList<>();
        return moves;
    }
    
    @Override
    public String toString() {
        return player == 0 ? "Q" : "q";
    }
}
