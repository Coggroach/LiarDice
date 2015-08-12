package com.coggroach.liardice.dice;

public interface IBet extends Comparable<IBet>
{
	void save(DiceList list);
	DiceLogic getDiceLogic();
	int getValue();
}
