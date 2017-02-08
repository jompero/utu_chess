/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessgame;



import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;

/**
 *
 * @author Dani Jompero
 */
abstract class Piece extends ImageView {
    protected Image sprite;
    protected int player;
    protected boolean isKing;
    protected Square square;
    
    public Piece(int player) {
        this.player = player;
    }
    
    public void drawSprite() {
        super.setFitHeight(Chess.SQUARESIZE);
        super.setFitWidth(Chess.SQUARESIZE);
        this.setImage(sprite);
    }
    
    public abstract ArrayList<String> getMoves(int x, int y);
}