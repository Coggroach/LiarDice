package com.coggroach.testliardice.dice;

import org.junit.Test;
import org.junit.Assert;

import com.coggroach.liardice.dice.Dice;

public class TestDice 
{
	private Dice dice;	
	
	@Test
	public void Initialize()
	{
		this.dice = new Dice();
		
		Assert.assertFalse(this.dice.hasRolled());
		
		this.dice.roll();
		
		int value = this.dice.getValue();
		
		boolean condition = value > 0 && value <= 6;
		Assert.assertTrue(condition);
		Assert.assertFalse(this.dice.hasRolled());		
	}	
}
