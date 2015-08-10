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
	
	
	private DiceList list;
	
	public DiceListHelper()
	{
		this.list = new DiceList();
		this.init();
	}
	
	private void init()	
	{
		for(int i = 0; i < 5; i++)
			this.list.add(new Dice());
		this.list.rollAll();
	}
	
	public DiceList getStandardDiceList()
	{
		return this.list;
	}
}
