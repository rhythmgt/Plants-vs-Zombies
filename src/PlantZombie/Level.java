package PlantZombie;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Level implements Serialisable{
    private final boolean[] availablePlants ;
    private final ArrayList<Zombie> Zombie;
    private final Courtyard myCourtyard ;
    private int sunshineRate , currentTime;
    private final HashMap<Integer , Zombie> ZombieEntryTime ;

    public boolean[] getPlants() {
        return availablePlants;
    }

    public ArrayList<Zombie> getZombie() {
        return Zombie;
    }

    public Courtyard getCourtyard() {
        return myCourtyard;
    }

    public int getSunshineRate() {
        return sunshineRate;
    }

    public void setSunshineRate(int sunshineRate) {
        this.sunshineRate = sunshineRate;
    }

    public int getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(int currentTime) {
        this.currentTime = currentTime;
    }

    public HashMap<Integer, Zombie> getMapZombie() {
        return ZombieEntryTime;
    }

    Level(){
        this.myCourtyard = new Courtyard();
        this.availablePlants = new boolean[5];
        this.Zombie = new ArrayList<Zombie>();
        this.ZombieEntryTime = new HashMap<>();
    }
    public void myCourtyard(Courtyard courtyard){}
    public void sowNewPlant(){}
    public void useLawnMover() {}


    protected class Courtyard implements Serialisable{
        private final Character[][] plants ;
        private final Boolean[] lawnMovers ;
        private final Character[][][] zombies;

        public Character[][] getPlants() {
            return plants;
        }
        public Boolean[] getLawnMovers() {
            return lawnMovers;
        }
        public Character[][][] getZombies() {
            return zombies;
        }

        Courtyard(){
            plants = new Plant[5][9];
            lawnMovers = new Boolean[5];
            zombies = new Zombie[5][9][4];
        }

        void addPlant(Plant p, int i, int j){}
        void removePlant(int i, int j){}
        void ZombieEnter(Zombie m, int i){}
        void KillZombie(Zombie m){}
    }

    protected abstract class Character implements Serialisable{
        private int hp;
        private final int row;

        public int getHp() {
            return hp;
        }

        public void setHp(int hp) {
            this.hp = hp;
        }

        public int getRow() {
            return row;
        }

        Character(int i , int j){
            hp = i ;
            row = j ;
        }
        Character(int i){
            row = i ;
        }

        public boolean getAttacked(int i){
            return true;
        }
    }


    protected abstract class Plant extends Character implements Serialisable{
        private int Refresh_time;
        private final int dist_house;
        private int costing;

        public int getCosting() {
            return costing;
        }
        public void setCosting(int costing) {
            this.costing = costing;
        }

        public int getRefresh_time() {
            return Refresh_time;
        }

        public void setRefresh_time(int refresh_time) {
            Refresh_time = refresh_time;
        }

        public int getDist_house() {
            return dist_house;
        }

        public void getUnrooted(){}
        Plant(int i, int j){
            super(i, j);
            this.dist_house = j;
        }
    }
    protected abstract class DefensivePlants extends Plant implements Serialisable{
        DefensivePlants(int i, int j){
            super(i,j);
        }
    }
    protected class CherryBomb extends DefensivePlants implements Serialisable{
        CherryBomb(int i, int j) {
            super(i,j);
            //destroy surrounding zombies
        }
    }
    protected class PotatoMine extends DefensivePlants implements Serialisable{
        PotatoMine(int i, int j){
            super(i,j);
        }
        @Override
        public void getUnrooted() {
            //destroy zombies in the block
        }
    }
    protected class SunFlower extends DefensivePlants {
        SunFlower(int i, int j){
            super(i,j);
        }
    }
    protected class WallNut extends DefensivePlants {
        WallNut(int i, int j){
            super(i,j);
        }
    }
    protected abstract class AttackingPlants extends Plant {
        AttackingPlants(int i, int j){
            super(i,j);
        }
        abstract void shoot();
    }
    protected class PeaShooter extends AttackingPlants {
        PeaShooter(int i, int j){
            super(i,j);
        }
        @Override
        public void shoot(){}

    }


    protected abstract class Zombie extends Character implements Serialisable{
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
    }
    protected class Zombie1 extends Zombie implements Serialisable{
        Zombie1(int i){super(i);}
    }
    protected class FlagZombie extends Zombie implements Serialisable{
        FlagZombie(int i){super(i);}
    }
    protected class ConeHeadZombie extends Zombie implements Serialisable{
        ConeHeadZombie(int i){super(i);}
    }


}