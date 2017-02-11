/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessgame;

import java.awt.Point;

/**
 *
 * @author Dani Jompero
 */
public class Chess {
    public static final int BOARDSIZE = 8;
    public static int SQUARESIZE = 70;
    
    /**
     *
     * @param x 'x' location of given coordinate
     * @param y 'y' location of given coordinate
     * @return  the Portable Game Notation of given coordinate
     */
    public static String cbn(Point p) {
        return String.valueOf((char) (p.getX() + 'A')) + Integer.toString((int) p.getY() + 1);
    }
    
    public static String printPGN(int round, Square from, Square to) {
    	return round + ".[" + from.toString() + " " + to.toString() + "]";
    }
}
