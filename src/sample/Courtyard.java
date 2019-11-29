package sample;

import javafx.animation.Transition;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.util.ArrayList;

public class Courtyard {
    private LawnMover[] lawnMovers;
    private Plant[][] plants;
    private ArrayList<ArrayList<Zombie>> zombies;
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

    Courtyard(int numRows, AnchorPane AP, ArrayList<Transition> anim){
        lawnMovers = new LawnMover[numRows];
        plants = new Plant[numRows][9];
        zombies = new ArrayList<>();
        myParent = AP;
        myAnimations = anim;
        for (int i=0; i<numRows; i++){
            zombies.add(new ArrayList<Zombie>());
        }
    }

    public LawnMover[] getLawnMovers() {
        return lawnMovers;
    }

    public Plant[][] getPlants() {
        return plants;
    }

    public ArrayList<ArrayList<Zombie>> getZombies() {
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
                    Plant sunflower = new SunFlower(x2,330, myParent);
                    addPlantToList(sunflower, 0, pos-1);
                    break;
                    case 2:
                    Plant peaShooter = new PeaShooter(0,pos-1, x2,330, myParent, zombies,myAnimations);
                    addPlantToList(peaShooter, 0, pos-1);
                }
            }
        }
    }
    public void addZombie(){

        Zombie NZ = new NormalZombie(0, 1216, myParent, myAnimations, plants);
        addZombieToList(NZ,0);

    }

}
