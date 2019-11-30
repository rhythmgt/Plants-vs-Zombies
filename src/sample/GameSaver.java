package sample;

import javafx.scene.input.MouseEvent;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class GameSaver {

    public static Game restore(String s) throws IOException, ClassNotFoundException {
        Game g;
        ObjectInputStream in = null;
        String a  = s + ".ser";
        try{
            in = new ObjectInputStream(new FileInputStream(a));
            g = (Game) in.readObject();
            System.out.println("loaded");
            return g;
        }
        finally {
            in.close();

        }
    }

}
