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
    public void restartLevel(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("level4.fxml"));
        Parent root = (Parent) loader.load();
        ((levelController) loader.getController()).init(myGame);
        ((Node) mouseEvent.getSource()).getScene().setRoot(root);	}
}
