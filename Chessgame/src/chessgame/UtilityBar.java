package chessgame;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class UtilityBar extends ToolBar {
	static Label console;
	
	public UtilityBar() {
		this.getItems().add(new NewGameButton());
		this.getItems().add(new LoadButton());
		this.getItems().add(new SaveButton());
		this.getItems().add(new Separator());
		this.getItems().add(RoundCounter.getInstance());
		console = new Label();
		this.getItems().add(console);
	}
	
	public static void updateConsole(String text) {
		console.setText(text);
	}
}

class NewGameButton extends Button {
	
	public NewGameButton() {
		setText("New");
		setOnAction(new EventHandler<ActionEvent>() {
		    @Override 
		    public void handle(ActionEvent e) {
		        GameManager.getInstance().defaultStart();
		    }
		});
	}
}

class LoadButton extends Button {
	
	public LoadButton() {
		setText("Load");
		setOnAction(new EventHandler<ActionEvent>() {
		    @Override 
		    public void handle(ActionEvent e) {
		    	// Create new file chooser pop up
		    	FileChooser fc = new FileChooser();
		    	// Open it in default directory (sav)
		    	fc.setInitialDirectory(new File("sav"));
                // Pop dialog
		    	File file = fc.showOpenDialog(new Stage());
                
                // If a file is selected, load file
                if (file != null) {
			    	GameState data = new Save().LoadData(file);
			        if (data != null) {
			        	GameManager.getInstance().loadState(data);
					}
                }
		    }
		});
	}
}

class SaveButton extends Button {
	
	public SaveButton() {
		setText("Save");
		setOnAction(new EventHandler<ActionEvent>() {
		    @Override 
		    public void handle(ActionEvent e) {
		    	GameState save = GameManager.getInstance().getState();
		    	
		    	// Create new file chooser pop up
		    	FileChooser fc = new FileChooser();
		    	// Open it in default directory (sav)
		    	fc.setInitialDirectory(new File("sav"));
		    	// Set default name
		    	fc.setInitialFileName(save.getPlayer(0) + " vs " + save.getPlayer(1) + ".sav");
		    	// Create new filter for custom save files
	         	FileChooser.ExtensionFilter savExtFilter = new FileChooser.ExtensionFilter("Save files (*.sav)", "*.sav");
	         	// Apply filter
	         	fc.getExtensionFilters().add(savExtFilter);
		    	// Pop dialog
                File file = fc.showSaveDialog(new Stage());
		    	
                // If file is selected, save file
                if (file != null) {
    		        if (new Save().SaveData(file, save)) {
    		        	UtilityBar.updateConsole("Game saved!");
    				} else {
    					UtilityBar.updateConsole("Error occured during saving!");
    				}
                }
		    }
		});
	}
}

class RoundCounter extends Label {
	
	private static RoundCounter controller;
	
	private RoundCounter() {
		this.setText("Round: 1");
	}
	
	public static RoundCounter getInstance() {
		if (RoundCounter.controller == null) {
			controller = new RoundCounter();
		}
		return controller;
	}
	
	public void refresh(int round) {
		setText("Round: " + round);
	}
}
