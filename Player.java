package com.coggroach.dice;

public class Player
{
	private DiceList bet;
	private String name;
	
	public Player(String name)
	{
		this.name = name;
		this.bet = new DiceList(5);
	}
	
	public void saveBet(DiceList list)
	{
		bet.removeAll();
		bet.save(list);
	}
}
