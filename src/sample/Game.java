package sample;


import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Game implements Serializable {
    private String name ;
    private Courtyard[] myLevels;
    private Courtyard currentLevel;
    private int selectedLevel;

    public String getName(){
        return this.name;
    }

    public void setName(String nm){
        name = nm;
    }

    public void addCourtyard(int level, Courtyard yard){
        myLevels[level-1] = yard;
    }

    public void removeCourtyard(int level){
        myLevels[level-1] = null;
    }

    public Courtyard getCourtyard(int level){
        return myLevels[level-1];
    }

    Game(){
        name = "defaultName";
        myLevels = new Courtyard[5];
    }

    public void Serialize(MouseEvent mouseEvent) throws IOException {
        System.out.println("Called me");
        ObjectOutputStream out = null;
        try{
            String name = this.getName()+".ser";
            out = new ObjectOutputStream(
                    new FileOutputStream(name)

            );
            out.writeObject(this);
            System.out.printf("Saved as %s\n", name);
        }
        finally {
            out.close();
            ((Stage) ((Node)mouseEvent.getSource()).getScene().getWindow()).close();

        }
    }
}
