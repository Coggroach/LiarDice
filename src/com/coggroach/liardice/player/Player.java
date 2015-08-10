package com.coggroach.liardice.player;

import java.util.ArrayList;
import java.util.List;

import com.coggroach.liardice.dice.DiceList;

public class Player implements IPlayer
{
	private DiceList bet;
	private String name;
	private boolean folded;
	private int id;
	private List<Integer> diceToReRoll;
	private boolean declaredFalse;
	private int score;
	
	public Player(String name, int id)
	{
		this.name = name;
		this.id = id;
		this.score = 0;
		this.reset();
	}
	
	@Override
	public void update(DiceList list)
	{
		bet.save(list);
	}

	@Override
	public String getName()
	{		
		return this.name;
	}

	@Override
	public boolean hasFolded()
	{
		return this.folded;
	}

	@Override
	public void reset()
	{
		this.folded = false;
		this.bet = new DiceList();
		this.diceToReRoll = new ArrayList<Integer>();
		this.declaredFalse = false;		
	}

	@Override
	public int getId()
	{
		return this.id;
	}

	@Override
	public void updateRoll(List<Integer> list)
	{
		this.diceToReRoll.addAll(list);
	}

	@Override
	public List<Integer> getRoll()
	{
		return this.diceToReRoll;
	}

	@Override
	public boolean isDeclaringFalse()
	{
		return this.declaredFalse;
	}

	@Override
	public void updateDeclare(boolean b)
	{
		this.declaredFalse = b;
	}

	@Override
	public DiceList getBet()
	{
		return this.bet;
	}

	@Override
	public int getScore()
	{
		return this.score;
	}

	@Override
	public void addScore(int i)
	{
		this.score += i;
	}
}
