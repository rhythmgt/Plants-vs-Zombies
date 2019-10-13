package com.company;

abstract class Zombie implements Serialisable{
	private int hp;
	private int status;
	private final int targetRow;
	private int distanceRemaining;
	private int speed;

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getTargetRow() {
		return targetRow;
	}

	public int getDistanceRemaining() {
		return distanceRemaining;
	}

	public void setDistanceRemaining(int distanceRemaining) {
		this.distanceRemaining = distanceRemaining;
	}

	Zombie(int i) {
		targetRow = i;
	}
}

class Zombie1 extends Zombie implements Serialisable{
	Zombie1(int i){super(i);}
}
class FlagZombie extends Zombie implements Serialisable{
	FlagZombie(int i){super(i);}
}
class ConeHeadZombie extends Zombie implements Serialisable{
	ConeHeadZombie(int i){super(i);}
}