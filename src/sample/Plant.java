package sample;

import javafx.animation.Transition;
import javafx.scene.layout.AnchorPane;

import java.io.Serializable;
import java.util.ArrayList;

public  abstract class Plant extends Character implements Serializable {
        private final int dist_house;

        public int getDist_house() {
                return dist_house;
                }

                @Override
        public void killMe(){
            setHp(0);
                if (myanimation!= null){
                        myanimation.stop();
                }
                myParent.getChildren().remove(myImg);
                myCourtyard.removePlant(this.getRow(), this.getDist_house());
        }
        Plant(int i, int j, AnchorPane parent, Courtyard yard, int hp){
                super(i);
                this.dist_house = j;
                this.myParent = parent;
                this.myCourtyard = yard;
                this.maxHp = hp;
                this.hp = hp;
        }

        abstract void restoreMe(AnchorPane pn, ArrayList<Transition> anim);
}