package sample;

import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class CherryBomb extends Plant {
    private ArrayList<CopyOnWriteArrayList<Zombie>> Enemies;
    CherryBomb(int row, int col,double i, double j, AnchorPane parent, Courtyard yard, ArrayList<CopyOnWriteArrayList<Zombie>> al) {
        super(row, col, parent, yard, 100);
        s0 = "/sample/images/plants/bomb0.gif";
        myImg = new ImageView(s0);
        myX = i-60;
        myY = j-30;
        myImg.setLayoutX(myX);
        myImg.setLayoutY(myY);
        Enemies = al;
        parent.getChildren().add(myImg);
        ExplodeTransition t =new ExplodeTransition();
        t.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                killMe();
            }
        });

        t.play();
    }

    @Override
    void restoreMe(AnchorPane pane, ArrayList<Transition> anim) {
        myParent = pane;
        myImg = new ImageView(s0);
        myImg.setLayoutX(myX);
        myImg.setLayoutY(myY);
        myParent.getChildren().add(myImg);
        ExplodeTransition t =new ExplodeTransition();
        t.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                killMe();
            }
        });
        t.play();
    }

    class ExplodeTransition extends Transition{
        int state = 1;
        ExplodeTransition() {
            this.setCycleDuration(Duration.seconds(3));
            this.setCycleCount(1);
        }

        @Override
        protected void interpolate(double frac) {
            if (state==1 && (frac>=0.33 || frac<0.4)){

                myImg.setImage(new Image("/sample/images/plants/bomb1.gif"));
                state = 2;
            }
            if (state ==2 && (frac >= 0.7)){
                myImg.setImage(new Image("/sample/images/plants/bomb3.gif"));
                state = 3;
            }
            if (state==3){
                myImg.setScaleX(2*myImg.getScaleX());
                myImg.setScaleY(2*myImg.getScaleY());
                state=4;
            }
            if (state==4){
                for (CopyOnWriteArrayList<Zombie> enemyList : Enemies){
                    for (Zombie enemy : enemyList){
                        if (myImg.getBoundsInParent().intersects(enemy.getImage().getBoundsInParent())){
                            try {
                                enemy.killMe();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                state = 5;
            }
        }
    }
}
