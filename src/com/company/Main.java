package com.company;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public  static  void main(String[] args)throws IOException{
        GameStarter gameStarter = new GameStarter();
        gameStarter.onStart();
    }
}

class GameStarter {
    private Game game;

    GameStarter(){
        onStart();
    }

    public void onStart(){
        resumeGame();
        newGame();
    }
    private void resumeGame() {
        ArrayList<Game> savedGames ;
        //Show list of saved instances of game from a folder
    }

    private void newGame() {
        game = new Game();
        //Load a new Game instance with level 1
    }

    public static void deserialize()throws IOException, ClassNotFoundException {
    }
    public static void serialize()throws IOException, ClassNotFoundException {
    }
}

class Game implements Serializable {
    //private int currentLevel ;
    private final String name ;
    private Level ongoingLevel ;
    private GameStarter myStarter;

    public Level getOngoingLevel() {
        return ongoingLevel;
    }
    public void setOngoingLevel(Level ongoingLevel) {
        this.ongoingLevel = ongoingLevel;
    }
    public String getName() {
        return name;
    }
    public void setmyStarter(GameStarter gm){
        myStarter = gm;
    }
    /*public void setName(String name) {
        this.name = name;
    }*/
    /*public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }
    public int getCurrentLevel() {
        return currentLevel;
    }*/
    Game(){
        ongoingLevel = new Level1();
        name = "anamika";
    }

    public void pauseGame(){}
    public void resume(){}
    public void saveandExit(){}
    public void exitWithoutSave(){}
    public void startGame(){}
}

abstract class Level implements Serializable{
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


    protected class Courtyard {
        private final Plant[][] plants ;
        private final Boolean[] lawnMovers ;
        private final Zombie[][][] zombies;

        public Plant[][] getPlants() {
            return plants;
        }
        public Boolean[] getLawnMovers() {
            return lawnMovers;
        }
        public Zombie[][][] getZombies() {
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
}

class Level1 extends Level{
    Level1(){
        super();
    }
}
class Level2 extends Level{
    Level2(){
        super();
    }
}
class Level3 extends Level{
    Level3(){
        super();
    }
}
class Level4 extends Level{
    Level4(){
        super();
    }
}
class Level5 extends Level{
    Level5(){
        super();
    }
}









