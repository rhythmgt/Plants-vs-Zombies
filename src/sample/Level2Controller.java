package sample;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Level2Controller extends levelController {
    public void initialize(URL location, ResourceBundle resources) {
        animation = new ArrayList<>(0);
        myCourtyard = new Courtyard2(myParent, animation, sunCount, winMenu);

        startMeter();
    }
}
