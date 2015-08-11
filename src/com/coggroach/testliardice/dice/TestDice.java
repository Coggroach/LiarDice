package com.coggroach.testliardice.dice;

import org.junit.Assert;
import org.junit.Test;

import com.coggroach.liardice.dice.Dice;
import com.coggroach.liardice.dice.IDice;
import com.coggroach.liardice.faux.FauxDice;

public class TestDice 
{	
	@Test
	public void RollAndWithinRange()
	{
		this.AssertRollAndWithinRange(new Dice());	
	}	
	
	@Test
	public void FauxRollAndWithinRange()
	{
		this.AssertRollAndWithinRange(new FauxDice(3));
	}	
	
	private void AssertRollAndWithinRange(IDice dice)
	{
		Assert.assertFalse(dice.hasRolled());
		
		dice.roll();
		
		int value = dice.getValue();
		
		boolean condition = value > 0 && value <= 6;
		Assert.assertTrue(condition);
		Assert.assertTrue(dice.hasRolled());	
	}
}
