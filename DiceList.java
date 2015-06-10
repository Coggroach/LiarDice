package com.coggroach.dice;

public class DiceList
{
	public static final int ONE_DOUBLE = 0;
	public static final int ONE_TRIPLE = 1;
	public static final int TWO_DOUBLES = 2;
	public static final int ONE_QUADRIPLE = 3;
	public static final int ONE_FULLHOUSE = 4;
	public static final int ONE_STRAIGHT = 5;
	public static final int ONE_QUINTUPLE = 6;
	public static final int ONE_NONE = -1;
	
	private List<Dice> dice;
	
	public DiceList()
	{
		this.dice = new ArrayList<Dice>();
	}
	
	public DiceList(int i)
	{
		this.dice = new ArrayList<Dice>(i);
	}
	
	public void save(DiceList list)
	{
		this.dice.addAll(list.dice);
	}
	
	public void removeAll()
	{
		this.dice.removeAll();
	}
	
	public void rollAll()
	{
		for(Dice d : dice)
		{
			d.roll();
		}
	}
	
	public void roll(int i)
	{
		this.dice.get(i).roll();
	}
	
	public int compare()
	{
		
		return ONE_NONE;
	}
	
	private int[] getFrequencyTable()
	{
		int[] list = new int[] {0, 0, 0, 0, 0, 0};
		
		for(Dice d : dice)
		{
			list[d.getValue()]++;
		}
		return list;
	}
	
	private int getMostFrequent()
	{
		int[] list = getFrequencyTable();	
		int maxVal = list[0];
		int maxFreq = 0;
		
		for(int i = 1; i < list.length; i++)
		{
			if(maxVal < list[i])
			{
				maxVal = list[i];
				maxFreq = i;
			}
		}
		return maxFreq;
	}
}
