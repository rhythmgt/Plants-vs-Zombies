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
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
		Parent root = (Parent)loader.load();
		((homeController)loader.getController()).setLevel(1);
		((Node)mouseEvent.getSource()).getScene().setRoot(root);
	}
	public void startLevel2(MouseEvent mouseEvent) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
		Parent root = (Parent)loader.load();
		((homeController)loader.getController()).setLevel(2);
		((Node)mouseEvent.getSource()).getScene().setRoot(root);
	}

	public void startLevel3(MouseEvent mouseEvent) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
		Parent root = (Parent)loader.load();
		((homeController)loader.getController()).setLevel(3);
		((Node)mouseEvent.getSource()).getScene().setRoot(root);
	}

	public void startLevel4(MouseEvent mouseEvent) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
		Parent root = (Parent)loader.load();
		((homeController)loader.getController()).setLevel(4);
		((Node)mouseEvent.getSource()).getScene().setRoot(root);
	}
}
