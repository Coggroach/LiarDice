package com.coggroach.testliardice.dice;

import org.junit.Assert;
import org.junit.Test;

import com.coggroach.liardice.dice.DiceList;
import com.coggroach.liardice.dice.DiceLogic;
import com.coggroach.liardice.faux.FauxDice;

public class TestDiceLogic
{
	@Test
	public void Quintuple()
	{
		DiceList list = new DiceList();
		
		for(int i = 0; i < 5; i++)
		{			
			list.add(new FauxDice(1));
		}		
		Assert.assertTrue(DiceLogic.getLogicResult(list) == DiceLogic.Quintuple);	
	}
	
	@Test
	public void Quadriple()
	{
		DiceList list = new DiceList();
		
		list.add(new FauxDice(2));
		for(int i = 0; i < 4; i++)
		{			
			list.add(new FauxDice(1));
		}		
		Assert.assertTrue(DiceLogic.getLogicResult(list) == DiceLogic.Quadriple);
	}
	
	@Test
	public void Triple()
	{
		DiceList list = new DiceList();
		
		list.add(new FauxDice(2));
		list.add(new FauxDice(3));
		for(int i = 0; i < 3; i++)
		{			
			list.add(new FauxDice(1));
		}		
		Assert.assertTrue(DiceLogic.getLogicResult(list) == DiceLogic.Triple);
	}
	
	@Test
	public void Pair()
	{
		DiceList list = new DiceList();
		
		list.add(new FauxDice(2));
		list.add(new FauxDice(3));
		list.add(new FauxDice(5));
		for(int i = 0; i < 2; i++)
		{			
			list.add(new FauxDice(1));
		}		
		Assert.assertTrue(DiceLogic.getLogicResult(list) == DiceLogic.Pair);
	}
	
	@Test
	public void DoublePair()
	{
		DiceList list = new DiceList();
		
		list.add(new FauxDice(2));
		for(int i = 0; i < 2; i++)
		{			
			list.add(new FauxDice(1));
			list.add(new FauxDice(3));
		}		
		Assert.assertTrue(DiceLogic.getLogicResult(list) == DiceLogic.DoublePair);
	}
	
	@Test
	public void FullHouse()
	{
		DiceList list = new DiceList();
		
		list.add(new FauxDice(3));
		list.add(new FauxDice(3));
		for(int i = 0; i < 3; i++)
		{			
			list.add(new FauxDice(1));
		}		
		Assert.assertTrue(DiceLogic.getLogicResult(list) == DiceLogic.FullHouse);
	}
	
	@Test
	public void Flush()
	{
		DiceList list = new DiceList();
		
		for(int i = 1; i <= 5; i++)
		{			
			list.add(new FauxDice(i));
		}		
		Assert.assertTrue(DiceLogic.getLogicResult(list) == DiceLogic.Flush);
	}
	
	@Test
	public void High()
	{
		DiceList list = new DiceList();
		
		for(int i = 1; i <= 4; i++)
		{			
			list.add(new FauxDice(i));
		}		
		list.add(new FauxDice(6));
		
		Assert.assertTrue(DiceLogic.getLogicResult(list) == DiceLogic.High);
	}
}
