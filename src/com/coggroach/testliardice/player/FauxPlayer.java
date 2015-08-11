package com.coggroach.testliardice.player;

import java.util.List;

import com.coggroach.liardice.dice.DiceList;
import com.coggroach.liardice.dice.IBet;
import com.coggroach.liardice.player.IPlayer;
import com.coggroach.testliardice.dice.FauxBet;

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
	public boolean hasFolded()
	{
		return false;
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
	public boolean isDeclaring()
	{	
		return false;
	}

	@Override
	public void updateDeclare(boolean b)
	{
		
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
	public void updateBet(DiceList list)
	{
		// TODO Auto-generated method stub
		
	}
}
