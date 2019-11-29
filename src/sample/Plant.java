package sample;

import javafx.scene.layout.AnchorPane;

import java.io.Serializable;

public  abstract class Plant extends Character implements Serializable {
        private int Refresh_time;
        private final int dist_house;
        private int costing;


        public int getCosting() {
                return costing;
                }
        public void setCosting(int costing) {
                this.costing = costing;
                }

        public int getRefresh_time() {
                return Refresh_time;
                }

        public void setRefresh_time(int refresh_time) {
                Refresh_time = refresh_time;
                }

        public int getDist_house() {
                return dist_house;
                }

                @Override
        public void killMe(){
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
}