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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.net.URL;
import java.util.*;


public class levelController implements  Initializable {

	public javafx.scene.image.ImageView LandMover;
	public ImageView sunflowerCards;
	public ImageView potatoCards;
	public ImageView cherrybombCards;
	public ImageView wallnutCards;
	public AnchorPane menu;
	public AnchorPane myParent;
	public Label sunCount;
	public ImageView backgroundBoard;
	public ImageView peaCard;
	public double startDragX , startDragY;
	public ImageView peashooter;
	public ImageView wallnut;
	public ImageView cherrybomb;
	public ArrayList<Transition> animation ;
	public ImageView player;
	public ImageView peaball;
	public ImageView zombie;
	public ImageView sunflower;
	public ImageView potato;
	public Timer tZombie;
	public Timer tSun ;
    public ImageView khopdi;
    public ImageView timer;
	public AnchorPane endMenu;
	public AnchorPane winMenu;
	protected Game myGame;
	protected   Courtyard myCourtyard;
	protected int myLevel =1 ;

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
                anim2.setDuration(new Duration(150000));
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
		FXMLLoader loader = new FXMLLoader(getClass().getResource("level1.fxml"));
		Parent root = (Parent) loader.load();
		((levelController) loader.getController()).init(myGame);
		((Node) mouseEvent.getSource()).getScene().setRoot(root);	}


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
	public void mousePressedPotato(MouseEvent mouseEvent) {
		startDragX = mouseEvent.getSceneX();
		startDragY = mouseEvent.getSceneY();
		player= new ImageView();
		player.setImage(potato.getImage());
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
		if (x > 308.0 && x < 1223.0 && y > 98.0 && y < 697.0) {
			if (player.getImage() == peashooter.getImage()) {
				myParent.getChildren().remove(player);
				if(myCourtyard.addPlant(x, y, 2)==true)
					cardEnabler(peaCard , 5000);
			}
			else if (player.getImage() == sunflower.getImage()) {
				myParent.getChildren().remove(player);
				if(myCourtyard.addPlant(x, y, 1)==true)
					cardEnabler(sunflowerCards , 5000);
			}
			else if (player.getImage() == cherrybomb.getImage()) {
				myParent.getChildren().remove(player);
				if(myCourtyard.addPlant(x, y, 4)==true)
					cardEnabler(cherrybombCards , 5000);
			}
			else if (player.getImage() == wallnut.getImage()) {
				myParent.getChildren().remove(player);
				if(myCourtyard.addPlant(x, y, 3)==true)
					cardEnabler(wallnutCards , 5000);
			}
			else {
				myParent.getChildren().remove(player);
				if(myCourtyard.addPlant(x, y, 5)==true)
					cardEnabler(potatoCards , 5000);
			}
		}
		else
			myParent.getChildren().remove(player);
	}

	public void cardEnabler(ImageView im , int x){
		im.setDisable(true);
		ColorAdjust colorAdjust = new ColorAdjust();
		colorAdjust.setBrightness(-0.6);
		im.setEffect(colorAdjust);
		new Timer().schedule(
				new TimerTask() {
					@Override
					public void run() {
						ColorAdjust colorAdjust = new ColorAdjust();
						colorAdjust.setBrightness(0.0);
						im.setEffect(colorAdjust);
						System.out.println("ping");
						im.setDisable(false);
					}
				}, x);
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
	public void mousePressedbomb(MouseEvent mouseEvent) {
		startDragX = mouseEvent.getSceneX();
		startDragY = mouseEvent.getSceneY();
		player= new ImageView();
		player.setImage(cherrybomb.getImage());
		player.setFitWidth(77.0);
		player.setFitHeight(77.0);
		player.setVisible(true);
		myParent.getChildren().add(player);
		player.setLayoutX(mouseEvent.getSceneX()-33.5);
		player.setLayoutY(mouseEvent.getSceneY()-64);

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		/*animation = new ArrayList<>(0);
		myCourtyard = new CourtYard1(myParent, animation, sunCount, winMenu);

		startMeter();*/
	}
	public void backtomenu(MouseEvent mouseEvent) throws IOException {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
			Parent root = (Parent) loader.load();
			((homeController) loader.getController()).setLevel(myLevel,myGame);
			((Node) mouseEvent.getSource()).getScene().setRoot(root);
	}


	public void restore(Game g, Courtyard c) throws IOException, ClassNotFoundException {

		myGame = g;
		myCourtyard = c;
		animation = new ArrayList<>(0);
		myCourtyard.reInitialize(myParent, sunCount, endMenu, animation , winMenu);

	}

	public void save(MouseEvent mouseEvent) throws IOException {

		myGame.addCourtyard(myLevel, myCourtyard);
		myGame.Serialize(mouseEvent);
	}

	public void init(Game g) {
		myGame = g;
		animation = new ArrayList<>(0);
		myCourtyard = new CourtYard1(myParent, animation, sunCount, endMenu , winMenu);
		myGame.addCourtyard(myLevel, myCourtyard);
		startMeter();
	}

	public void nextLevel(MouseEvent mouseEvent) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("level2.fxml"));
		Parent root = (Parent) loader.load();
		((Level2Controller) loader.getController()).init(myGame);
		((Node) mouseEvent.getSource()).getScene().setRoot(root);	}

}
