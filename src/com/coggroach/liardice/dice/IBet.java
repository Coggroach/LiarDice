package com.coggroach.liardice.dice;

public interface IBet
{
	void save(DiceList list);
	DiceLogic getDiceLogic();
	int getValue();
}
