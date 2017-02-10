package chessgame;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;

public class UtilityBar extends ToolBar {

	public UtilityBar() {
		this.getItems().add(new SaveButton());
		this.getItems().add(new Button("Load"));
		this.getItems().add(new Button("Save"));
		this.getItems().add(new Label("Round"));
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
