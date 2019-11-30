package sample;

import java.util.ArrayList;

public class Level4Controller extends levelController {
    public void init(Game g) {
        myGame = g;
        animation = new ArrayList<>(0);
        myCourtyard = new CourtYard4(myParent, animation, sunCount, endMenu , winMenu);
        this.myLevel = 4;
        myGame.addCourtyard(myLevel, myCourtyard);
        startMeter();
    }
}
