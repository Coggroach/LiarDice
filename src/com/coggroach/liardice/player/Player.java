package com.coggroach.liardice.player;

import com.coggroach.liardice.dice.DiceList;

public class Player implements IPlayer
{
	private DiceList bet;
	private String name;
	private boolean folded;
	
	public Player(String name)
	{
		this.name = name;
		this.reset();
	}
	
	@Override
	public void saveBet(DiceList list)
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
	}
}
