package sample;

import javafx.animation.Transition;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class SunToken {
    Button myImage;
    AnchorPane myParent;
    Courtyard myYard;
    SunToken(AnchorPane myParent, Courtyard myYard, double x, double ystart, double yend){
        myImage = new Button();
        myImage.setOnMouseClicked(event -> {
            myYard.changeSunValue(100);
            myImage.setVisible(false);
            myParent.getChildren().remove(myImage);
        });
        double r=25;
        myImage.setShape(new Circle(r));
        myImage.setMinSize(2*r, 2*r);
        myImage.setMaxSize(2*r, 2*r);
        myImage.setLayoutX(x);
        myImage.setLayoutY(80.0);
        myImage.getStylesheets().add("/sample/sunStyle.css");
        myParent.getChildren().add(myImage);
        double difference = yend-ystart;
        Transition t = new Transition() {
            {
                this.setCycleCount(1);
                this.setCycleDuration(Duration.seconds(18));
            }
            @Override
            protected void interpolate(double frac) {
                myImage.setLayoutY(ystart + frac*difference);
            }
        };
        t.play();
    }
}
