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
public class Pawn extends Piece {
    String spriteWhite = "img/pw.png";
    String spriteBlack = "img/pb.png";
    
    boolean hasMoved = false;
    
    int[][] moveSet = new int[][] {{0, 1},{0, 2}};
    int[][] moveSet2 = new int[][] {{0, 1}};
    
    public Pawn(int player) {
        super(player);
        sprite = player == 0 ? new Image(spriteWhite) : new Image(spriteBlack);
        drawSprite();
    }
    
    @Override
    public ArrayList<Point> getMoves(int x, int y) {
        ArrayList<Point> moves = new ArrayList<>();
        if (player == 1) {
        	if(hasMoved==false){
            	for (int[] move : moveSet){
            		moves.add(new Point(x + move[0], y + move[1]));
            		hasMoved=true;
            	}
            }
        	else{
        			for (int[] move : moveSet2){
            		moves.add(new Point(x + move[0], y + move[1]));	
        		}
        	}
        	
        } else {
        	if(hasMoved==false){
            	for (int[] move : moveSet){
            		moves.add(new Point(x - move[0], y - move[1]));
            		hasMoved=true;
            	}
            }
        	else{
        			for (int[] move : moveSet2){
            		moves.add(new Point(x - move[0], y - move[1]));	
        		}
        	}
        }
        return moves;
    }
    
    @Override
    public String toString() {
        return player == 0 ? "P" : "p";
    }
    
}
