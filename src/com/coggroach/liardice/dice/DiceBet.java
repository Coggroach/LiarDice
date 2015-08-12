package com.coggroach.liardice.dice;

public class DiceBet implements IBet
{
	private DiceLogic bet;
	private int value;
	
	
	public DiceBet()
	{
		this.bet = null;
		this.value = 0;
	}
	
	public DiceBet(DiceLogic bet, int val)
	{
		this.bet = bet;
		this.value = val;
	}

	@Override
	public void save(DiceList list)
	{
		IBet bet = DiceLogic.getDiceBet(list);
		this.bet = bet.getDiceLogic();
		this.value = bet.getValue();
	}

	@Override
	public DiceLogic getDiceLogic()
	{
		return this.bet;
	}

	@Override
	public int getValue()
	{
		return this.value;
	}

	@Override
	public int compareTo(IBet o)
	{
		float oProb = o.getDiceLogic().getProbability();
		float tProb = this.bet.getProbability();
		
		if(Math.abs(oProb - tProb) <= 0.000001)		
			return value - o.getValue();		
		
		return (int) (-tProb + oProb);
	}
}
