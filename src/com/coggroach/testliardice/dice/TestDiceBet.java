package com.coggroach.testliardice.dice;

import org.junit.Assert;
import org.junit.Test;

import com.coggroach.liardice.dice.DiceList;
import com.coggroach.liardice.dice.DiceLogic;
import com.coggroach.liardice.dice.IBet;
import com.coggroach.liardice.faux.FauxDice;

public class TestDiceBet
{
	@Test
	public void Quintuple_4()
	{
		DiceList list = new DiceList();
		
		for(int i = 0; i < 5; i++)
		{			
			list.add(new FauxDice(4));
		}		
		IBet bet = DiceLogic.getDiceBet(list);
		Assert.assertTrue(bet.getDiceLogic() == DiceLogic.Quintuple);	
		Assert.assertTrue(bet.getValue() == 4);
	}
	
	@Test
	public void Quadriple_1()
	{
		DiceList list = new DiceList();
		
		list.add(new FauxDice(2));
		for(int i = 0; i < 4; i++)
		{			
			list.add(new FauxDice(1));
		}
		IBet bet = DiceLogic.getDiceBet(list);
		Assert.assertTrue(bet.getDiceLogic() == DiceLogic.Quadriple);	
		Assert.assertTrue(bet.getValue() == 1);
	}
	
	@Test
	public void Triple_6()
	{
		DiceList list = new DiceList();
		
		list.add(new FauxDice(2));
		list.add(new FauxDice(3));
		for(int i = 0; i < 3; i++)
		{			
			list.add(new FauxDice(6));
		}		
		IBet bet = DiceLogic.getDiceBet(list);
		Assert.assertTrue(bet.getDiceLogic() == DiceLogic.Triple);	
		Assert.assertTrue(bet.getValue() == 6);
	}
	
	@Test
	public void Pair_2()
	{
		DiceList list = new DiceList();
		
		list.add(new FauxDice(1));
		list.add(new FauxDice(3));
		list.add(new FauxDice(5));
		for(int i = 0; i < 2; i++)
		{			
			list.add(new FauxDice(2));
		}
		IBet bet = DiceLogic.getDiceBet(list);
		Assert.assertTrue(bet.getDiceLogic() == DiceLogic.Pair);	
		Assert.assertTrue(bet.getValue() == 2);		
	}
	
	@Test
	public void DoublePair_3()
	{
		DiceList list = new DiceList();
		
		list.add(new FauxDice(2));
		for(int i = 0; i < 2; i++)
		{			
			list.add(new FauxDice(1));
			list.add(new FauxDice(3));
		}
		IBet bet = DiceLogic.getDiceBet(list);
		Assert.assertTrue(bet.getDiceLogic() == DiceLogic.DoublePair);	
		Assert.assertTrue(bet.getValue() == 3);
	}
	
	@Test
	public void FullHouse_1()
	{
		DiceList list = new DiceList();
		
		list.add(new FauxDice(3));
		list.add(new FauxDice(3));
		for(int i = 0; i < 3; i++)
		{			
			list.add(new FauxDice(1));
		}
		IBet bet = DiceLogic.getDiceBet(list);
		Assert.assertTrue(bet.getDiceLogic() == DiceLogic.FullHouse);	
		Assert.assertTrue(bet.getValue() == 1);
	}
	
	@Test
	public void Flush_5()
	{
		DiceList list = new DiceList();
		
		for(int i = 1; i <= 5; i++)
		{			
			list.add(new FauxDice(i));
		}
		IBet bet = DiceLogic.getDiceBet(list);
		Assert.assertTrue(bet.getDiceLogic() == DiceLogic.Flush);	
		Assert.assertTrue(bet.getValue() == 5);
	}
	
	@Test
	public void High_6()
	{
		DiceList list = new DiceList();
		
		for(int i = 1; i <= 4; i++)
		{			
			list.add(new FauxDice(i));
		}		
		list.add(new FauxDice(6));
		IBet bet = DiceLogic.getDiceBet(list);
		Assert.assertTrue(bet.getDiceLogic() == DiceLogic.High);	
		Assert.assertTrue(bet.getValue() == 6);
	}
	
	@Test
	public void compareTo()
	{
		DiceList a = new DiceList();
		DiceList b = new DiceList();
		
		for(int i = 0; i < 5; i++)
		{			
			a.add(new FauxDice(5)); //Quintuple (5)
			b.add(new FauxDice(i+1)); //Flush (5)
		}
		IBet aBet = DiceLogic.getDiceBet(a);
		IBet bBet = DiceLogic.getDiceBet(b);
		
		Assert.assertTrue(aBet.compareTo(bBet) > 0);
		Assert.assertTrue(bBet.compareTo(aBet) < 0);
		Assert.assertTrue(aBet.compareTo(aBet) == 0);
		Assert.assertTrue(bBet.compareTo(bBet) == 0);
	}

}
