package sample;

import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.*;
import javafx.scene.layout.Pane;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.awt.*;
import java.io.IOException;


public class level1Controller {

	public javafx.scene.image.ImageView LandMover;
	public AnchorPane menu;
	public AnchorPane myParent;
	public Label sunCount;

	@FXML
	private Button PauseBtn;



	@FXML
	public void pauseGame(MouseEvent mouseEvent) throws IOException {
		/*Stage primaryStage2 = new Stage();

		PauseBtn.setDisable(true);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));
		Parent root2 = loader.load();

		primaryStage2.initStyle(StageStyle.UNDECORATED);
		primaryStage2.setScene(new Scene(root2, 300, 300));


		((menuController) loader.getController()).setCallingLevel((Stage) ((Node)mouseEvent.getSource()).getScene().getWindow());
		((menuController) loader.getController()).setCallingButton(PauseBtn);
		primaryStage2.show();*/
		menu.setVisible(true);
		PauseBtn.setDisable(true);
	}

	@FXML
	public void pauseGame2(MouseEvent mouseEvent) throws IOException, InterruptedException {
		/*while (LandMover.getX() < 800){
		LandMover.setX(LandMover.getX() + 10);
			Thread.sleep(10);

		}*/
		PathElement[] path = {
				new MoveTo(256,65),
				new LineTo(800, 65),
				//new ClosePath(),
		};
		Path road = new Path();
		road.getElements().addAll(path);
		PathTransition anim = new PathTransition();
		anim.setNode(LandMover);
		anim.setPath(road);
		anim.setDuration(new Duration(5000));
		//anim.setCycleCount(1);

		Animation.Status status = anim.getStatus();

		anim.play();
	}


	public void resumeGame(MouseEvent mouseEvent) {
		PauseBtn.setDisable(false);
		menu.setVisible(false);

	}

	public void exitGame(MouseEvent mouseEvent) {


		((Stage) ((Node)mouseEvent.getSource()).getScene().getWindow()).close();
	}

	public void restartLevel(MouseEvent mouseEvent) throws IOException {
		((Node)mouseEvent.getSource()).getScene().setRoot((Parent) FXMLLoader.load(getClass().getResource("level1.fxml")));
	}

	public void addSun(MouseEvent mouseEvent){
		addSun(570);
	}

	public void addSun(double x){
		Button sunToken = new Button();
		sunToken.setOnMouseClicked(event -> {
			changeSunValue(100);
			sunToken.setVisible(false);
		});
		double r=25;
		sunToken.setShape(new Circle(r));
		sunToken.setMinSize(2*r, 2*r);
		sunToken.setMaxSize(2*r, 2*r);
		sunToken.setLayoutX(x);
		sunToken.setLayoutY(80.0);
		sunToken.getStylesheets().add("/sample/sunStyle.css");
		myParent.getChildren().add(sunToken);
		PathElement[] path = {
				new MoveTo(x,80.0),
				new LineTo(x, 300),
		};

		Path road = new Path();
		road.getElements().addAll(path);
		PathTransition tanim = new PathTransition();
		tanim.setNode(sunToken);
		tanim.setPath(road);
		tanim.setDuration(new Duration(10000));
		tanim.play();
	}

	public void changeSunValue(int k){
		int i = Integer.parseInt(sunCount.getText());
		i += k;
		sunCount.setText(Integer.toString(i));
	}
}
