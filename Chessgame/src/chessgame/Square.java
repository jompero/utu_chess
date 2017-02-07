/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessgame;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

/**
 *
 * @author Dani Jompero
 */
public class Square extends StackPane {
    int x;
    int y;
    String cbn;
    
    Check check;
    Piece piece;
        
    public Square(int x, int y) {
        this.x = x;
        this.y = y;
        this.cbn = Chess.cbn(x, y);
        check = new Check(x, y);
        this.getChildren().add(check);
        
        EventHandler eh = new EventHandler<MouseEvent> () {
            @Override
            public void handle(MouseEvent e) {
                if (piece != null) {
                    Node source = (Node) e.getSource();
                    Integer colIndex = GridPane.getColumnIndex(source);
                    Integer rowIndex = GridPane.getRowIndex(source);
                    System.out.println("This is square " + cbn + " and contains " + getPiece());
                    System.out.println("Available moves for " + getPiece() + " are: " + getPiece().getMoves(y, x));
                }
            }
        };
        this.setOnMouseEntered(eh);
    }
    
    public void setPiece(Piece piece) {
        this.piece = piece;
        this.getChildren().add(piece);
    }
    
    public Piece getPiece() {
        return piece;
    }
    
    public int getRank() {
        return x;
    }
    
    public int getFile() {
        return y;
    }
    
    @Override
    public String toString() {
        return cbn;
    }
}
