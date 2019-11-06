package PlantZombie;

import java.io.Serializable;

public class Game implements Serializable {
	private String name ;
	private Level ongoingLevel ;
	transient private GameStarter myStarter;

	public Level getOngoingLevel() {
		return ongoingLevel;
	}
	public void setOngoingLevel(Level ongoingLevel) {
		this.ongoingLevel = ongoingLevel;
	}
	public String getName() {
		return name;
	}
	/*public void setmyStarter(GameStarter gm){
		myStarter = gm;
	}*/
    public void setName(String name) {
        this.name = name;
    }
    /*public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }
    public int getCurrentLevel() {
        return currentLevel;
    }*/
	public Game(){
		ongoingLevel = new Level1();
		name = "anamika";
		//myStarter = new GameStarter();
	}

	public void pauseGame(){}
	public void resume(){}
	public void saveandExit(){}
	public void exitWithoutSave(){}
	public void startGame(){}
}
