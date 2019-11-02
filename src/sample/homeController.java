package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class homeController implements Initializable {

	@FXML
	public Button continueButton ;

	public void continueGame(ActionEvent actionEvent) throws IOException {
		Parent root2 = FXMLLoader.load(getClass().getResource("selectLevel.fxml"));
		//Stage myStage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
		//myStage.setMaximized(true);
		//myStage.setFullScreen(true);
		//myStage.setScene(new Scene(root2, 300, 300));
		//myStage.show();
		((Node)actionEvent.getSource()).getScene().setRoot(root2);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
}
