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
	
	public DiceBet(DiceList bet)
	{
		
	}

	@Override
	public void save(DiceList list)
	{
		this.bet = DiceLogic.getLogicResult(list);
	}
}
