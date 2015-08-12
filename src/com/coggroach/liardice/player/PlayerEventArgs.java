package com.coggroach.liardice.player;

import java.util.List;

import com.coggroach.liardice.dice.IBet;

public class PlayerEventArgs 
{
	private IBet bet;
	private IPlayer declared;
	private List<Integer> rerolls;
	
	public PlayerEventArgs()
	{
		this.bet = null;
		this.declared = null;
		this.rerolls = null;
	}
	
	public PlayerEventArgs(IBet bet, IPlayer player, List<Integer> list)
	{
		this.bet = bet;
		this.declared = player;
		this.rerolls = list;
	}

	public IBet getBet()
	{
		return bet;
	}

	public void setBet(IBet bet)
	{
		this.bet = bet;
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

