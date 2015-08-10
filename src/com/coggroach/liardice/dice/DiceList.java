package com.coggroach.liardice.dice;

import java.util.ArrayList;

public class DiceList extends ArrayList<IDice>
{	
	private static final long serialVersionUID = 1L;

	public void save(DiceList list)
	{
		this.clear();
		this.addAll(list);
	}
		
	public void rollAll()
	{		
		for(int i = 0; i < this.size(); i++)		
			this.get(i).roll();		
	}
	
	public void cloneDices(IDice template, int count)
	{
		for(int i = 0; i < count; i++)
		{
			try
			{
				this.add(template.copy());
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public int getDiceValue(int i)
	{
		return this.get(i).getValue();
	}
	
	public void roll(int i)
	{
		this.get(i).roll();
	}
		
	public DiceLogic compare()
	{
		return DiceLogic.getLogicResult(this);
	}
}
