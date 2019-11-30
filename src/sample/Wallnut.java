package sample;

import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public class Wallnut extends Plant {
    Wallnut(int row, int col,double i, double j, AnchorPane parent, Courtyard yard) {
        super(row, col, parent, yard, 100);
        myParent = parent;
        s0 = "/sample/images/plants/wNut0.png";
        myImg = new ImageView(s0);
        myX = i-33.5;
        myY = j;
        myImg.setLayoutX(myX);
        myImg.setLayoutY(myY);
        myParent.getChildren().add(myImg);
    }

    @Override
    public void getAttacked(int i){
        this.hp -= i;
        if (hp<0.6*maxHp){
            s0 = "/sample/images/plants/wNut1.png";
            myImg.setImage(new Image(s0));
        }
        if (hp<0.3*maxHp){
            s0 = "/sample/images/plants/wNut2.png";
            myImg.setImage(new Image(s0));
        }
        if (hp<=0){
            killMe();
        }

    }

    @Override
    void restoreMe(AnchorPane pane, ArrayList<Transition> anim) {
        myParent = pane;
        myImg = new ImageView(s0);
        myImg.setLayoutX(myX);
        myImg.setLayoutY(myY);
        myParent.getChildren().add(myImg);

    }
}
