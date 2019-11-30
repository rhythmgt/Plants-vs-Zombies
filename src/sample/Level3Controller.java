package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
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
    public void restartLevel(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("level3.fxml"));
        Parent root = (Parent) loader.load();
        ((Level3Controller) loader.getController()).init(myGame);
        ((Node) mouseEvent.getSource()).getScene().setRoot(root);	}

        public void nextLevel(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("level4.fxml"));
        Parent root = (Parent) loader.load();
        ((Level4Controller) loader.getController()).init(myGame);
        ((Node) mouseEvent.getSource()).getScene().setRoot(root);	}

}
