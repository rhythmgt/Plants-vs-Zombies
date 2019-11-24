package sample;

import javafx.animation.Transition;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

import java.util.ArrayList;

public class peaAnimation extends Transition {

    private double position;
    private double difference;
    private ImageView myshape;
    private ArrayList<ImageView> myenemies;
    private final AnchorPane myParent;

    peaAnimation(Duration dr, double start, double end, ImageView myshape, ArrayList<ImageView> enemy, AnchorPane ap) {
        setCycleDuration(dr);
        position = start;
        difference = end - start;
        this.myshape = myshape;
        this.myenemies = enemy;
        this.myParent = ap;
    }

    @Override
    protected void interpolate(double frac) {
        if (frac==0){
            myshape.setVisible(true);
        }
        myshape.setLayoutX(position + (difference * frac));
        isCollided();
    }


    void isCollided() {

        for (ImageView myenemy : myenemies){
            if (myshape.getBoundsInParent().intersects(myenemy.getBoundsInParent())) {
                System.out.println("Thuk gya");
                System.out.println("Ho gya");
                myshape.setVisible(false);
                //myParent.getChildren().remove(myshape);
                //this.stop();

            }
        }
    }
}
