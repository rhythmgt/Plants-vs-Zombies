package sample;

import javafx.animation.Transition;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.util.ArrayList;

public class CourtYard5 extends Courtyard {

    CourtYard5(AnchorPane AP, ArrayList<Transition> anim, Label TokenValue) {
        super(5, AP, anim, TokenValue);
        mybounds = new double[]{308.0, 412.0, 512.0, 606.0, 715.0, 815.0, 917.0, 1012.0, 1123.0, 1223.0};
        vertBounds = new double[]{98.0,217.0 ,331.0,459.0,560.0,697.0};
        initializeLandMovers();
        Transition t = new ZombieCreator();
        t.play();
        myAnimations.add(t);
    }

    private class ZombieCreator extends Transition{

        ZombieCreator(){
            this.setCycleDuration(Duration.seconds(15));
            this.setCycleCount(INDEFINITE);
        }
        @Override
        protected void interpolate(double frac) {
            if (frac==0){addZombie(0);}
        }
    }
}
