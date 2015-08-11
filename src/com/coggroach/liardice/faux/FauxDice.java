package com.coggroach.liardice.faux;

import com.coggroach.liardice.dice.IDice;

public class FauxDice implements IDice
{
	public int Value;
		
	public FauxDice(int i)
	{
		this.Value = i;
	}
	
	@Override
	public int getValue()
	{
		return this.Value;
	}

	@Override
	public void roll()
	{
		this.Value = 6;
	}

	@Override
	public boolean hasRolled()
	{
		return this.Value == 6;
	}

	@Override
	public IDice copy() throws Exception
	{
		return new FauxDice(this.Value);
	}
}
