package sample;

import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Level2Controller extends levelController {

    public ImageView sunflowerCard;
    public ImageView wallnutCard;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.sunflowerCards = sunflowerCard ;
        this.wallnutCards = wallnutCard ;
    }
    public void init(Game g) {
        myGame = g;
        animation = new ArrayList<>(0);
        myCourtyard = new Courtyard2(myParent, animation, sunCount, endMenu , winMenu);
        this.myLevel = 2;
        myGame.addCourtyard(myLevel, myCourtyard);
        startMeter();
    }
}
