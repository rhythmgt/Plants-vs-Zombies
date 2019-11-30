package sample;

import javafx.animation.Transition;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;


import java.io.Serializable;
import java.util.ArrayList;

public class SunFlower extends Plant implements Serializable {
    SunFlower(int row, int col,double i, double j, AnchorPane parent, Courtyard yard) {
        super(row, col, parent, yard, 100);
        s0 = "/sample/images/plants/sunflower.gif";
        myImg = new ImageView(s0);
        myImg.setLayoutX(i-33.5);
        myImg.setLayoutY(j);
        parent.getChildren().add(myImg);

    }

    void restoreMe(AnchorPane pane, ArrayList<Transition> anim){
        myParent = pane;
        myImg = new ImageView(s0);
        myImg.setLayoutX(myX);
        myImg.setLayoutY(myY);
        myParent.getChildren().add(myImg);
        //addAnimation
    }
}
