package PlantZombie;

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

interface Serialisable{

}





class Level1 extends Level implements Serialisable{
    Level1(){
        super();
    }
}
class Level2 extends Level implements Serialisable{
    Level2(){
        super();
    }
}
class Level3 extends Level implements Serialisable{
    Level3(){
        super();
    }
}
class Level4 extends Level implements Serialisable{
    Level4(){
        super();
    }
}
class Level5 extends Level implements Serialisable{
    Level5(){
        super();
    }
}













