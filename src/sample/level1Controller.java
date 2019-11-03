package sample;

import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class level1Controller {

	public javafx.scene.image.ImageView LandMover;
	public AnchorPane menu;
	public AnchorPane myParent;
	public Label sunCount;
	public ImageView backgroundBoard;
	public ImageView peaCard;
	public double startDragX , startDragY;
	public ImageView peashooter;

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

	public void mousePressed(MouseEvent mouseEvent) {
		startDragX = mouseEvent.getSceneX();
		startDragY = mouseEvent.getSceneY();
		System.out.println(mouseEvent.getSceneX() + " " + mouseEvent.getSceneY());}

	public void mouseReleased(MouseEvent mouseEvent) throws InterruptedException {
		MouseEvent e = mouseEvent ;
		ImageView player = new ImageView();
		player.setImage(peashooter.getImage());
		player.setFitWidth(77.0);
		player.setFitHeight(77.0);
		player.setLayoutX(971.0);
		player.setLayoutY(13.0);
		player.setVisible(true);
		myParent.getChildren().add(player);
		player.setTranslateX(e.getSceneX() - startDragX);
		player.setTranslateY(e.getSceneY() - startDragY);
		System.out.println("here "+e.getSceneX() + " " + e.getSceneY());
		if (e.getSceneX() > 232 && e.getSceneX() < 232 + 65 && e.getSceneY() > 20 && e.getSceneY() < 20 + 100) {
			player.setTranslateX(275 - startDragX);
			player.setTranslateY(60 - startDragY);
			System.out.println(e.getSceneX() + " " + e.getSceneY());
		} else if (e.getSceneX() > 232 + 65 && e.getSceneX() < 232 + 65 + 65 && e.getSceneY() > 20 && e.getSceneY() < 20 + 100) {
			player.setTranslateX(275 + 65 - startDragX);
			player.setTranslateY(60 - startDragY);
			System.out.println(e.getSceneX() + " " + e.getSceneY());
		} else if (e.getSceneX() > 232 + 65 + 65 && e.getSceneX() < 232 + 65 + 65 + 65 && e.getSceneY() > 20 && e.getSceneY() < 20 + 100) {
			player.setTranslateX(275 + 65 + 65 - startDragX);
			player.setTranslateY(60 - startDragY);
			player.setOnMousePressed(null);
			player.setOnMouseDragged(null);
			System.out.println(e.getSceneX() + " " + e.getSceneY());
		} else if (e.getSceneX() > 232 + 65 + 65 + 65 && e.getSceneX() < 232 + 65 + 65 + 65 + 65 && e.getSceneY() > 20 && e.getSceneY() < 20 + 100) {
			player.setTranslateX(275 + 65 + 65 + 65 - startDragX);
			player.setTranslateY(60 - startDragY);
			System.out.println(e.getSceneX() + " " + e.getSceneY());
		} else if (e.getSceneX() > 240 + 65 + 65 + 65 + 65 && e.getSceneX() < 240 + 65 + 65 + 65 + 65 + 65 && e.getSceneY() > 20 && e.getSceneY() < 20 + 100) {
			player.setTranslateX(283 + 65 + 65 + 65 + 65 - startDragX);
			player.setTranslateY(60 - startDragY);
			System.out.println(e.getSceneX() + " " + e.getSceneY());
		} else if (e.getSceneX() > 240 + 65 + 65 + 65 + 65 + 65 && e.getSceneX() < 240 + 65 + 65 + 65 + 65 + 65 + 65 && e.getSceneY() > 20 && e.getSceneY() < 20 + 100) {
			player.setTranslateX(275 + 65 + 65 + 65 + 65 + 65 - startDragX);
			player.setTranslateY(60 - startDragY);
			System.out.println(e.getSceneX() + " " + e.getSceneY());
		} else if (e.getSceneX() > 232 + 65 + 65 + 65 + 65 + 65 + 65 && e.getSceneX() < 232 + 65 + 65 + 65 + 65 + 65 + 65 + 65 && e.getSceneY() > 20 && e.getSceneY() < 20 + 100) {
			player.setTranslateX(275 + 65 + 65 + 65 + 65 + 65 + 65 - startDragX);
			player.setTranslateY(60 - startDragY);
			System.out.println(e.getSceneX() + " " + e.getSceneY());
		} else if (e.getSceneX() > 232 + 65 + 65 + 65 + 65 + 65 + 65 + 65 && e.getSceneX() < 232 + 65 + 65 + 65 + 65 + 65 + 65 + 65 + 65 && e.getSceneY() > 20 && e.getSceneY() < 20 + 100) {
			player.setTranslateX(275 + 65 + 65 + 65 + 65 + 65 + 65 + 65 - startDragX);
			player.setTranslateY(60 - startDragY);
			System.out.println(e.getSceneX() + " " + e.getSceneY());
		} else if (e.getSceneX() > 232 + 65 + 65 + 65 + 65 + 65 + 65 + 65 + 65 && e.getSceneX() < 232 + 65 + 65 + 65 + 65 + 65 + 65 + 65 + 65 + 65 && e.getSceneY() > 20 && e.getSceneY() < 20 + 100) {
			player.setTranslateX(275 + 65 + 65 + 65 + 65 + 65 + 65 + 65 + 65 - startDragX);
			player.setTranslateY(60 - startDragY);
			System.out.println(e.getSceneX() + " " + e.getSceneY());
		}
		player.setOnMousePressed(null);
		player.setOnMouseReleased(null);
	}
}
