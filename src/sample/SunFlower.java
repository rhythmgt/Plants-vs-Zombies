package sample;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;


import java.io.Serializable;

public class SunFlower extends Plant implements Serializable {
    SunFlower(double i, double j, AnchorPane parent) {
        super((int)i, (int)j);
        ImageView img = new ImageView("/sample/images/plants/sunflower.gif");
        img.setLayoutX(i-33.5);
        img.setLayoutY(j);
        parent.getChildren().add(img);
    }
}
