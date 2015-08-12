package com.coggroach.liardice.player;

import java.util.List;

import com.coggroach.liardice.dice.DiceList;
import com.coggroach.liardice.dice.IBet;

public interface IPlayer
{	
	public void addScore(int i);
	public IBet getBet();
	public int getId();
	public String getName();
	public DiceList getDiceList();
	public List<Integer> getRoll();
	public int getScore();
	//public boolean hasFolded();
	public IPlayer isDeclaring();
	public void reset();
	public void updateBet(IBet bet);
	public void updateDiceList(DiceList list);
	public void updateDeclare(IPlayer b);
	public void updateRoll(List<Integer> list);
}
