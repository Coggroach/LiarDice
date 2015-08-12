package com.coggroach.liardice.player;

import java.util.ArrayList;
import java.util.List;

import com.coggroach.liardice.dice.DiceBet;
import com.coggroach.liardice.dice.DiceList;
import com.coggroach.liardice.dice.DiceHelper;
import com.coggroach.liardice.dice.IBet;

public class Player implements IPlayer
{
	private IBet bet;
	private DiceList dice;
	private String name;
	private int id;
	private List<Integer> diceToReRoll;
	private IPlayer declaredFalse;
	private int score;
	
	public Player(String name, int id)
	{
		this.name = name;
		this.id = id;
		this.score = 0;
		this.dice = new DiceList();
		this.dice.addAll(DiceHelper.getInstance().getStandardDiceList());
		this.reset();
	}

	@Override
	public String getName()
	{		
		return this.name;
	}

	@Override
	public void reset()
	{
		this.bet = new DiceBet();
		this.diceToReRoll = new ArrayList<Integer>();
		this.declaredFalse = null;
		this.dice.rollAll();
	}

	@Override
	public int getId()
	{
		return this.id;
	}

	@Override
	public void updateRoll(List<Integer> list)
	{
		this.diceToReRoll.addAll(list);
	}

	@Override
	public List<Integer> getRoll()
	{
		return this.diceToReRoll;
	}

	@Override
	public IPlayer isDeclaring()
	{
		return this.declaredFalse;
	}

	@Override
	public void updateDeclare(IPlayer b)
	{
		this.declaredFalse = b;
	}

	@Override
	public IBet getBet()
	{
		return this.bet;
	}

	@Override
	public int getScore()
	{
		return this.score;
	}

	@Override
	public void addScore(int i)
	{
		this.score += i;
	}

	@Override
	public void updateBet(IBet bet)
	{
		this.bet = bet;
	}

	@Override
	public void updateDiceList(DiceList list)
	{
		this.dice = list;
	}

	@Override
	public DiceList getDiceList()
	{
		return this.dice;
	}
}
