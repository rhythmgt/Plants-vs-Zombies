package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import sample.Game;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class homeController  {
	public Label userName;
	public ComboBox savedGames;
	public AnchorPane myPane;
	private Game mygame;

	public void setMygame(Game mygame) {
		this.mygame = mygame;
	}

	private int Level = 1;

	public void setLevel(int level, Game g) {
		Level = level;mygame = g;
		userName.setText(g.getName());
	}


	public void continueGame(MouseEvent actionEvent) throws IOException, InterruptedException, ClassNotFoundException {
		if (mygame==null){
			mygame = new Game();
			userName.setText(mygame.getName());
		}
		if (Level==1){
			Courtyard cy = mygame.getCourtyard(1);
			if (cy!=null){
			FXMLLoader loader = new FXMLLoader(getClass().getResource("level1.fxml"));
			Parent root = (Parent)loader.load();
			((levelController)loader.getController()).restore(mygame, cy);
			((Node)actionEvent.getSource()).getScene().setRoot(root);}
			else{
				newGame(actionEvent);
			}
		}
		else if (Level==2){
			Courtyard cy = mygame.getCourtyard(2);
			if (cy!=null){
				FXMLLoader loader = new FXMLLoader(getClass().getResource("level2.fxml"));
				Parent root = (Parent)loader.load();
				((levelController)loader.getController()).restore(mygame, cy);
				((Node)actionEvent.getSource()).getScene().setRoot(root);}
			else{
				newGame(actionEvent);
			}
		}
		else if (Level == 3){
			Courtyard cy = mygame.getCourtyard(3);
			if (cy!=null){
				FXMLLoader loader = new FXMLLoader(getClass().getResource("level3.fxml"));
				Parent root = (Parent)loader.load();
				((levelController)loader.getController()).restore(mygame, cy);
				((Node)actionEvent.getSource()).getScene().setRoot(root);}
			else{
				newGame(actionEvent);
			}
		}
		else if (Level==4){Courtyard cy = mygame.getCourtyard(4);
			if (cy!=null){
				FXMLLoader loader = new FXMLLoader(getClass().getResource("level4.fxml"));
				Parent root = (Parent)loader.load();
				((levelController)loader.getController()).restore(mygame, cy);
				((Node)actionEvent.getSource()).getScene().setRoot(root);}
			else{
				newGame(actionEvent);
			}
		}
		else{
			Courtyard cy = mygame.getCourtyard(5);
			if (cy!=null){
				FXMLLoader loader = new FXMLLoader(getClass().getResource("level5.fxml"));
				Parent root = (Parent)loader.load();
				((levelController)loader.getController()).restore(mygame, cy);
				((Node)actionEvent.getSource()).getScene().setRoot(root);}
			else{
				newGame(actionEvent);
			}
		}

	}

	public void selectLevel(MouseEvent mouseEvent) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("selectLevel.fxml"));
		Parent root = (Parent) loader.load();
		((selectlevelController) loader.getController()).setGame(mygame);
		((Node) mouseEvent.getSource()).getScene().setRoot(root);
		root.getStylesheets().add(getClass().getResource("buttonStyle2.css").toString());
	}

	public void quitGame(MouseEvent mouseEvent) {
		System.exit(0);
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

	public void newGame(MouseEvent mouseEvent) throws IOException {
		if (mygame==null){
			mygame = new Game();
			userName.setText(mygame.getName());
		}
		if (Level == 1) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("level1.fxml"));
			Parent root = (Parent) loader.load();
			((levelController) loader.getController()).init(mygame);
			((Node) mouseEvent.getSource()).getScene().setRoot(root);
		} else if (Level == 2) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("level2.fxml"));
			Parent root = (Parent) loader.load();
			((Level2Controller) loader.getController()).init(mygame);
			((Node) mouseEvent.getSource()).getScene().setRoot(root);
		} else if (Level == 3) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("level3.fxml"));
			Parent root = (Parent) loader.load();
			((Level3Controller) loader.getController()).init(mygame);
			((Node) mouseEvent.getSource()).getScene().setRoot(root);
		}else if (Level == 4) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("level4.fxml"));
			Parent root = (Parent) loader.load();
			((Level4Controller) loader.getController()).init(mygame);
			((Node) mouseEvent.getSource()).getScene().setRoot(root);
		}else if (Level == 5) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("level5.fxml"));
			Parent root = (Parent) loader.load();
			((Level5Controller) loader.getController()).init(mygame);
			((Node) mouseEvent.getSource()).getScene().setRoot(root);
		}
	}



	public void loadUser(MouseEvent mouseEvent) {

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
				try {
					mygame = GameSaver.restore(name);
					userName.setText(mygame.getName());
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				myPane.getChildren().remove(newPane);
			});

	}

	public void newUser(MouseEvent mouseEvent) {
		mygame = new Game();
		userName.setText(mygame.getName());
	}
}
