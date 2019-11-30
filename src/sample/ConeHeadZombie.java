package sample;

import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.Serializable;
import java.util.ArrayList;

public class ConeHeadZombie extends Zombie implements Serializable {
    ConeHeadZombie(int ind, double i, double j, AnchorPane parent, ArrayList<Transition> animations, Plant[][] Enemies, Courtyard courtYard) {
        super(ind, 200, parent, courtYard);
        targetLandMover = courtYard.getLawnMovers()[ind];
        myParent = parent;
        s0 = "/sample/images/zombies/ConeheadZombie.gif";
        myImg = new ImageView(s0);
        s1 = "/sample/images/zombies/ConeheadZombie.gif";
        s2 = "/sample/images/zombies/zombie_normal.gif";
        s3 = "/sample/images/zombies/ConeheadZombieAttack.gif";
        s4 = "/sample/images/zombies/ZombieAttack.gif";
        myX = i-33.5;
        myY = j-20;
        myImg.setLayoutX(myX);
        myImg.setLayoutY(myY);
        myImg.setScaleY(0.8*myImg.getScaleY());
        parent.getChildren().add(myImg);
        enemies = Enemies[this.getRow()];
        moveZombie(animations);
    }

    void moveZombie(ArrayList<Transition> animations){
        myanimation= this.new moveZombieAnimation(myImg.getLayoutX(), myImg.getLayoutX()-900);
        myanimation.setCycleCount(1);
        animations.add(myanimation);
        myanimation.play();
    }



}
