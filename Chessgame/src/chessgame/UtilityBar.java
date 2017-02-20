package chessgame;

import java.io.File;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

public class UtilityBar extends ToolBar {
	static Label console;
	
	public UtilityBar() {
		// Create a pane to align content to the right
		Pane alignRight = new Pane();
		HBox.setHgrow(alignRight, Priority.ALWAYS);
		
		// Console to display relevant text
		console = new Label();
		
		//ObservableList<Button> buttons;
		//buttons.add(new NewGameButton());
		//buttons.add(new LoadButton());
		//buttons.add(new SaveButton());
		
		MenuButton menuButton = new MenuButton("Game");
		menuButton.getItems().addAll(new NewGameButton(), new SaveButton(), new LoadButton());
		
		// Add and show content of utility bar
		this.getItems().addAll(
				RoundCounter.getInstance(),
				new Separator(),
				new Label(),
				console,
				alignRight,
				menuButton
				);
	}
	
	public static Window getWindow() {
		return console.getScene().getWindow();
	}
	
	// Change text in console
	public static void updateConsole(String text) {
		console.setText(text);
	}
}

class NewGameButton extends MenuItem {
	
	public NewGameButton() {
		setText("New");
		setOnAction(new EventHandler<ActionEvent>() {
		    @Override 
		    public void handle(ActionEvent e) {
		    	// Create or get the instance of GameManager
	    		GameManager gm = GameManager.getInstance();
		    	
	    		// Pop up an alert if there is already a game going
	    		if (gm.getGame() > 0) {
			    	// Create new dialog box to confirm if a new game is wanted
		    		Alert newGameAlert = new Alert(AlertType.CONFIRMATION);
		    		newGameAlert.setTitle("Are you sure?");
		    		newGameAlert.setContentText("Are you sure you want to start a new game? All unsaved progress will be lost.");
		    		newGameAlert.setHeaderText(null);
		    		
		    		Optional<ButtonType> input = newGameAlert.showAndWait();
		    		if (input.get() == ButtonType.OK)
		    			GameManager.getInstance().newGame();
	    		} else {
	    			GameManager.getInstance().newGame();
	    		}
		    }
		});
	}
}

class LoadButton extends MenuItem {
	
	public LoadButton() {
		setText("Load");
		setOnAction(new EventHandler<ActionEvent>() {
		    @Override 
		    public void handle(ActionEvent e) {
		    	// Create or get the instance of GameManager
	    		GameManager gm = GameManager.getInstance();
		    	
	    		// Pop up an alert if there is already a game going
	    		if (gm.getGame() > 0) {
			    	// Create new dialog box to confirm if a new game is wanted
		    		Alert newGameAlert = new Alert(AlertType.CONFIRMATION);
		    		newGameAlert.setTitle("Are you sure?");
		    		newGameAlert.setContentText("Are you sure you want to load another game? All unsaved progress will be lost.");
		    		newGameAlert.setHeaderText(null);
		    		
		    		Optional<ButtonType> input = newGameAlert.showAndWait();
		    		if (input.get() == ButtonType.CANCEL || input.get() == ButtonType.CLOSE)
		    			return;
	    		}
	    		
		    	// Create new file chooser pop up
		    	FileChooser fc = new FileChooser();
		    	// Open it in default directory (sav)
		    	fc.setInitialDirectory(new File("sav"));
		    	// Get current stage so that the chess board is locked until pop up is closed
		    	Stage stage = (Stage) UtilityBar.getWindow();
		    	// Pop dialog
		    	File file = fc.showOpenDialog(stage);
                
                // If a file is selected, load file
                if (file != null) {
			    	GameState data = new Save().LoadData(file);
			        if (data != null) {
			        	gm.loadGame(data);
					} else {
						UtilityBar.updateConsole("Unable to read data!");
					}
                }
		    }
		});
	}
}

class SaveButton extends MenuItem {
	
	public SaveButton() {
		setText("Save");
		setOnAction(new EventHandler<ActionEvent>() {
		    @Override 
		    public void handle(ActionEvent e) {
		    	GameState save = GameManager.getInstance().getState();
		    	
		    	if (save == null) {
		    		UtilityBar.updateConsole("No game state!");
		    		return;
		    	}
		    	
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
	         	// Get current stage so that the chess board is locked until pop up is closed
		    	Stage stage = (Stage) UtilityBar.getWindow();
		    	// Pop dialog
                File file = fc.showSaveDialog(stage);
		    	
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
