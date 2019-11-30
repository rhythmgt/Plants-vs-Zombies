package sample;

import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Level4Controller extends levelController {
    public ImageView sunflowerCard;
    public ImageView cherrybombCard;
    public ImageView wallnutCard;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.sunflowerCards = sunflowerCard ;
        this.cherrybombCards = cherrybombCard ;
        this.wallnutCards = wallnutCard ;
    }
    public void init(Game g) {
        myGame = g;
        animation = new ArrayList<>(0);
        myCourtyard = new CourtYard4(myParent, animation, sunCount, endMenu , winMenu);
        this.myLevel = 4;
        myGame.addCourtyard(myLevel, myCourtyard);
        startMeter();
    }
}
