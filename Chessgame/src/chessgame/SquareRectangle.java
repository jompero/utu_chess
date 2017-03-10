package chessgame;

import java.awt.Point;

import javafx.scene.effect.InnerShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

class SquareRectangle extends Rectangle {
    final Color paint;
    final Color dark = Color.DARKGRAY;
    final Color light = Color.LIGHTGRAY;
    final Color highlight = Color.WHITE;
    final int strokeWeight = 4;
    InnerShadow shadow = new InnerShadow(4, 2, 2, Color.BLACK);
    InnerShadow noShadow = new InnerShadow(0, 0, 0, Color.BLACK);
    
    
    public SquareRectangle(Point p) {
        super(Chess.SQUARESIZE, Chess.SQUARESIZE);
        
        paint = ((p.getX() + p.getY()) % 2) == 0 ? dark : light;
        this.setFill(paint);
        this.setStroke(highlight);
        this.setStrokeType(StrokeType.INSIDE);
        this.setStrokeWidth(0);
    }
    
    public void highlightFX(boolean isOn) {
    	this.setStrokeWidth(isOn ? strokeWeight : 0);
    }
    
    public void selectFX(boolean isOn) {
    	if (isOn) {
    		this.setEffect(shadow);
    	} else {
    		this.setEffect(noShadow);
    	}
    }

}