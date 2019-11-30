package sample;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Level5Controller extends levelController {
    public void init(Game g) {
        myGame = g;
        animation = new ArrayList<>(0);
        myCourtyard = new CourtYard5(myParent, animation, sunCount, winMenu);
        this.myLevel = 2;
        myGame.addCourtyard(myLevel, myCourtyard);
        startMeter();
    }
}
