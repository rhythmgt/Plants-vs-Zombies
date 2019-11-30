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
        myImg = new ImageView("/sample/images/zombies/ConeheadZombie.gif");
        moving1 = new Image("/sample/images/zombies/ConeheadZombie.gif");
        moving2 = new Image("/sample/images/zombies/zombie_normal.gif");
        fightImage1 = new Image("/sample/images/zombies/ConeheadZombieAttack.gif");
        fightImage2 = new Image("/sample/images/zombies/ZombieAttack.gif");
        myImg.setLayoutX(i-33.5);
        myImg.setLayoutY(j-20);
        myImg.setScaleY(0.8*myImg.getScaleY());
        parent.getChildren().add(myImg);
        moveZombie(animations, Enemies);
    }

    void moveZombie(ArrayList<Transition> animations, Plant[][] Enemies){
        myanimation= this.new moveZombieAnimation(myImg.getLayoutX(), myImg.getLayoutX()-900, Enemies[this.getRow()]);
        myanimation.setCycleCount(1);
        animations.add(myanimation);
        myanimation.play();
    }



}
