package com.coggroach.liardice;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.coggroach.liardice.dice.DiceList;
import com.coggroach.liardice.dice.DiceListHelper;
import com.coggroach.liardice.player.IPlayer;

public class Game
{
	private List<IPlayer> players;
	private DiceList gameDiceList;
	private IPlayer currentPlayer;
	private Random rand;
	
	public Game()
	{
		this.players = new ArrayList<IPlayer>();
		this.gameDiceList = DiceListHelper.getInstance().getStandardDiceList();
		this.rand = new Random();
		this.currentPlayer = null;
	}
	
	public void addPlayer(IPlayer player)
	{
		this.players.add(player);
	}
	
	public void initialize()
	{
		this.currentPlayer = this.getFirstPlayer();
		this.gameDiceList.rollAll();
	}
	
	public void tick() throws Exception
	{	
		this.currentPlayer = this.getNextPlayer();
		
		//TODO Player Receives DiceList
		//TODO Player Decides Reroll
		//TODO Player places bet/folds		
	}
	
	private IPlayer getFirstPlayer()
	{
		return this.players.get(this.rand.nextInt(this.players.size()));
	}
	
	public List<IPlayer> getPlayers()
	{
		return this.players;
	}

	public DiceList getDiceList()
	{
		return this.gameDiceList;
	}

	private IPlayer getNextPlayer() throws Exception
	{		
		int index = this.players.indexOf(this.currentPlayer) + 1;
		
		if(index >= this.gameDiceList.size())
			index = 0;
		
		IPlayer player = this.players.get(index);
		if(!player.hasFolded())
			return player;
		else if(this.havePlayersFolded())
			throw new Exception("All Players have Folded");
		else
		{
			this.currentPlayer = player;
			return this.getNextPlayer();
		}
		
	}
	
	private boolean havePlayersFolded()
	{
		Iterator<IPlayer> iterator = this.players.iterator();
		while(iterator.hasNext())
		{
			if(!iterator.next().hasFolded())
				return false;
		}		
		return true;
	}
}
