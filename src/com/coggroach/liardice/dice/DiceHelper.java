package com.coggroach.liardice.dice;

import java.security.SecureRandom;

public class DiceHelper
{
	private static DiceHelper instance;
	private static int HandSize = 5;
	
	public static DiceHelper getInstance()
	{
		return instance;
	}
	
	static 
	{
		instance = new DiceHelper();
	}
	
	private SecureRandom rand;
	
	public DiceHelper()
	{
		this.rand = new SecureRandom();
	}
		
	public DiceList getStandardDiceList()
	{
		DiceList list = new DiceList();
		for(int i = 0; i < HandSize; i++)
			list.add(new Dice());
		list.rollAll();
		return list;
	}
	
	public int getRandomRoll()
	{
		return this.rand.nextInt(6) + 1;
	}
}
