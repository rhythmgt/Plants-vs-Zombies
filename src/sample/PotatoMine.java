package sample;

import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class PotatoMine extends Plant {
    private CopyOnWriteArrayList<Zombie> Enemies;
    PotatoMine(int row, int col, double i, double j, AnchorPane parent, Courtyard yard, ArrayList<CopyOnWriteArrayList<Zombie>> al) {
        super(row, col, parent, yard, 100);
        myImg = new ImageView("/sample/images/plants/potato0.gif");
        myImg.setLayoutX(i);
        myImg.setLayoutY(j);
        Enemies = al.get(row);
        parent.getChildren().add(myImg);
        GrowTransition t =new GrowTransition();
        myanimation = t;
        t.play();
    }

    class GrowTransition extends Transition{
        int state = 1;
        GrowTransition() {
            this.setCycleDuration(Duration.seconds(6));
            this.setCycleCount(INDEFINITE);
        }

        @Override
        protected void interpolate(double frac) {
            if (state==1 && (frac>=0.45 && frac<0.65)){
                myImg.setImage(new Image("/sample/images/plants/potato1.gif"));
                state = 2;
            }
            for (Zombie enemy : Enemies){
                if (myImg.getBoundsInParent().intersects(enemy.getImage().getBoundsInParent())){
                    enemy.killMe();
                    killMe();
                    this.stop();
                }
            }
        }
    }
}
