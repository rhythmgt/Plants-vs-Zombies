package sample;

import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.Serializable;
import java.util.ArrayList;

abstract class Zombie extends Character implements Serializable{
    private int status;
    private int distanceRemaining;
    private int speed;
    protected Image moving1;
    protected Image fightImage1;
    protected Image fightImage2;
    protected Image moving2;

    protected LawnMover targetLandMover;
    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getDistanceRemaining() {
        return distanceRemaining;
    }

    public void setDistanceRemaining(int distanceRemaining) {
        this.distanceRemaining = distanceRemaining;
    }

    Zombie(int i, int maxhp, AnchorPane parent, Courtyard yard) {
        super(i);
        this.maxHp = maxhp;
        this.setHp(maxhp);
        myParent = parent;
        this.myCourtyard = yard;

    }

    @Override
    public void getAttacked(int i){
        this.hp -= i;
        if (hp<=0){
            killMe();
        }
        if (hp<0.5*maxHp){
            myImg.setImage(moving2);
        }
    }

    @Override
    public void killMe(){
        setHp(0);
        myParent.getChildren().remove(myImg);
        myanimation.stop();
        myCourtyard.removeZombie(getRow(),this);
    }

    protected void attackPlant(Plant p){
        Transition t = new Transition() {
            {
                if (getHp()<0.5*maxHp){
                    myImg.setImage(fightImage2);
                }
                else{
                    myImg.setImage(fightImage1);
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
                            p.getAttacked(10);
                            System.out.println("attacking");
                        }
                    }
                    else{
                        this.stop();
                        if (hp<0.5*maxHp){
                            myImg.setImage(moving2);
                        }
                        else{
                            myImg.setImage(moving1);
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
        moveZombieAnimation(double startPoint, double endPoint, Plant[] Enemies){
            setCycleDuration(Duration.seconds(50));
            position = startPoint;
            difference =endPoint-startPoint;
            myEnemies = Enemies;

        }

        @Override
        protected void interpolate(double frac) {

            myImg.setLayoutX(position + (difference*frac));
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
}