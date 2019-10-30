package sample;

import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class menuController {

	private Stage callingLevel;

	public void setCallingLevel(Stage callingLevel) {
		this.callingLevel = callingLevel;
	}

	public void resumeGame(MouseEvent mouseEvent) {
		((Stage) ((Node)mouseEvent.getSource()).getScene().getWindow()).close();
	}

	public void exitGame(MouseEvent mouseEvent) {

		callingLevel.close();
		((Stage) ((Node)mouseEvent.getSource()).getScene().getWindow()).close();
	}
}
