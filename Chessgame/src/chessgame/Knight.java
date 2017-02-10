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
    
    int[][] moveSet = new int[][] {{1, 2}, {-1, 2}, {2, 1}, {-2, 1}, {1,-2}, {-1,-2}, {2,-1}, {-2,-1}};
    
    public Knight(int player) {
        super(player);
        sprite = player == 0 ? new Image(spriteWhite) : new Image(spriteBlack);
        drawSprite();
    }

    @Override
    public ArrayList<Point> getMoves(int x, int y) {
        ArrayList<Point> moves = new ArrayList<>();
        if (player == 1) {
            for (int[] move : moveSet){
                moves.add(new Point(x + move[0], y + move[1]));
            }  
        } else {
            for (int[] move : moveSet){
                moves.add(new Point(x - move[0], y - move[1]));
            }
        }
        return moves;
    }
    
    @Override
    public String toString() {
        return player == 0 ? "N" : "n";
    }
}
