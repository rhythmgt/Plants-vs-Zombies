package sample;

import javafx.animation.Transition;
import javafx.util.Duration;

import javax.swing.text.html.ImageView;
import java.io.Serializable;
import java.util.ArrayList;

abstract class Zombie extends Character implements Serializable{
    private int status;
    private int distanceRemaining;
    private int speed;

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

    Zombie(int i) {
        super(i);
    }
        public void getAttacked(){
    }

    @Override
    public void killMe(){
        setHp(0);
        myParent.getChildren().remove(myImg);
        myanimation.stop();
        myCourtyard.removeZombie(getRow(),this);
    }

    private void attackPlant(Plant p){
        Transition t = new Transition() {
            {
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