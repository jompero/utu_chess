package chessgame;

import java.awt.Point;
import java.util.ArrayList;

public class Chess {
    public static final int BOARDSIZE = 8;
    public static int SQUARESIZE = 70;
    
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
    	return round + ".[" + from.toString() + " " + to.toString() + "]";
    }
    

    /**
     * @param point Point on board
     * @param board Chess board to refer to
     * @return Returns -1 for empty, 0 for player 1, 1 for player 2
     */
    public static int squareState(Point point, ArrayList<Square> board) {
    	for (Square s : board) {
    		if (s.getPoint().equals(point)) {
    			if (s.getPoint() != null) {
    				return s.getPiece().getPlayer();
    			}
    		}
    	}
    	return -1;
    }
}
