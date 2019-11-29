package sample;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.Serializable;

public abstract class Character implements Serializable {

        private int hp;
        private final int row;
        protected ImageView myImg;
        protected AnchorPane myParent;
        protected Courtyard myCourtyard;

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

        Character(int i , int j){
            hp = i ;
            row = j ;
        }
        Character(int i){
            row = i ;
        }

        public boolean getAttacked(int i){
            return true;
        }

}
