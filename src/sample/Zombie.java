package sample;

import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

abstract class Zombie extends Character implements Serializable{
    private int status;
    protected String s1;
    protected String s2;
    protected String s3;
    protected String s4;
    protected double animState;
    protected Plant[] enemies;
    protected LawnMover targetLandMover;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    Zombie(int i, int maxhp, AnchorPane parent, Courtyard yard) {
        super(i);
        this.maxHp = maxhp;
        this.setHp(maxhp);
        myParent = parent;
        this.myCourtyard = yard;

    }

    @Override
    public void getAttacked(int i) throws IOException {
        this.hp -= i;
        if (hp<=0){
            killMe();
        }
        if (hp<0.5*maxHp){
            myImg.setImage(new Image(s2));
        }
    }

    @Override
    public void killMe() throws IOException {
        setHp(0);
        myParent.getChildren().remove(myImg);
        myanimation.stop();
        myCourtyard.removeZombie(getRow(),this);
    }

    protected void attackPlant(Plant p){
        Transition t = new Transition() {
            {
                if (getHp()<0.5*maxHp){
                    myImg.setImage(new Image(s4));
                }
                else{
                    myImg.setImage(new Image(s3));
                }
                setCycleDuration(Duration.seconds(1));
                setCycleCount(INDEFINITE);
            }
            @Override
            protected void interpolate(double frac) {
                if (frac==0){
                    if (p!=null){
                        if (p.getHp()<=0){
                            //p = null;
                            this.stop();
                            myanimation.play();
                        }
                        else{
                            try {
                                p.getAttacked(10);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            System.out.println("attacking");
                        }
                    }
                    else{
                        this.stop();
                        if (hp<0.5*maxHp){
                            myImg.setImage(new Image(s2));
                        }
                        else{
                            myImg.setImage(new Image(s1));
                        }
                        myanimation.play();
                    }
                }
            }
        };
        t.play();
    }
    protected class moveZombieAnimation extends Transition{
        private double position;
        private double difference;
        private Plant[] myEnemies;
        moveZombieAnimation(double startPoint, double endPoint){
            setCycleDuration(Duration.seconds(50));
            position = startPoint;
            difference =endPoint-startPoint;
            myEnemies = enemies;

        }

        @Override
        protected void interpolate(double frac) {
            animState = this.getCurrentTime().toMillis();
            myX = position + (difference*frac);
            myImg.setLayoutX(myX);
            if (myImg.getLayoutX()<318){
                System.out.println("YOU LOST");
                myCourtyard.gameLost();
                this.stop();
            }
            isCollided();
        }

        void isCollided(){
            for (Plant enemy : myEnemies){
                if (enemy!=null && myImg.getBoundsInParent().intersects(enemy.getImage().getBoundsInParent())){

                    myanimation.pause();
                    System.out.println("I will fight with the plant");
                    attackPlant(enemy);
                }
            }

            if (targetLandMover != null && myImg.getBoundsInParent().intersects(targetLandMover.getImage().getBoundsInParent())){

                if (targetLandMover.getPresent()){
                    System.out.println("Collided");
                    targetLandMover.moveMe();
                }

            }
        }


    }

    public void restoreMe(AnchorPane pn, ArrayList<Transition> anim){
        myParent = pn;
        myImg = new ImageView(s0);
        myImg.setLayoutX(myX);
        myImg.setLayoutY(myY);
        myParent.getChildren().add(myImg);

        myanimation= this.new moveZombieAnimation(myImg.getLayoutX(), myImg.getLayoutX()-900);

        myanimation.setCycleCount(1);
        myanimation.playFrom(new Duration(animState));
        anim.add(myanimation);

        //addAnimation
    }
}
