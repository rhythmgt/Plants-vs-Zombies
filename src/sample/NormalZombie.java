package sample;

import javafx.animation.Transition;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.Serializable;
import java.util.ArrayList;

public class NormalZombie extends Zombie implements Serializable {
    NormalZombie(int ind, double i, AnchorPane parent, ArrayList<Transition> animations, Plant[][] Enemies) {
        super(ind);

        myImg = new ImageView("/sample/images/zombies/zombie.gif");
        myImg.setLayoutX(i-33.5);
        myImg.setLayoutY(333);
        parent.getChildren().add(myImg);
        moveZombie(animations, Enemies);
    }

    void moveZombie(ArrayList<Transition> animations, Plant[][] Enemies){
        Transition movingTransition= this.new moveZombieAnimation(myImg.getLayoutX(), myImg.getLayoutX()-900, Enemies[this.getRow()]);
        movingTransition.setCycleCount(1);
        animations.add(movingTransition);
        movingTransition.play();
    }
}
