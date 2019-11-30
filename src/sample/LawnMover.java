package sample;


import javafx.animation.Transition;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class LawnMover {
    private ImageView myImg;
    private AnchorPane myParent;
    private Boolean isPresent = true;
    private CopyOnWriteArrayList<Zombie> zombiesToKill;
    private int row;
    private Courtyard myCourtyard;

    public Boolean getPresent() {
        return isPresent;
    }

    public ImageView getImage(){
        return myImg;
    }
    LawnMover(int r,double y, AnchorPane AP, ArrayList<CopyOnWriteArrayList<Zombie>> zmb, Courtyard c){
        myParent = AP;
        myImg = new ImageView("/sample/images/landMower.png");
        myImg.setLayoutX(220.0);
        myImg.setLayoutY(y);
        myImg.setScaleX(myImg.getScaleX()/2);
        myImg.setScaleY(myImg.getScaleY()/2);
        AP.getChildren().add(myImg);
        myImg.setVisible(true);
        zombiesToKill = zmb.get(r);
        myCourtyard = c;
    }

    public void moveMe(){
        isPresent = false;
        Transition t = new LawnMoverTransition();
        t.play();
    }

    private class LawnMoverTransition extends Transition{
        private double position;
        private double difference;
        LawnMoverTransition(){
            setCycleDuration(Duration.seconds(10));
            setCycleCount(1);
            position = 220.0;
            difference = 1260.0 - position;
        }
        @Override
        protected void interpolate(double frac) {
            if (myImg.getLayoutX()>1254){
                myParent.getChildren().remove(myImg);
                this.stop();
                myCourtyard.removeLandMover(row);
            }
            myImg.setLayoutX(position + (difference*frac));
            isCollided();
        }
        void isCollided(){
            for (Zombie enemy : zombiesToKill){
                if (enemy!=null && myImg.getBoundsInParent().intersects(enemy.getImage().getBoundsInParent())){
                    enemy.killMe();
                    System.out.println("Zombie Killed");
                    enemy = null;
                }
            }
        }

    }
}
