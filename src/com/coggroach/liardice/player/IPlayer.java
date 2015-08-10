package com.coggroach.liardice.player;

import java.util.List;

import com.coggroach.liardice.dice.DiceList;

public interface IPlayer
{	
	public void addScore(int i);
	public DiceList getBet();
	public int getId();
	public String getName();
	public List<Integer> getRoll();
	public int getScore();
	public boolean hasFolded();
	public boolean isDeclaringFalse();
	public void reset();
	public void update(DiceList list);
	public void updateDeclare(boolean b);
	public void updateRoll(List<Integer> list);
}
