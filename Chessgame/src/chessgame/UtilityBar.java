package chessgame;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;

public class UtilityBar extends ToolBar {
	static Label console;
	
	public UtilityBar() {
		this.getItems().add(new NewGameButton());
		this.getItems().add(new Button("Load"));
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

class SaveButton extends Button {
	
	public SaveButton() {
		setText("Save");
		setOnAction(new EventHandler<ActionEvent>() {
		    @Override 
		    public void handle(ActionEvent e) {
		        if (new Save().SaveData(GameManager.getInstance().getState())) {
		        	UtilityBar.updateConsole("Game saved!");
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
	
	public void refresh() {
		setText("Round: " + GameManager.getRound());
	}
}
