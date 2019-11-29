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
            isCollided();
        }

        void isCollided(){
            for (Plant enemy : myEnemies){
                if (enemy!=null && myImg.getBoundsInParent().intersects(enemy.getImage().getBoundsInParent())){
                    System.out.println("I will fight with the plant");
                    this.pause();
                }
            }
        }
    }
}