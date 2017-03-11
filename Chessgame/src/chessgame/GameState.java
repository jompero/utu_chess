package chessgame;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;

public class GameState implements Serializable {
    String[] players = {"Player1", "Player2"};
    ArrayList<Point> moveHistory = new ArrayList<>();
    
    private static final long serialVersionUID = 1L;
    
    /** Copy state until round
     * 
     * @param gs GameState top be copied
     * @param round round <= ges.getMoveHistory.size()
     */
    public GameState (GameState gs, int round) {
    	this.players = new String[] {gs.getPlayer(0), gs.getPlayer(1)};
    	for (Point move : gs.getMoveHistory().subList(0, (round - 1) * 2)) {
    		this.moveHistory.add(move);
    	}
    }
    
    public GameState (GameState gs) {
    	this.players = new String[] {gs.getPlayer(0), gs.getPlayer(1)};
    	for (Point move : gs.getMoveHistory()) {
    		this.moveHistory.add(move);
    	}
    }
    
    public GameState() {}
    
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
