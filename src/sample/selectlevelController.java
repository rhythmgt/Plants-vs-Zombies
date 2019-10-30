package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class selectlevelController {
	public void startLevel1(MouseEvent mouseEvent) throws IOException {
		Parent root2 = FXMLLoader.load(getClass().getResource("level1.fxml"));
		/*Stage myStage = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
		myStage.setMaximized(true);
		myStage.setScene(new Scene(root2, 100, 100));
		myStage.show();*/
		((Node)mouseEvent.getSource()).getScene().setRoot(root2);
	}
}
