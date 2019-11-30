package sample;

import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;

public class CourtYard1 extends Courtyard {

    CourtYard1(AnchorPane AP, ArrayList<Transition> anim, Label TokenValue, AnchorPane menu , AnchorPane winMenu) {
        super(1, AP, anim, TokenValue, menu);
        mybounds = new double[]{318.0, 427.0, 514.0, 625.0, 730.0, 825.0, 934.0, 1027.0, 1135.0, 1254.0};
        vertBounds = new double[]{343.0, 477.0};
        this.winMenu = winMenu;
        initializeLandMovers();
        Transition t = new ZombieCreator();
        t.play();
        animationState = 1 ;
        t.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                animationState=0;
            }
        });
        myAnimations.add(t);
    }

    @Override
    void restoreZombieCreator() {
        Transition t = new ZombieCreator();
        t.playFrom(new Duration(animState));
        t.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                animationState=0;
            }
        });
        myAnimations.add(t);
    }

    private class ZombieCreator extends Transition{

        ZombieCreator(){
            this.setCycleDuration(Duration.seconds(15));
            this.setCycleCount(2);
        }
        @Override
        protected void interpolate(double frac) {
            animState = this.getCurrentTime().toMillis();
            Random rand = new Random();
            int x = rand.nextInt(1);
            if (frac==0){addZombie(x);}
        }
    }
}
