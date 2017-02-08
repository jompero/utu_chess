/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessgame;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

/**
 *
 * @author Dani Jompero
 */
class Check extends Rectangle {
    private final Color paint;
    private final Color dark = Color.DARKGRAY;
    private final Color light = Color.LIGHTGRAY;
    private final Color highlight = Color.YELLOW;
    private final Color select = Color.WHITE;
    private boolean highlighted = false;
    private boolean selected = false;
    
    public Check(int x, int y) {
        super(Chess.SQUARESIZE, Chess.SQUARESIZE);
        
        paint = ((x + y) % 2) == 0 ? dark : light;
        this.setFill(paint);
        this.setStrokeType(StrokeType.INSIDE);
        this.setStrokeWidth(0);
    }
    
    public void unHighlight() {
    	highlighted = false;
    	this.setStroke(select);
    	toggleStroke();
    }
    
    public void highlight() {
    	highlighted = true;
    	this.setStroke(highlight);
    	toggleStroke();
    }
    
    public void unSelect() {
    	selected = false;
    	toggleStroke();
    }
    
    public void select() {
    	ChessBoard.getInstance().unSelectAll();
    	selected = !selected;
    	this.setStroke(select);
    	toggleStroke();
    }
    
    private void toggleStroke() {
    	this.setStrokeWidth(highlighted || selected ? 2 : 0);
    }

}