package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.text.html.ImageView;
import java.io.IOException;


public class level1Controller {


	@FXML
	private Button PauseBtn;


	@FXML
	private ImageView landMover;
	@FXML
	public void pauseGame(MouseEvent mouseEvent) throws IOException {
		Stage primaryStage2 = new Stage();

		PauseBtn.setDisable(true);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));
		Parent root2 = loader.load();

		primaryStage2.initStyle(StageStyle.UNDECORATED);
		primaryStage2.setScene(new Scene(root2, 300, 300));


		((menuController) loader.getController()).setCallingLevel((Stage) ((Node)mouseEvent.getSource()).getScene().getWindow());
		((menuController) loader.getController()).setCallingButton(PauseBtn);
		primaryStage2.show();
	}

	@FXML
	public void pauseGame2(MouseEvent mouseEvent) throws IOException {

	}
}
