package sample;

import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class Courtyard implements Serializable {
    private transient AnchorPane endMenu;
    protected transient AnchorPane winMenu;
    private boolean isLocked;
    private transient Label sunCount;
    private int numSunToken;
    private LawnMover[] lawnMovers;
    private volatile Plant[][] plants;
    private volatile ArrayList<CopyOnWriteArrayList<Zombie>> zombies;
    protected double[] mybounds = new double[]{318.0, 427.0, 514.0, 625.0, 730.0, 825.0, 934.0, 1027.0, 1135.0, 1254.0};
    protected double[] vertBounds;
    protected double animState;
    protected int animationState ;
    private transient AnchorPane myParent;
    protected transient ArrayList<Transition> myAnimations;
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

    protected int getVerticalPlantingPos(double d){
        if (d<vertBounds[0]){
            return -1;
        }
        else{
            for (int i=1; i<vertBounds.length; i++){
                if (d<vertBounds[i]){
                    return i;
                }
            }
            return -1;
        }

    }

    public int getNumSunToken(){
        return this.numSunToken;
    }

    Courtyard(int numRows, AnchorPane AP, ArrayList<Transition> anim, Label TokenValue, AnchorPane menu ){
        sunCount = TokenValue;
        lawnMovers = new LawnMover[numRows];
        endMenu = menu;
        plants = new Plant[numRows][9];
        zombies = new ArrayList<>();
        myParent = AP;
        myAnimations = anim;
        for (int i=0; i<numRows; i++){
            zombies.add(new CopyOnWriteArrayList<Zombie>());
        }
        //initializeLandMovers();
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
        System.out.println("Added to List");
    }
    public void addZombieToList(Zombie z, int i){
        zombies.get(i).add(z);
    }

    public boolean  addPlant(double d,double g, int k){
        int pos = getPlantingPosition(d);
        int vertpos = getVerticalPlantingPos(g);
        if (pos!=-1){
            if (plants[vertpos-1][pos-1] == null ){
                double x2 = (mybounds[pos] + mybounds[pos-1])/2;
                double y2 = (vertBounds[vertpos] + vertBounds[vertpos-1])/2;
                switch (k){
                    case 1:
                        if (numSunToken>=50){
                    Plant sunflower = new SunFlower(vertpos-1, pos-1,x2,y2-65, myParent, this);
                    addPlantToList(sunflower, vertpos-1, pos-1);
                    Transition t = new Transition() {
                        {
                            this.setCycleCount(INDEFINITE);
                            this.setCycleDuration(Duration.seconds(10));
                        }
                        @Override
                        protected void interpolate(double frac) {
                            if (frac==0){
                                SunToken st = new SunToken(x2,y2-65,y2-75);
                            }
                        }
                    };
                    t.play();
                    myAnimations.add(t);
                    sunflower.setMyanimation(t);
                        changeSunValue(-50);
                            return true;}

                    break;
                    case 2:
                        if (numSunToken>=100){
                    Plant peaShooter = new PeaShooter(vertpos-1,pos-1, x2,y2-65, myParent, zombies,myAnimations, this);
                    addPlantToList(peaShooter, vertpos-1, pos-1);
                        changeSunValue(-100);
                            return true;}
                        break;
                    case 3:

                        if (numSunToken>=50){
                            Plant wallnut = new Wallnut(vertpos-1,pos-1, x2,y2-65, myParent, this);
                            addPlantToList(wallnut, vertpos-1, pos-1);
                            changeSunValue(-50);
                            return true;}
                        break;
                    case 4:
                        if (numSunToken>=150){
                            Plant bomb = new CherryBomb(vertpos-1,pos-1, x2,y2-65, myParent, this, zombies);
                            addPlantToList(bomb, vertpos-1, pos-1);
                            changeSunValue(-150);
                            return true;}
                        break;
                    case 5:
                        if (numSunToken>=150){
                            Plant bomb = new PotatoMine(vertpos-1,pos-1, x2,y2-65, myParent, this, zombies);
                            addPlantToList(bomb, vertpos-1, pos-1);
                            changeSunValue(-150);
                        return true;}
                }
            }


        }
        return false;
    }
    public void addZombie(int k){
        double y1 = (vertBounds[k+1] + vertBounds[k])/2;

        Zombie NZ = new NormalZombie(k, 1216, y1-65, myParent, myAnimations, plants, this);
        addZombieToList(NZ,k);

    }
    public  void removeZombie(int i, Zombie z) throws IOException {

            zombies.get(i).remove(z);
            System.out.println("Zombie Removed "+zombies.size()+"  anim  : "+animationState);
            if(animationState==0){
                Boolean b = true;
                for (CopyOnWriteArrayList<Zombie> zl : zombies){
                    b = b && (zl.size()==0);
                }
                if (b){
                gamewon();}
            }
    }

    public void gamewon() throws IOException {
        System.out.println("GAME WON");
        for(int i=0 ; i<myAnimations.size() ; i++)
            if(myAnimations.get(i).getStatus() == Animation.Status.RUNNING)
                myAnimations.get(i).stop();
        winMenu.setVisible(true);

        winMenu.toFront();

    }

    public void removePlant(int i, int j){
        if (plants[i][j]!=null){
        plants[i][j] = null;
        System.out.println("Plant Removed");}
    }

    protected void initializeLandMovers(){
        double y = 330;
        for (int i =0; i< lawnMovers.length; i++){
            y = (vertBounds[i+1]+vertBounds[i])/2;
            lawnMovers[i] = new LawnMover(i, y-70,myParent, zombies, this);
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
        myAnimations.add(sunTokenScheduler);
    }

    public void changeSunValue(int i) {
        numSunToken += i;
        sunCount.setText(Integer.toString(numSunToken));
    }
    public class SunToken {
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
            myAnimations.add(t);
        }
    }
    public void gameLost(){
        for(int i=0 ; i<myAnimations.size() ; i++)
            if(myAnimations.get(i).getStatus() == Animation.Status.RUNNING)
                myAnimations.get(i).stop();
        endMenu.setVisible(true);
        endMenu.toFront();
    }



    public void reInitialize(AnchorPane pn, Label TokenValue, AnchorPane menu, ArrayList<Transition> anim , AnchorPane winMenu){
        myParent = pn;
        sunCount = TokenValue;
        this.endMenu = menu;
        this.winMenu = winMenu;
        myAnimations = anim;
        for (Plant[] i : plants){
            for (Plant j : i){
                if (j!= null){
                    j.restoreMe(pn, myAnimations);
                }
            }
        }
        for (CopyOnWriteArrayList<Zombie> zmL : zombies){
            for (Zombie z : zmL){
                if (z!=null){
                    z.restoreMe(pn, myAnimations);
                }
            }
        }
        for (LawnMover lm : lawnMovers){
            if (lm!=null){
                lm.restoreMe(pn);
            }
        }
        sunGenerator();
        restoreZombieCreator();
        sunCount.setText(Integer.toString(numSunToken));
    }

    abstract void restoreZombieCreator();
}
