/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessgame;

import javafx.scene.effect.InnerShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

/**
 *
 * @author Dani Jompero
 */
class Check extends Rectangle {
    final Color paint;
    final Color dark = Color.DARKGRAY;
    final Color light = Color.LIGHTGRAY;
    final Color highlight = Color.YELLOW;
    final int strokeWeight = 2;
    InnerShadow shadow = new InnerShadow(4, 2, 2, Color.BLACK);
    
    
    public Check(int x, int y) {
        super(Chess.SQUARESIZE, Chess.SQUARESIZE);
        
        paint = ((x + y) % 2) == 0 ? dark : light;
        this.setFill(paint);
        this.setStrokeType(StrokeType.INSIDE);
        this.setStrokeWidth(0);
        this.setStroke(highlight);
    }
    
    public void highlightFX(boolean isOn) {
    	this.setStrokeWidth(isOn ? strokeWeight : 0);
    }
    
    public void selectFX(boolean isOn) {
    	this.setEffect(shadow);
    }

}