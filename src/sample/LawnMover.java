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

    public Boolean getPresent() {
        return isPresent;
    }

    public ImageView getImage(){
        return myImg;
    }
    LawnMover(int r, AnchorPane AP, ArrayList<CopyOnWriteArrayList<Zombie>> zmb){
        myParent = AP;
        myImg = new ImageView("/sample/images/landMower.png");
        myImg.setLayoutX(220.0);
        myImg.setLayoutY(364.0);
        AP.getChildren().add(myImg);
        myImg.setVisible(true);
        zombiesToKill = zmb.get(r);
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
            difference = 1250.0 - position;
        }
        @Override
        protected void interpolate(double frac) {
            myImg.setLayoutX(position + (difference*frac));
            isCollided();
        }
        void isCollided(){
            for (Zombie enemy : zombiesToKill){
                if (enemy!=null && myImg.getBoundsInParent().intersects(enemy.getImage().getBoundsInParent())){
                    enemy.killMe();
                    System.out.println("Zombie Killed");
                }
            }
        }

    }
}
