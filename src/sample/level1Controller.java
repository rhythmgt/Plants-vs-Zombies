package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class level1Controller {
	public void pauseGame(MouseEvent mouseEvent) throws IOException {
		Stage primaryStage2 = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));
		Parent root2 = loader.load();

		primaryStage2.initStyle(StageStyle.UNDECORATED);
		primaryStage2.setScene(new Scene(root2, 300, 300));

		((menuController) loader.getController()).setCallingLevel((Stage) ((Node)mouseEvent.getSource()).getScene().getWindow());
		primaryStage2.show();
	}
}
