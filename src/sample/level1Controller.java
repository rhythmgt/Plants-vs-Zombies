package sample;

import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import javax.swing.text.html.ImageView;
import java.io.IOException;


public class level1Controller {

	@FXML

	public javafx.scene.image.ImageView LandMover;

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
}
