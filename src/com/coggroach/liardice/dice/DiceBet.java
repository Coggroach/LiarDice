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
		this.bet = DiceLogic.getLogicResult(list);
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
}
