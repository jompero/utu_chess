package chessgame;

import java.awt.Point;

public class Chess {
    public static final int BOARDSIZE = 8;
    public static int SQUARESIZE = 80;
    
    /**
     *
     * @param p Point location of square
     * @return  the Chess board notation of given coordinate
     */
    public static String cbn(Point p) {
        return String.valueOf((char) (p.getX() + 'A')) + Integer.toString((int) p.getY() + 1);
    }
    
    /**
    *
    * @param round The round of the move
    * @param from Square the move starts from
    * @param to Square the move ends to
    * @return  the Portable Game Notation of given move
    */
    public static String printPGN(int round, Square from, Square to) {
    	if (from != null && to != null)
    		return round + ".[" + from.toString() + " " + to.toString() + "]";
    	return null;
    }
}
