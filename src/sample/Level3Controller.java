package sample;

import java.util.ArrayList;

public class Level3Controller extends levelController {
    public void init(Game g) {
        myGame = g;
        animation = new ArrayList<>(0);
        myCourtyard = new Courtyard3(myParent, animation, sunCount, endMenu , winMenu);
        this.myLevel = 3;
        myGame.addCourtyard(myLevel, myCourtyard);
        startMeter();
    }
}
