package sample;

import java.io.Serializable;

public  abstract class Plant extends Character implements Serializable {
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