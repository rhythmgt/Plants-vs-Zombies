package sample;

import javafx.animation.Transition;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class peaAnimation extends Transition {

    private double position;
    private double difference;
    private ImageView myshape;
    private CopyOnWriteArrayList<Zombie> myenemies;
    private final AnchorPane myParent;

    peaAnimation(Duration dr, double start, double end, ImageView myshape, CopyOnWriteArrayList<Zombie> enemy, AnchorPane ap) {
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
        try {
            isCollided();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    void isCollided() throws IOException {

        for (Zombie myenemy : myenemies){
            if (myshape.getBoundsInParent().intersects(myenemy.getImage().getBoundsInParent())) {
                System.out.println("Thuk gya");
                myenemy.getAttacked(10);
                //myshape.setVisible(false);
                myParent.getChildren().remove(myshape);
                this.stop();

            }
        }
    }
}
