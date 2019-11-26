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


public class level1Controller implements  Initializable {

	public javafx.scene.image.ImageView LandMover;
	public AnchorPane menu;
	public AnchorPane myParent;
	public Label sunCount;
	public ImageView backgroundBoard;
	public ImageView peaCard;
	public double startDragX , startDragY;
	public ImageView peashooter;
	public ArrayList<Transition> animation ;
	public ImageView player;
	public ImageView peaball;
	public ImageView zombie;
	public ImageView sunflower;
	public Timer tZombie;
	public Timer tSun ;
    public ImageView khopdi;
    public ImageView timer;
	private ArrayList<ImageView> myZombies;
	private ArrayList<Plant> myPlants;
	private ArrayList<Zombie> myzombies;
	public ArrayList<ImageView> getMyZombies() {
		return myZombies;
	}

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
	private Button PauseBtn;
	@FXML
	public void pauseGame(MouseEvent mouseEvent) throws IOException {
		pauser(mouseEvent);
		menu.setVisible(true);
		menu.toFront();
		PauseBtn.setDisable(true);
	}

	@FXML
	public void pauseGame2(MouseEvent mouseEvent) throws IOException, InterruptedException {
		PathElement[] path = {
				new MoveTo(256,65),
				new LineTo(800, 65),
		};
		Path road = new Path();
		road.getElements().addAll(path);
		PathTransition anim = new PathTransition();
		anim.setNode(LandMover);
		anim.setPath(road);
		anim.setDuration(new Duration(5000));
		anim.play();
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

	public void addSun(MouseEvent mouseEvent){
		addSun(570);
	}

	public void addSun(double x){
		Button sunToken = new Button();
		sunToken.setOnMouseClicked(event -> {
			changeSunValue(100);
			sunToken.setVisible(false);
			myParent.getChildren().remove(sunToken);
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
		if(x>326.0 && x<1238.0 && y>360.0 && y<463.0) {
			if(player.getImage()==sunflower.getImage()){
				sunflowerSun(x,y);
				myParent.getChildren().remove(player);
				Plant sunFlower = new SunFlower(x,330, myParent);
				myPlants.add(sunFlower);
			}
			else {
				x = mouseEvent.getSceneX() + 38.5;
				myParent.getChildren().remove(player);
				PeaShooter PS = new PeaShooter(x,330, myParent, myzombies,animation);
				myPlants.add(PS);
			}
		}
		else
			myParent.getChildren().remove(player);
	}

	private void sunflowerSun(double x , double y) {
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				Button sunToken = new Button();
				sunToken.setOnMouseClicked(event -> {
					changeSunValue(100);
					sunToken.setVisible(false);
					myParent.getChildren().remove(sunToken);
				});
				Random rand = new Random();
				double r=25;
				sunToken.setShape(new Circle(r));
				sunToken.setMinSize(2*r, 2*r);
				sunToken.setMaxSize(2*r, 2*r);
				sunToken.setLayoutX(x);
				sunToken.setLayoutY(y);
				sunToken.getStylesheets().add("/sample/sunStyle.css");
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						myParent.getChildren().add(sunToken);
					}
				});
				sunflowerSun(x,y);
			}
		};
		timer.schedule(task,+10000);
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
			tSun.cancel();
		}
		else{
			flag=0;
			for(int i=0 ; i<animation.size() ; i++)
				if(animation.get(i).getStatus() == Animation.Status.PAUSED) {
					animation.get(i).play();
				}
//			funzombie();
			funsun();
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

	public void funzombie(){
		/*
		tZombie = new Timer();
		TimerTask taskZombie = new TimerTask() {
			@Override
			public void run() {
				ImageView zom = new ImageView();
				zom.setImage(zombie.getImage());
				zom.setLayoutX(1216.0);
				zom.setLayoutY(330.0);

				///

				myZombies.add(zom);

				///

				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						myParent.getChildren().add(zom);
					}
				});
				PathElement[] path1 = {
						new MoveTo(zom.getX(), zom.getY() + 60),
						new LineTo(zom.getX() - 900, zom.getY() + 60),
				};
				Path road1 = new Path();
				road1.getElements().addAll(path1);
				PathTransition anim = new PathTransition();
				animation.add(anim);
				anim.setOnFinished(e->endZombie(zom));
				anim.setNode(zom);
				anim.setPath(road1);
				anim.setDuration(new Duration(80000));
				anim.play();
				funzombie();
			}
		};
		tZombie.schedule(taskZombie,10000);*/

		Transition zombieCreater = new Transition() {
			{
				this.setCycleDuration(Duration.seconds(15));
				this.setCycleCount(INDEFINITE);
			}
			@Override
			protected void interpolate(double frac) {
				if (frac==0){
				Zombie NZ = new NormalZombie(1216, myParent, animation, myPlants);
				myzombies.add(NZ);}
			}
		} ;
		zombieCreater.play();
	}
	private void endZombie(ImageView zom) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				zom.setVisible(false);
				myParent.getChildren().remove(zom);
				moveLawnmover();
			}
		});
	}
	public int tr = 0;
	public void moveLawnmover() {
		if(tr==0) {
			PathElement[] path = {
					new MoveTo(326, 55),
					new LineTo(1250, 55),
			};
			Path road = new Path();
			road.getElements().addAll(path);
			PathTransition anim = new PathTransition();
			animation.add(anim);
			anim.setOnFinished(e->endLawnmover());
			anim.setNode(LandMover);
			anim.setPath(road);
			anim.setDuration(new Duration(6000));
			anim.play();
			tr++;
		}
	}
	private void endLawnmover() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				LandMover.setVisible(false);
				myParent.getChildren().remove(LandMover);
				moveLawnmover();
			}
		});
	}


	public void funsun(){
		tSun = new Timer();
		TimerTask taskSun = new TimerTask() {
			@Override
			public void run() {
				//System.out.println("here");
				Button sunToken = new Button();
				sunToken.setOnMouseClicked(event -> {
					changeSunValue(100);
					sunToken.setVisible(false);
					myParent.getChildren().remove(sunToken);
				});
				Random rand = new Random();
				double r=25;
				int x = 326 + rand.nextInt(200);
				int y = 260 + rand.nextInt(100);
				sunToken.setShape(new Circle(r));
				sunToken.setMinSize(2*r, 2*r);
				sunToken.setMaxSize(2*r, 2*r);
				sunToken.setLayoutX(x);
				sunToken.setLayoutY(80.0);
				sunToken.getStylesheets().add("/sample/sunStyle.css");
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						myParent.getChildren().add(sunToken);
					}
				});
				PathElement[] path = {
						new MoveTo(x,-100.0),
						new LineTo(x, y),
				};
				Path road = new Path();
				road.getElements().addAll(path);
				PathTransition anim = new PathTransition();
				animation.add(anim);
				anim.setNode(sunToken);
				anim.setPath(road);
				anim.setDuration(new Duration(10000));
				anim.play();
				funsun();
			}
		};
		tSun.schedule(taskSun,9000);
	}



	@Override
	public void initialize(URL location, ResourceBundle resources) {
		funsun();
		funzombie();
		startMeter();
		animation = new ArrayList<>(0);
		myZombies = new ArrayList<>();
		myzombies = new ArrayList<>();
		myPlants = new ArrayList<>();
	}


}
