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
        s0 = "/sample/images/plants/potato0.gif";
        myImg = new ImageView(s0);
        myX = i;
        myY = j;
        myImg.setLayoutX(myX);
        myImg.setLayoutY(myY);
        this.myParent = parent;
        myParent.getChildren().add(myImg);
        GrowTransition t =new GrowTransition();
        myanimation = t;
        t.play();
        Enemies = al.get(row);
    }

    @Override
    void restoreMe(AnchorPane pane, ArrayList<Transition> anim) {
        myParent = pane;
        myImg = new ImageView(s0);
        myImg.setLayoutX(myX);
        myImg.setLayoutY(myY);
        myParent.getChildren().add(myImg);

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
                s0 = "/sample/images/plants/potato1.gif";
                myImg.setImage(new Image(s0));
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
