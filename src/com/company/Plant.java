package com.company;

abstract class Plant implements Serialisable{
	private int Refresh_time;
	private final int dist_house;
	private int hp;
	private int costing;
	private final int row;

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


	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public void getUnrooted(){}
	Plant(int i, int j){
		this.row=i;
		this.dist_house = j;
	}
}
abstract class DefensivePlants extends Plant implements Serialisable{
	DefensivePlants(int i, int j){
		super(i,j);
	}
}
class CherryBomb extends DefensivePlants implements Serialisable{
	CherryBomb(int i, int j) {
		super(i,j);
		//destroy surrounding zombies

	}
}

class PotatoMine extends DefensivePlants implements Serialisable{
	PotatoMine(int i, int j){
		super(i,j);
	}
	@Override
	public void getUnrooted() {
		//destroy zombies in the block
	}
}



class SunFlower extends DefensivePlants{
	SunFlower(int i, int j){
		super(i,j);
	}
}
class WallNut extends DefensivePlants{
	WallNut(int i, int j){
		super(i,j);
	}
}

abstract class AttackingPlants extends Plant {
	AttackingPlants(int i, int j){
		super(i,j);
	}
	abstract void shoot();
}

class PeaShooter extends AttackingPlants{
	PeaShooter(int i, int j){
		super(i,j);
	}
	@Override
	public void shoot(){}
}