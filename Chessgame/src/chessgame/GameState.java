package chessgame;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;

public class GameState implements Serializable {
    String[] players = {"Player1", "Player2"};
    ArrayList<Point> moveHistory = new ArrayList<>();
    
    private static final long serialVersionUID = 1L;
    
    public void logTurn(int round, Square from, Square to) {
    	moveHistory.add(round * 2, from.getPoint());
    	moveHistory.add(round * 2 + 1, to.getPoint());
    }
    
    public void setPlayer(String name, int player) {
    	players[player] = name;
    }
    
    public String[] getPlayers() {
    	return players;
    }
    
    public String getPlayer(int player) {
    	return players[player];
    }
    
    public ArrayList<Point> getMoveHistory() {
    	return moveHistory;
    }
}
