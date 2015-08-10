package com.coggroach.liardice.player;

import com.coggroach.liardice.dice.DiceList;

public interface IPlayer
{
	public void saveBet(DiceList list);
	public String getName();
	public boolean hasFolded();
	public void reset();
}
