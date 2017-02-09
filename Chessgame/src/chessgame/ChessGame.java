/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessgame;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Dani Jompero
 */
public class ChessGame extends Application {

	ChessBoard cb;
	GameManager gm;
	
    public static void main(String[] args) {
        Application.launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Shakkipeli");
        
        Group scene = new Group();
        cb = ChessBoard.getInstance();
        
        scene.getChildren().add(cb);
        
        primaryStage.setScene(new Scene(scene, 550, 550));
        primaryStage.setResizable(false);
        primaryStage.show();
        gm = GameManager.getInstance();
    }
}
