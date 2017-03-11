package chessgame;

import java.io.File;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ChessGame extends Application {

	ChessBoard cb;
	GameManager gm;
	
    public static void main(String[] args) {
        Application.launch(args);
    }
    
    public void start(Stage primaryStage) {
    	File dir = new File("sav");
    	if (!dir.exists()) {
    		dir.mkdir();
    	}
    	
        // ------------------- SCENE SETUP ------------------- //
    	
    	primaryStage.setTitle("Shakkipeli");
        
        VBox scene = new VBox();
        cb = ChessBoard.getInstance();
        UtilityBar ub = new UtilityBar();
        
        StackPane boardPane = new StackPane(cb);
        boardPane.setAlignment(Pos.CENTER);
        
        scene.getChildren().add(ub);
        scene.getChildren().add(boardPane);
        
        // -------------------------------------------------- //
        
        primaryStage.setScene(new Scene(scene, Chess.SQUARESIZE * Chess.BOARDSIZE, ub.getMinHeight() + Chess.SQUARESIZE * Chess.BOARDSIZE));
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
        primaryStage.show();
    }
}
