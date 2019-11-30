package sample;


import javafx.animation.Transition;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class LawnMover implements Serializable {
    private transient ImageView myImg;
    private  transient AnchorPane myParent;
    private String s0;
    private double myX;
    private double myY;
    private double mywidth;
    private double myheight;
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
        s0 = "/sample/images/landMower.png";
        myImg = new ImageView(s0);
        myX = 220.0;
        myY = y;
        myImg.setLayoutX(myX);
        myImg.setLayoutY(myY);
        mywidth = myImg.getScaleX()/2;
        myheight = myImg.getScaleY()/2;
        myImg.setScaleX(mywidth);
        myImg.setScaleY(myheight);
        AP.getChildren().add(myImg);
        myImg.setVisible(true);
        zombiesToKill = zmb.get(r);
        myCourtyard = c;
    }

    public void moveMe(){
        isPresent = false;
        Transition t = new LawnMoverTransition();
        t.play();
        myCourtyard.myAnimations.add(t);
    }

    public void restoreMe(AnchorPane pn) {
        myParent = pn;
        myImg = new ImageView(s0);
        myImg.setLayoutX(myX);
        myImg.setLayoutY(myY);
        myImg.setScaleX(mywidth);
        myImg.setScaleY(myheight);
        myParent.getChildren().add(myImg);
        //addAnimation
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
            try {
                isCollided();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        void isCollided() throws IOException {
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
