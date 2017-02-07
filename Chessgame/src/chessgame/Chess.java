/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessgame;

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
    public static String cbn(int x, int y) {
        return String.valueOf((char) (y + 'A')) + Integer.toString(x + 1);
    }
    
    public static String pgnSum(String pgn, int[] offset) {
        char file = (char) (pgn.charAt(0) + (char) offset[1]);
        int rank = (int) Integer.valueOf(pgn.charAt(1)) + offset[0];
        return Character.toString(file) + rank;
    }
}
