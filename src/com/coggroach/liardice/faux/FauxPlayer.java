package com.coggroach.liardice.faux;

import java.util.List;

import com.coggroach.liardice.dice.DiceList;
import com.coggroach.liardice.dice.IBet;
import com.coggroach.liardice.player.IPlayer;

public class FauxPlayer implements IPlayer
{	
	private int id;
	public IBet bet;
	
	public FauxPlayer(int i)
	{
		this.id = i;
		this.bet = new FauxBet();
	}
	
	@Override
	public String getName()
	{
		return "FauxPlayer" + this.id;
	}
	
	@Override
	public void reset()
	{	
		this.bet = null;
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
	public IBet getBet()
	{
		return this.bet;
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

	@Override
	public void updateBet(IBet bet)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateDiceList(DiceList list)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public DiceList getDiceList()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IPlayer isDeclaring()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateDeclare(IPlayer b)
	{
		// TODO Auto-generated method stub
		
	}
}
