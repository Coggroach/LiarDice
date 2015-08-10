package com.coggroach.liardice.player;

import com.coggroach.liardice.dice.DiceList;

public class Player implements IPlayer
{
	private DiceList bet;
	private String name;
	
	public Player(String name)
	{
		this.name = name;
		this.bet = new DiceList();
	}
	
	@Override
	public void saveBet(DiceList list)
	{
		bet.clear();
		bet.save(list);
	}
}
