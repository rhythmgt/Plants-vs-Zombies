package sample;

import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;

public class Courtyard2 extends Courtyard {

    Courtyard2(AnchorPane AP, ArrayList<Transition> anim, Label TokenValue, AnchorPane menu,AnchorPane winMenu) {
        super(3, AP, anim, TokenValue, menu);
        mybounds = new double[]{308.0, 412.0, 512.0, 606.0, 715.0, 815.0, 917.0, 1012.0, 1123.0, 1223.0};
        vertBounds = new double[]{213.0 ,333.0,457.0,582.0};
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
        myAnimations.add(t);
    }
    private class ZombieCreator extends Transition{

        ZombieCreator(){
            this.setCycleDuration(Duration.seconds(15));
            this.setCycleCount(9);
        }
        @Override
        protected void interpolate(double frac) {

            animState = this.getCurrentTime().toMillis();
            Random rand = new Random();
            int x = rand.nextInt(3);
            if (frac==0){addZombie(x,0);}
        }
    }
}
