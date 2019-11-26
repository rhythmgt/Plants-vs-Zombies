package sample;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;


import java.io.Serializable;

public class SunFlower extends Plant implements Serializable {
    SunFlower(double i, double j, AnchorPane parent) {
        super((int)i, (int)j);
        myImg = new ImageView("/sample/images/plants/sunflower.gif");
        myImg.setLayoutX(i-33.5);
        myImg.setLayoutY(j);
        parent.getChildren().add(myImg);
    }
}
