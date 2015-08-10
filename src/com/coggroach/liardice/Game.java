package com.coggroach.liardice;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.coggroach.liardice.dice.DiceList;
import com.coggroach.liardice.dice.DiceListHelper;
import com.coggroach.liardice.dice.DiceLogic;
import com.coggroach.liardice.player.IPlayer;

public class Game
{
	private List<IPlayer> players;
	private DiceList gameDiceList;
	private IPlayer currentPlayer;
	private Random rand;
	private GameStatus status;
	
	public Game()
	{
		this.players = new ArrayList<IPlayer>();
		this.gameDiceList = DiceListHelper.getInstance().getStandardDiceList();
		this.rand = new Random();
		this.currentPlayer = null;
		this.status = GameStatus.Started;
	}
	
	public void addPlayer(IPlayer player)
	{
		this.players.add(player);
	}
	
	public void initialize()
	{
		this.currentPlayer = this.getFirstPlayer();
		this.gameDiceList.rollAll();
		this.status = GameStatus.Running;
	}
	
	public void updateCurrentPlayer() throws Exception
	{
		this.currentPlayer = this.getNextPlayer();
		this.currentPlayer.update(this.gameDiceList);		
	}
	
	public void updateGameStatus()
	{
		if(this.currentPlayer.isDeclaringFalse())
			this.status = GameStatus.Declaring;
	}
	
	public void updateDiceList()
	{
		List<Integer> list = this.currentPlayer.getRoll();
		
		for(int i : list)
			this.gameDiceList.roll(i);	
	}
	
	public void resolveGameStatus() throws Exception
	{
		if(this.status == GameStatus.Declaring)
		{
			this.resolveDeclaring();
			this.status = GameStatus.Stopped;
		}
	}
	
	private void resolveDeclaring() throws Exception
	{
		IPlayer lastPlayer = this.getLastPlayer();
		
		DiceLogic last = DiceLogic.getLogicResult(lastPlayer.getBet());
		DiceLogic curr = DiceLogic.getLogicResult(this.gameDiceList);
		
		if(last == curr)
		{
			this.currentPlayer.addScore(-2);
			lastPlayer.addScore(1);
		}
		else
		{
			lastPlayer.addScore(-2);
			this.currentPlayer.addScore(1);
		}
	}
	
	public IPlayer getPlayerFromId(int id) throws Exception
	{
		Iterator<IPlayer> iterator = this.players.iterator();
		IPlayer player = null;
		while(iterator.hasNext())
		{
			player = iterator.next();
			if(player.getId() == id)
				return player; 
		}
		throw new Exception("Player with Id not found");
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
	
	public IPlayer getCurrentPlayer()
	{
		return this.currentPlayer;
	}
	
	public GameStatus getGameStatus()
	{
		return this.status;
	}

	private IPlayer getNextPlayer() throws Exception
	{		
		int index = this.players.indexOf(this.currentPlayer) + 1;
		
		if(index >= this.gameDiceList.size())
			index = 0;
		
		IPlayer player = this.players.get(index);
		if(!player.hasFolded())
			return player;
		else if(this.foldedCount() == this.players.size())
			throw new Exception("All Players have Folded");
		else
		{
			this.currentPlayer = player;
			return this.getNextPlayer();
		}		
	}
	
	private IPlayer getLastPlayer() throws Exception
	{
		if(this.foldedCount() >= this.players.size() - 1)
			throw new Exception("All Players have Folded");
		
		return this.getLastPlayer(this.currentPlayer);
	}
	
	private IPlayer getLastPlayer(IPlayer currentPlayer)
	{
		int index = this.players.indexOf(this.currentPlayer) - 1;		
		
		if(index < 0)
			index = this.players.size() - 1;
		
		IPlayer player = this.players.get(index);
		if(!player.hasFolded())
			return player;
		else
			return this.getLastPlayer(player);
	}
	
	private int foldedCount()
	{
		Iterator<IPlayer> iterator = this.players.iterator();
		int i = 0;
		while(iterator.hasNext())
		{
			if(!iterator.next().hasFolded())
				i++;
		}		
		return i;
	}
}
