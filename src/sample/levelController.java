package sample;

import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.animation.Transition;
import javafx.application.Platform;
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
import java.util.*;


public class levelController implements  Initializable {

	public javafx.scene.image.ImageView LandMover;
	public AnchorPane menu;
	public AnchorPane myParent;
	public Label sunCount;
	public ImageView backgroundBoard;
	public ImageView peaCard;
	public double startDragX , startDragY;
	public ImageView peashooter;
	public ImageView wallnut;
	public ArrayList<Transition> animation ;
	public ImageView player;
	public ImageView peaball;
	public ImageView zombie;
	public ImageView sunflower;
	public Timer tZombie;
	public Timer tSun ;
    public ImageView khopdi;
    public ImageView timer;

	protected   Courtyard myCourtyard;

	public void startMeter(){

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                double xnot = khopdi.getX();
                double ynot = khopdi.getY();
                PathElement[] path = {
                        new MoveTo(xnot,ynot+10),
                        new LineTo(xnot-180, ynot+10),
                };

                Path road = new Path();
                road.getElements().addAll(path);
                PathTransition anim2 = new PathTransition();
                anim2.setNode(khopdi);
                anim2.setPath(road);
                anim2.setDuration(new Duration(60000));
                animation.add(anim2);
                anim2.play();
            }
        });


    }

    @FXML
	protected Button PauseBtn;
	@FXML
	public void pauseGame(MouseEvent mouseEvent) throws IOException {
		pauser(mouseEvent);
		menu.setVisible(true);
		menu.toFront();
		PauseBtn.setDisable(true);
	}


	public void resumeGame(MouseEvent mouseEvent) {
		pauser(mouseEvent);
		PauseBtn.setDisable(false);
		menu.setVisible(false);
	}

	public void exitGame(MouseEvent mouseEvent) {
		((Stage) ((Node)mouseEvent.getSource()).getScene().getWindow()).close();
	}

	public void restartLevel(MouseEvent mouseEvent) throws IOException {
		((Node)mouseEvent.getSource()).getScene().setRoot((Parent) FXMLLoader.load(getClass().getResource("level1.fxml")));
	}


	public void mousePressedNut(MouseEvent mouseEvent) {
		startDragX = mouseEvent.getSceneX();
		startDragY = mouseEvent.getSceneY();
		player= new ImageView();
		player.setImage(wallnut.getImage());
		player.setFitWidth(77.0);
		player.setFitHeight(77.0);
		player.setVisible(true);
		myParent.getChildren().add(player);
		player.setLayoutX(mouseEvent.getSceneX()-38.5);
		player.setLayoutY(mouseEvent.getSceneY()-77);
	}

	public void mousePressedpea(MouseEvent mouseEvent) {
		startDragX = mouseEvent.getSceneX();
		startDragY = mouseEvent.getSceneY();
		player= new ImageView();
		player.setImage(peashooter.getImage());
		player.setFitWidth(77.0);
		player.setFitHeight(77.0);
		player.setVisible(true);
		myParent.getChildren().add(player);
		player.setLayoutX(mouseEvent.getSceneX()-38.5);
		player.setLayoutY(mouseEvent.getSceneY()-77);
	}

	public void mouseReleased(MouseEvent mouseEvent) throws InterruptedException {
		double x = mouseEvent.getSceneX();
		double y = mouseEvent.getSceneY();
		myParent.getChildren().remove(player);
		//double x2 = getPlantingPosition(x);
		if(x>308.0 && x<1223.0 && y>98.0 && y<697.0) {
			if(player.getImage()==sunflower.getImage()){
				//sunflowerSun(x,y);
				myParent.getChildren().remove(player);
				//Plant sunFlower = new SunFlower(x2,330, myParent);
				//myPlants.add(sunFlower);
				myCourtyard.addPlant(x,y,1);
			}
			else if (player.getImage()==peashooter.getImage()){
				//x = mouseEvent.getSceneX() + 38.5;
				myParent.getChildren().remove(player);
				//PeaShooter PS = new PeaShooter(x2,330, myParent, myzombies,animation);
				//myPlants.add(PS);
				myCourtyard.addPlant(x,y,2);
			}
			else if(player.getImage() == wallnut.getImage()){
				myParent.getChildren().remove(player);
				myCourtyard.addPlant(x,y,3);
			}
		}
		else
			myParent.getChildren().remove(player);
	}

	public void dragging(MouseEvent mouseEvent) {
		MouseEvent e = mouseEvent ;
		player.setLayoutX(e.getSceneX()-38.5);
		player.setLayoutY(e.getSceneY()-77);
		//System.out.println(e.getSceneX()+" " + e.getSceneY());
	}


	public int flag = 0;
	public void pauser(MouseEvent mouseEvent) {
		if(flag==0){
			for(int i=0 ; i<animation.size() ; i++)
				if(animation.get(i).getStatus() == Animation.Status.RUNNING) {
					animation.get(i).pause();
				}
			flag+=1;
//			tZombie.cancel();
//			tSun.cancel();
		}
		else{
			flag=0;
			for(int i=0 ; i<animation.size() ; i++)
				if(animation.get(i).getStatus() == Animation.Status.PAUSED) {
					animation.get(i).play();
				}
//			funzombie();
//			funsun();
		}
	}

	public void mousePressedsunflower(MouseEvent mouseEvent) {
		startDragX = mouseEvent.getSceneX();
		startDragY = mouseEvent.getSceneY();
		player= new ImageView();
		player.setImage(sunflower.getImage());
		player.setFitWidth(77.0);
		player.setFitHeight(77.0);
		player.setVisible(true);
		myParent.getChildren().add(player);
		player.setLayoutX(mouseEvent.getSceneX()-33.5);
		player.setLayoutY(mouseEvent.getSceneY()-64);

	}
	protected void endZombie(ImageView zom) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				zom.setVisible(false);
				myParent.getChildren().remove(zom);
				//moveLawnmover();
			}
		});
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		animation = new ArrayList<>(0);
		myCourtyard = new CourtYard1(myParent, animation, sunCount);

		startMeter();
	}
}
