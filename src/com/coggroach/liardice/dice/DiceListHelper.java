package com.coggroach.liardice.dice;

public class DiceListHelper
{
	private static DiceListHelper instance;
	
	public static DiceListHelper getInstance()
	{
		return instance;
	}
	
	static 
	{
		instance = new DiceListHelper();
	}
	
	
	public DiceListHelper()
	{

	}
		
	public DiceList getStandardDiceList()
	{
		DiceList list = new DiceList();
		for(int i = 0; i < 5; i++)
			list.add(new Dice());
		list.rollAll();
		return list;
	}
}
