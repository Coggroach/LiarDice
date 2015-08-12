package com.coggroach.liardice.player;

import java.util.List;

import com.coggroach.liardice.dice.DiceList;

public class PlayerEventArgs
{
	private DiceList diceList;
	private IPlayer declared;
	private List<Integer> rerolls;
	
	public PlayerEventArgs()
	{
		this.diceList = null;
		this.declared = null;
		this.rerolls = null;
	}

	public DiceList getDiceList()
	{
		return diceList;
	}

	public void setDiceList(DiceList diceList)
	{
		this.diceList = diceList;
	}

	public IPlayer isDeclared()
	{
		return declared;
	}

	public void setDeclared(IPlayer declared)
	{
		this.declared = declared;
	}

	public List<Integer> getRoll()
	{
		return rerolls;
	}

	public void setRoll(List<Integer> rerolls)
	{
		this.rerolls = rerolls;
	}
}

