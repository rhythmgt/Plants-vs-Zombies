package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class homeController {
	public void continueGame(ActionEvent actionEvent) throws IOException {
		Parent root2 = FXMLLoader.load(getClass().getResource("selectLevel.fxml"));
		//Stage myStage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
		//myStage.setMaximized(true);
		//myStage.setFullScreen(true);
		//myStage.setScene(new Scene(root2, 300, 300));
		//myStage.show();
		((Node)actionEvent.getSource()).getScene().setRoot(root2);
		root2.getStylesheets().add(getClass().getResource("buttonStyle2.css").toString());
	}
}
