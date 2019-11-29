package sample;

import javafx.animation.Transition;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.Serializable;

public abstract class Character implements Serializable {

        private int hp;
        private final int row;
        protected ImageView myImg;
        protected AnchorPane myParent;
        protected Courtyard myCourtyard;
    protected Transition myanimation;
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

        public void getAttacked(int i){
            this.hp -= i;
            if (hp<=0){
                killMe();
            }
        }

        public abstract void killMe();

}
