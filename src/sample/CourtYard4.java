package sample;

import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.util.ArrayList;

public class CourtYard4 extends Courtyard {

    CourtYard4(AnchorPane AP, ArrayList<Transition> anim, Label TokenValue, AnchorPane menu, AnchorPane winMenu) {
        super(5, AP, anim, TokenValue, menu);
        mybounds = new double[]{308.0, 412.0, 512.0, 606.0, 715.0, 815.0, 917.0, 1012.0, 1123.0, 1223.0};
        vertBounds = new double[]{98.0,217.0 ,331.0,459.0,560.0,697.0};
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
        t.play();
        myAnimations.add(t);
    }
    private class ZombieCreator extends Transition{

        ZombieCreator(){
            this.setCycleDuration(Duration.seconds(15));
            this.setCycleCount(4);
        }
        @Override
        protected void interpolate(double frac) {
            if (frac==0){addZombie(0);}
        }
    }
}
