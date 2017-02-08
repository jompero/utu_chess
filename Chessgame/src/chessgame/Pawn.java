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
public class Pawn extends Piece {
    String spriteWhite = "img/pw.png";
    String spriteBlack = "img/pb.png";
    
    int[][] moveSet = new int[][] {{0, 1},{0, 2}};
    
    public Pawn(int player) {
        super(player);
        sprite = player == 0 ? new Image(spriteWhite) : new Image(spriteBlack);
        drawSprite();
    }
    
    @Override
    public ArrayList<String> getMoves(int x, int y) {
        ArrayList<String> moves = new ArrayList<>();
        if (player == 1) {
            for (int[] move : moveSet){
                moves.add(Chess.cbn(x + move[0], y + move[1]));
            }  
        } else {
            for (int[] move : moveSet){
                moves.add(Chess.cbn(x - move[0], y - move[1]));
            }
        }
        return moves;
    }
    
    @Override
    public String toString() {
        return player == 0 ? "P" : "p";
    }
    
}
