package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class homeController {
	private int Level = 1;

	public void setLevel(int level) {
		Level = level;
	}


	public void continueGame(ActionEvent actionEvent) throws IOException, InterruptedException {

		if (Level==1){
			FXMLLoader loader = new FXMLLoader(getClass().getResource("level1.fxml"));
			Parent root = (Parent)loader.load();
			//((level1Controller)loader.getController()).start();
			((Node)actionEvent.getSource()).getScene().setRoot(root);
		}
		else{
			Parent root2 = FXMLLoader.load(getClass().getResource("level3.fxml"));
			((Node)actionEvent.getSource()).getScene().setRoot(root2);
		}

	}

	public void selectLevel(MouseEvent mouseEvent) throws IOException {
		Parent root2 = FXMLLoader.load(getClass().getResource("selectLevel.fxml"));
		((Node)mouseEvent.getSource()).getScene().setRoot(root2);
		root2.getStylesheets().add(getClass().getResource("buttonStyle2.css").toString());
	}

	public void quitGame(MouseEvent mouseEvent) {
		System.exit(0);
	}
}
