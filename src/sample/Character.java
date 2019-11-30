package sample;

import javafx.animation.Transition;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.io.Serializable;

public abstract class Character implements Serializable {

        protected int hp;
        protected int maxHp;
        private final int row;
        protected transient ImageView myImg;
        protected transient AnchorPane myParent;
        protected Courtyard myCourtyard;
        protected double myX;
        protected double myY;
        protected String s0;

        protected transient Transition myanimation;

    public void setMyanimation(Transition myanimation) {
        this.myanimation = myanimation;
    }

    public ImageView getImage(){
            return myImg;
        }

        public int getHp() {
            return hp;
        }

        public void setHp(int hp) {
            this.hp = hp;
        }

        public int getRow() {
            return row;
        }


        Character(int i){
            row = i ;
        }

        public void getAttacked(int i) throws IOException {
            this.hp -= i;
            if (hp<=0){
                killMe();
            }
        }

        public abstract void killMe() throws IOException;



}
