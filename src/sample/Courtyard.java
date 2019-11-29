package sample;

import javafx.animation.Transition;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class Courtyard {
    private Label sunCount;
    private int numSunToken;
    private LawnMover[] lawnMovers;
    private volatile Plant[][] plants;
    private volatile ArrayList<CopyOnWriteArrayList<Zombie>> zombies;
    private double[] mybounds = new double[]{318.0, 427.0, 514.0, 625.0, 730.0, 825.0, 934.0, 1027.0, 1135.0, 1254.0};
    private final AnchorPane myParent;
    private ArrayList<Transition> myAnimations;
    private int getPlantingPosition(double d){
        if (d<mybounds[0]){
            return -1;
        }
        else {
            for (int i=1; i<10; i++){
                if (d<mybounds[i]){
                    return i ;
                }
            }
            return -1;
        }
    }

    Courtyard(int numRows, AnchorPane AP, ArrayList<Transition> anim, Label TokenValue){
        sunCount = TokenValue;
        lawnMovers = new LawnMover[numRows];

        plants = new Plant[numRows][9];
        zombies = new ArrayList<>();
        myParent = AP;
        myAnimations = anim;
        for (int i=0; i<numRows; i++){
            zombies.add(new CopyOnWriteArrayList<Zombie>());
        }
        initializeLandMovers();
        sunGenerator();
    }

    public LawnMover[] getLawnMovers() {
        return lawnMovers;
    }

    public Plant[][] getPlants() {
        return plants;
    }

    public ArrayList<CopyOnWriteArrayList<Zombie>> getZombies() {
        return zombies;
    }

    public void addPlantToList(Plant p, int i, int j){
        plants[i][j] = p;
    }
    public void addZombieToList(Zombie z, int i){
        zombies.get(i).add(z);
    }

    public void  addPlant(double d, int k){
        int pos = getPlantingPosition(d);
        if (pos!=-1){
            if (plants[0][pos-1] ==null ){
                double x2 = (mybounds[pos] + mybounds[pos-1])/2;
                switch (k){
                    case 1:
                    Plant sunflower = new SunFlower(0, pos-1,x2,330, myParent, this);
                    addPlantToList(sunflower, 0, pos-1);
                    break;
                    case 2:
                    Plant peaShooter = new PeaShooter(0,pos-1, x2,330, myParent, zombies,myAnimations, this);
                    addPlantToList(peaShooter, 0, pos-1);
                }
            }
        }
    }
    public void addZombie(){

        Zombie NZ = new NormalZombie(0, 1216, myParent, myAnimations, plants, this);
        addZombieToList(NZ,0);

    }
    public  void removeZombie(int i, Zombie z){

            zombies.get(i).remove(z);
            System.out.println("Zombie Removed");
    }

    public void removePlant(int i, int j){
        plants[i][j] = null;
        System.out.println("Plant Removed");
    }

    private void initializeLandMovers(){
        for (int i =0; i< lawnMovers.length; i++){
            lawnMovers[i] = new LawnMover(0, myParent, zombies, this);
        }
    }

    public void removeLandMover(int i){
        if (!(i>=this.lawnMovers.length || i<0)){
            lawnMovers[i] = null;
        }
    }

    public void sunGenerator(){
        Random rand = new Random();

        Transition sunTokenScheduler = new Transition() {
            {
                this.setCycleDuration(Duration.seconds(15));
                this.setCycleCount(INDEFINITE);
            }
            @Override
            protected void interpolate(double frac) {
                if (frac==0){
                    int x = 326 + rand.nextInt(200);
                    SunToken sun = new SunToken(x, 30, 350);
                }
            }
        };
        sunTokenScheduler.play();
    }

    public void changeSunValue(int i) {
        numSunToken += i;
        sunCount.setText(Integer.toString(numSunToken));
    }
    private class SunToken {
        Button myImage;
        SunToken(double x, double ystart, double yend){
            myImage = new Button();
            myImage.setOnMouseClicked(event -> {
                changeSunValue(100);
                myImage.setVisible(false);
                myParent.getChildren().remove(myImage);
            });
            double r=25;
            myImage.setShape(new Circle(r));
            myImage.setMinSize(2*r, 2*r);
            myImage.setMaxSize(2*r, 2*r);
            myImage.setLayoutX(x);
            myImage.setLayoutY(80.0);
            myImage.getStylesheets().add("/sample/sunStyle.css");
            myParent.getChildren().add(myImage);
            double difference = yend-ystart;
            Transition t = new Transition() {
                {
                    this.setCycleCount(1);
                    this.setCycleDuration(Duration.seconds(18));
                }
                @Override
                protected void interpolate(double frac) {
                    myImage.setLayoutY(ystart + frac*difference);
                }
            };
            t.play();
        }
    }

}
