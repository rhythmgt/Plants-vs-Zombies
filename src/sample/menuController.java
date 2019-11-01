package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class menuController {

	private Stage callingLevel;
	private Button callingButton;

	public void setCallingLevel(Stage callingLevel) {
		this.callingLevel = callingLevel;
	}

	public void setCallingButton(Button callingButton) {
		this.callingButton = callingButton;
	}

	public void resumeGame(MouseEvent mouseEvent) {
		callingButton.setDisable(false);
		((Stage) ((Node)mouseEvent.getSource()).getScene().getWindow()).close();
	}

	public void exitGame(MouseEvent mouseEvent) {

		callingLevel.close();
		((Stage) ((Node)mouseEvent.getSource()).getScene().getWindow()).close();
	}

	public void restartLevel(MouseEvent mouseEvent) throws IOException {
		callingLevel.getScene().setRoot((Parent) FXMLLoader.load(getClass().getResource("level1.fxml")));
		((Stage) ((Node)mouseEvent.getSource()).getScene().getWindow()).close();
	}
}
