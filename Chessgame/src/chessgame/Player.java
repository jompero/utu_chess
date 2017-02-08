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
class Player {
    private String name;
    
    public Player(int player) {
    	name = player == 0 ? "Player 1" : "Player 2";
    }
    
    public String getName() {
    	return name;
    }
}
