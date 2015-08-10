package com.coggroach.liardice.player;

import java.util.List;

import com.coggroach.liardice.dice.DiceList;

public interface IPlayer
{	
	public String getName();
	public boolean hasFolded();
	public void reset();
	public int getId();
	public void update(DiceList list);
	public void updateRoll(List<Integer> list);
	public List<Integer> getRoll();
	public boolean isDeclaringFalse();
	public void updateDeclare(boolean b);
	public DiceList getBet();
	public int getScore();
	public void addScore(int i);
}
