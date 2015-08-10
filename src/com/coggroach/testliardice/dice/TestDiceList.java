package com.coggroach.testliardice.dice;

import org.junit.Assert;
import org.junit.Test;

import com.coggroach.liardice.dice.Dice;
import com.coggroach.liardice.dice.DiceList;

public class TestDiceList
{
	private DiceList list;
	
	@Test
	public void Initialize()
	{
		this.list = new DiceList();
		
		Assert.assertTrue(this.list.size() == 0);
		
		Dice dice = new Dice();
		dice.roll();
		
		this.list.add(dice);
		
		Assert.assertTrue(this.list.size() == 1);		
	}
}
