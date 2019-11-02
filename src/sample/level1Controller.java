package sample;

import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class level1Controller extends Application implements Initializable {

	@FXML
	public Button fds;

	@FXML
	public javafx.scene.image.ImageView SunToken;

	@Override
	public void start(Stage primaryStage) {
		try {
			System.out.println("OnStartDetected");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void initialize(URL url, ResourceBundle rb) {
		Courtyard.setVisible(true);
		System.out.println("Initialize");
		SunToken.setVisible(false);
		SunToken.setDisable(true);
		//Enter a gif in preloader in the main javafx application

		fds.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				System.out.print("mouse enterd");
			}
		});
		fds.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				System.out.println("mouse exited");
			}
		});

	}

	public static void main(String[] args) {
		launch(args);
	}

	@FXML
	public javafx.scene.image.ImageView LandMover;

	@FXML
	public javafx.scene.image.ImageView Courtyard;

	@FXML
	private Button PauseBtn;

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
	public void runA(){
		LandMover.setVisible(false);
		LandMover.setDisable(false);
	}


	@FXML
	public void disappear(){
		SunToken.setVisible(false);
		SunToken.setDisable(true);
	}

	@FXML
	public void pauseGame2(MouseEvent mouseEvent) throws IOException, InterruptedException {
		SunToken.setVisible(true);
		SunToken.setDisable(false);
		PathElement[] path = {
				new MoveTo(106,30),
				new LineTo(1000, 30),
		};
		LandMover.setFitHeight(70);
		LandMover.setFitWidth(100);

		Path road = new Path();
		road.getElements().addAll(path);

		PathTransition anim = new PathTransition();
		anim.setNode(LandMover);
		anim.setPath(road);
		anim.setDuration(new Duration(2000));
		anim.setOnFinished(e->runA());
		anim.play();

		PathTransition tanim = new PathTransition();
		tanim.setNode(SunToken);
		tanim.setPath(road);
		tanim.setDuration(new Duration(3000));
		tanim.play();

	}

	@FXML
	public void dragdrop0(DragEvent event) throws IOException, InterruptedException {
		System.out.println("DropDetected");
		event.setDropCompleted(true);
	}

	@FXML
	public void dragdetectedpea(MouseEvent mouseEvent) throws IOException{
		//System.out.println("dragdetectedpea");
		mouseEvent.consume();
		mouseEvent.setDragDetect(true);
	}
}


/*drop.setOnMouseDragOver(new EventHandler<MouseDragEvent>() {
			@Override
			public void handle(MouseDragEvent event) {
				System.out.print("fdfdsfds");
			}
		});
		peashooter.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				event.setDragDetect(true);
				//System.out.println("initialized");
			}
		});
		peashooter.setOnMouseDragExited(new EventHandler<MouseDragEvent>() {
			@Override
			public void handle(MouseDragEvent event) {
				System.out.println("exited");
			}
		});
		peashooter.setOnMouseDragReleased(new EventHandler<MouseDragEvent>() {
			@Override
			public void handle(MouseDragEvent event) {
				System.out.println("DragReleased");
			}
		});*/


