package chessgame;

import java.io.File;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ChessGame extends Application {

	ChessBoard cb;
	GameManager gm;
	
    public static void main(String[] args) {
        Application.launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
    	File dir = new File("sav");
    	if (!dir.exists()) {
    		dir.mkdir();
    	}
    	
    	primaryStage.setTitle("Shakkipeli");
        
        VBox scene = new VBox();
        cb = ChessBoard.getInstance();
        UtilityBar ub = new UtilityBar();
        
        scene.getChildren().add(ub);
        scene.getChildren().add(cb);
        
        primaryStage.setScene(new Scene(scene, 550, 585));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
