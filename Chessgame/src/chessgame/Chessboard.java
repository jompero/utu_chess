/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessgame;

import java.awt.Color;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Dani Jompero
 */
public class Chessboard extends GridPane {

    private final ArrayList<Square> board = new ArrayList<>();
    
    public Chessboard() {
        createBoard();
        drawBoard();
    }
    
    private void createBoard() {
        for (int file = 0; file < Chess.BOARDSIZE; file++) {
            for (int rank = 0; rank < Chess.BOARDSIZE; rank++) {
                board.add(new Square(rank, file));
            }
        }
    }
    
    private void drawBoard() {
        for (Square s : board) {
            this.add(s, s.getRank(), s.getFile());
        }
    }
    
    @Override
    public String toString() {
        String temp = "Shakkilauta\n A B C D E F G H";
        int row = 0;
        ObservableList<Node> children = this.getChildren();
        for (int i = 0; i < children.size(); i++) {
            if (i % Chess.BOARDSIZE == 0 && i != children.size() -1) {
                temp += "\n" + Integer.toString(i);
                row++;
            }
            //Check s = (Check) children.get(i);
            /*if (s.getPiece() != null) {
                temp += "[" + s.getPiece().toString() + "]";
            } else {*/
                temp += "[ ]";
            //}
        }
        temp += "\n";
        for (int i = 0; i < Chess.BOARDSIZE; i++) {
            temp = temp + "  " + Integer.toString(i + 1);
        }
        return temp;
    }
}
