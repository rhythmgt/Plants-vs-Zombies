package sample;

import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class PeaShooter extends Plant implements Serializable {
  //  private AnchorPane parent;
    private CopyOnWriteArrayList<Zombie> opponent;

    PeaShooter(int row, int col, double i, double j, AnchorPane parent, ArrayList<CopyOnWriteArrayList<Zombie>> al, ArrayList<Transition> animations, Courtyard yard) {
        super(row, col, parent, yard, 100);
        s0 = "/sample/images/plants/peashooter.gif";
        myImg = new ImageView(s0);
        myX = i-33.5;
        myY = j;
        myImg.setLayoutX(myX);
        myImg.setLayoutY(myY);
        this.myParent = parent;
        this.myParent.getChildren().add(myImg);
        this.opponent = al.get(row);

            shootPea(animations);

    }
    
    private void shootPea(ArrayList<Transition> animations) {
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
            myanimation = abc;
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
            peaImage.setLayoutX(myImg.getLayoutX()+50);
            peaImage.setLayoutY(myImg.getLayoutY());
            peaAnimation anim = new peaAnimation(Duration.millis(4000), peaImage.getLayoutX(), peaImage.getLayoutX() + 1000, peaImage, opponent, myParent);
            myParent.getChildren().add(peaImage);
            animations.add(anim);
            anim.play();
        }
    }

    void restoreMe(AnchorPane pane, ArrayList<Transition> anim){
        myParent = pane;
        myImg = new ImageView(s0);
        myImg.setLayoutX(myX);
        myImg.setLayoutY(myY);
        myParent.getChildren().add(myImg);
        shootPea(anim);
        //addAnimation
    }

}
