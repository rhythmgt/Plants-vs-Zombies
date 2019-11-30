package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class selectlevelController
{
	Game myGame;

	void setGame(Game g){
		myGame = g;
	}
	public void startLevel1(MouseEvent mouseEvent) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
		Parent root = (Parent)loader.load();
		((homeController)loader.getController()).setLevel(1,myGame);

		((Node)mouseEvent.getSource()).getScene().setRoot(root);
	}
	public void startLevel2(MouseEvent mouseEvent) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
		Parent root = (Parent)loader.load();
		((homeController)loader.getController()).setLevel(2,myGame);
		((Node)mouseEvent.getSource()).getScene().setRoot(root);
	}

	public void startLevel3(MouseEvent mouseEvent) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
		Parent root = (Parent)loader.load();
		((homeController)loader.getController()).setLevel(3,myGame);
		((Node)mouseEvent.getSource()).getScene().setRoot(root);
	}

	public void startLevel4(MouseEvent mouseEvent) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
		Parent root = (Parent)loader.load();
		((homeController)loader.getController()).setLevel(4,myGame);
		((Node)mouseEvent.getSource()).getScene().setRoot(root);
	}

	public void startLevel5(MouseEvent mouseEvent) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
		Parent root = (Parent)loader.load();
		((homeController)loader.getController()).setLevel(5,myGame);

		((Node)mouseEvent.getSource()).getScene().setRoot(root);
	}
}
