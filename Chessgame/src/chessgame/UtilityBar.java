package chessgame;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;

public class UtilityBar extends ToolBar {

	public UtilityBar() {
		this.getItems().add(new SaveButton());
		this.getItems().add(new Button("Load"));
		this.getItems().add(new Button("Save"));
		this.getItems().add(new Separator());
		this.getItems().add(RoundCounter.getInstance());
	}
	
}

class SaveButton extends Button {
	
	public SaveButton() {
		setText("New");
		setOnAction(new EventHandler<ActionEvent>() {
		    @Override 
		    public void handle(ActionEvent e) {
		        GameManager.getInstance().defaultStart();
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
