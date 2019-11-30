package sample;

import javafx.animation.Transition;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.util.ArrayList;

public class CourtYard1 extends Courtyard {

    CourtYard1(AnchorPane AP, ArrayList<Transition> anim, Label TokenValue, AnchorPane menu) {
        super(1, AP, anim, TokenValue, menu);
        mybounds = new double[]{318.0, 427.0, 514.0, 625.0, 730.0, 825.0, 934.0, 1027.0, 1135.0, 1254.0};
        vertBounds = new double[]{343.0, 477.0};
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
