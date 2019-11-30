package sample;

import javafx.animation.Transition;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;


import java.io.Serializable;

public class SunFlower extends Plant implements Serializable {
    SunFlower(int row, int col,double i, double j, AnchorPane parent, Courtyard yard) {
        super(row, col, parent, yard, 100);
        myImg = new ImageView("/sample/images/plants/sunflower.gif");
        myImg.setLayoutX(i-33.5);
        myImg.setLayoutY(j);
        parent.getChildren().add(myImg);

    }
}
