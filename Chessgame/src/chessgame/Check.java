/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessgame;

import java.awt.Point;

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
    InnerShadow noShadow = new InnerShadow(0, 0, 0, Color.BLACK);
    InnerShadow glow = new InnerShadow(10, 0, 0, Color.CYAN);
    InnerShadow noGlow = new InnerShadow(0, 0, 0, Color.YELLOW);
    
    
    public Check(Point p) {
        super(Chess.SQUARESIZE, Chess.SQUARESIZE);
        
        paint = ((p.getX() + p.getY()) % 2) == 0 ? dark : light;
        this.setFill(paint);
        this.setStrokeType(StrokeType.INSIDE);
        this.setStrokeWidth(0);
        this.setStroke(highlight);
    }
    
    public void highlightFX(boolean isOn) {
    	if (isOn) {
    		this.setEffect(glow);
    	} else {
    		this.setEffect(noGlow);
    	}
    }
    
    public void selectFX(boolean isOn) {
    	if (isOn) {
    		this.setEffect(shadow);
    	} else {
    		this.setEffect(noShadow);
    	}
    }

}