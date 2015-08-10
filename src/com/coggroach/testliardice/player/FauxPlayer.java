package com.coggroach.testliardice.player;

import java.util.List;

import com.coggroach.liardice.dice.DiceList;
import com.coggroach.liardice.player.IPlayer;

public class FauxPlayer implements IPlayer
{	
	private int id;
	public DiceList DiceList;
	
	public FauxPlayer(int i)
	{
		this.id = i;
		this.DiceList = new DiceList();
	}
	
	@Override
	public void update(DiceList list)
	{
		this.DiceList.save(list);
	}

	@Override
	public String getName()
	{
		return "FauxPlayer" + this.id;
	}

	@Override
	public boolean hasFolded()
	{
		return false;
	}

	@Override
	public void reset()
	{	
		this.DiceList.clear();
	}

	@Override
	public int getId()
	{
		return this.id;
	}

	@Override
	public void updateRoll(List<Integer> list)
	{
		
	}

	@Override
	public List<Integer> getRoll()
	{
		return null;
	}

	@Override
	public boolean isDeclaringFalse()
	{	
		return false;
	}

	@Override
	public void updateDeclare(boolean b)
	{
		
	}

	@Override
	public DiceList getBet()
	{
		return null;
	}

	@Override
	public int getScore()
	{
		return 0;
	}

	@Override
	public void addScore(int i)
	{
		
	}
}
