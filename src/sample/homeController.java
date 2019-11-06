package sample;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import PlantZombie.Game;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class homeController implements Initializable {
	public Label userName;
	public ComboBox savedGames;
	public AnchorPane myPane;
	private Game mygame;

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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		mygame = new Game();
		userName.setText(mygame.getName());
	}

	public void changeUserName(MouseEvent mouseEvent) {
		Label label1 = new Label("UserName:");
		TextField textField = new TextField ();
		HBox hb = new HBox();
		hb.getChildren().addAll(label1, textField);
		hb.setSpacing(10);
		Button btn = new Button("Submit");

		VBox box0 = new VBox();
		box0.getChildren().addAll(hb, btn);
		AnchorPane newPane = new AnchorPane(box0);
		newPane.setStyle("-fx-background-color: white");
		myPane.getChildren().add(newPane);
		newPane.setVisible(true);

		System.out.println("Hello");
		btn.setOnMouseClicked(event1 -> {
			String name = textField.getText();
			mygame.setName(name);
			userName.setText(name);
			myPane.getChildren().remove(newPane);
		});
	}
}
