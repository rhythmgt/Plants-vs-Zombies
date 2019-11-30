package sample;

import javafx.animation.Transition;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.util.ArrayList;

public class Courtyard2 extends Courtyard {

    Courtyard2(AnchorPane AP, ArrayList<Transition> anim, Label TokenValue, AnchorPane menu) {
        super(3, AP, anim, TokenValue, menu);
        mybounds = new double[]{308.0, 412.0, 512.0, 606.0, 715.0, 815.0, 917.0, 1012.0, 1123.0, 1223.0};
        vertBounds = new double[]{213.0 ,333.0,457.0,582.0};
        initializeLandMovers();
        Transition t = new ZombieCreator();
        t.play();
        myAnimations.add(t);
    }

    @Override
    void restoreZombieCreator() {
        Transition t = new ZombieCreator();
        t.playFrom(new Duration(animState));
        myAnimations.add(t);
    }
    private class ZombieCreator extends Transition{

        ZombieCreator(){
            this.setCycleDuration(Duration.seconds(15));
            this.setCycleCount(INDEFINITE);
        }
        @Override
        protected void interpolate(double frac) {

            animState = this.getCurrentTime().toMillis();
            if (frac==0){addZombie(0);}
        }
    }
}