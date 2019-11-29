package sample;

import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class CherryBomb extends Plant {
    private ArrayList<CopyOnWriteArrayList<Zombie>> Enemies;
    CherryBomb(int row, int col,double i, double j, AnchorPane parent, Courtyard yard, ArrayList<CopyOnWriteArrayList<Zombie>> al) {
        super(row, col, parent, yard, 100);
        myImg = new ImageView("/sample/images/plants/bomb0.gif");
        myImg.setLayoutX(i-60);
        myImg.setLayoutY(j-30);
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
                        enemy.killMe();}
                    }
                }
                state = 5;
            }
        }
    }
}
