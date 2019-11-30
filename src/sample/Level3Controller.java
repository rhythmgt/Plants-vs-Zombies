package sample;

import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Level3Controller extends levelController {

    public ImageView sunflowerCard;
    public ImageView cherrybombCard;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.sunflowerCards = sunflowerCard ;
        this.cherrybombCards = cherrybombCard ;
    }

    public void init(Game g) {
        myGame = g;
        animation = new ArrayList<>(0);
        myCourtyard = new Courtyard3(myParent, animation, sunCount, endMenu , winMenu);
        this.myLevel = 3;
        myGame.addCourtyard(myLevel, myCourtyard);
        startMeter();
    }
}
