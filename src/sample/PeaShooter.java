package sample;

import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.Serializable;
import java.util.ArrayList;

public class PeaShooter extends Plant implements Serializable {
    private AnchorPane parent;
    private ArrayList<Zombie> opponent;
    PeaShooter(int row, int col, double i, double j, AnchorPane parent, ArrayList<ArrayList<Zombie>> al, ArrayList<Transition> animations) {
        super(row, col);
        myImg = new ImageView("/sample/images/plants/peashooter.gif");
        myImg.setLayoutX(i-33.5);
        myImg.setLayoutY(j);
        this.parent = parent;
        this.parent.getChildren().add(myImg);
        this.opponent = al.get(row);
        try {
            shootPea(animations);
        }
        catch (InterruptedException ie){
            ;
        }
    }
    
    private void shootPea(ArrayList<Transition> animations) throws InterruptedException {
        //instantiate peas here
            Transition abc = new Transition() {
                {
                    this.setCycleDuration(Duration.seconds(5));
                }
                @Override
                protected void interpolate(double frac) {
                    if (frac==0){
                        Pea pea1 = new Pea(animations);
                    }
                }

            };
            animations.add(abc);
            abc.setCycleCount(Animation.INDEFINITE);
            abc.play();
    };


    private class Pea{
        private final ImageView peaImage;
        
        Pea(ArrayList<Transition> animations){
            peaImage = new ImageView("sample/images/pea.png");
            peaImage.setVisible(true);
            peaImage.setFitHeight(21.0);
            peaImage.setFitWidth(21.0);
            peaImage.setLayoutX(myImg.getLayoutX()+10);
            peaImage.setLayoutY(330);
            peaAnimation anim = new peaAnimation(Duration.millis(4000), peaImage.getLayoutX(), peaImage.getLayoutX() + 1000, peaImage, opponent, parent);
            parent.getChildren().add(peaImage);
            animations.add(anim);
            anim.play();
        }
    }
}
