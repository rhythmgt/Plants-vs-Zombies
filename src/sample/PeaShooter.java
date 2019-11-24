package sample;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.Serializable;
import java.util.ArrayList;

public class PeaShooter extends Plant implements Serializable {
    private AnchorPane parent;
    private ArrayList<ImageView> opponent;
    PeaShooter(double i, double j, AnchorPane parent, ArrayList<ImageView> al) {
        super((int)i, (int)j);
        myImg = new ImageView("/sample/images/plants/PeaShooter.gif");
        myImg.setLayoutX(i-33.5);
        myImg.setLayoutY(j);
        this.parent = parent;
        this.parent.getChildren().add(myImg);
        this.opponent = al;
    }
    
    private void shootPea(){
        //instantiate peas here
    };
    
    private class Pea{
        private final ImageView peaImage;
        
        Pea(){
            peaImage = new ImageView("sample/images/pea.png");
            peaImage.setVisible(true);
            peaImage.setFitHeight(21.0);
            peaImage.setFitWidth(21.0);
            peaImage.setLayoutX(myImg.getLayoutX());
            peaImage.setLayoutY(330);
            peaAnimation anim = new peaAnimation(Duration.millis(4000), peaImage.getLayoutX(), peaImage.getLayoutX() + 1000, peaImage, this.opponent, parent);
            parent.getChildren().add(peaImage);
        }
    }
}
