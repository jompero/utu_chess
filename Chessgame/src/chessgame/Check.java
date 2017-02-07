/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessgame;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Dani Jompero
 */
class Check extends Rectangle {
    private final Color paint;
    private final Color dark = Color.DARKGRAY;
    private final Color light = Color.LIGHTGRAY;
    
    public Check(int x, int y) {
        super(Chess.SQUARESIZE, Chess.SQUARESIZE);
        
        paint = ((x + y) % 2) == 0 ? dark : light;
        this.setFill(paint);
    }

}