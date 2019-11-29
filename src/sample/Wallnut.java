package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class Wallnut extends Plant {
    Wallnut(int row, int col,double i, double j, AnchorPane parent, Courtyard yard) {
        super(row, col, parent, yard, 100);
        myImg = new ImageView("/sample/images/plants/wNut0.png");
        myImg.setLayoutX(i-33.5);
        myImg.setLayoutY(j);
        parent.getChildren().add(myImg);
    }

    @Override
    public void getAttacked(int i){
        this.hp -= i;
        if (hp<0.6*maxHp){
            myImg.setImage(new Image("/sample/images/plants/wNut1.png"));
        }
        if (hp<0.3*maxHp){
            myImg.setImage(new Image("/sample/images/plants/wNut2.png"));
        }
        if (hp<=0){
            killMe();
        }

    }

}
