package com.coggroach.liardice.dice;

public class Dice implements IDice
{
	private int value;	

	public Dice()
	{
		this.value = 0;
	}

	@Override
	public int getValue()
	{
		return this.value;
	}

	@Override
	public void roll()
	{
		this.value = DiceHelper.getInstance().getRandomRoll();
	}

	@Override
	public boolean hasRolled()
	{
		return this.value != 0;
	}

	@Override
	public IDice copy() throws CloneNotSupportedException
	{
		return (IDice) this.clone();
	}
}
