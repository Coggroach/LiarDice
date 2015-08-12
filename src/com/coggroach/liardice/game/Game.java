package com.coggroach.liardice.game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.coggroach.liardice.dice.DiceBet;
import com.coggroach.liardice.dice.DiceList;
import com.coggroach.liardice.dice.IBet;
import com.coggroach.liardice.player.IPlayer;
import com.coggroach.liardice.player.PlayerEvent;
import com.coggroach.liardice.player.PlayerEventArgs;

public class Game implements PlayerEvent, IGameStatus
{
	private List<IPlayer> players;
	private IPlayer currentPlayer;
	private IBet tableBet;
	private Random rand;
	private GameStatus status;
	private int round;
	
	public Game()
	{
		this.players = new ArrayList<IPlayer>();		
		this.rand = new Random();
		this.currentPlayer = null;
		this.tableBet = null;
		this.status = GameStatus.Starting;
	}
	
	public void addPlayer(IPlayer player)
	{
		this.players.add(player);
	}
	
	public void init()
	{
		this.currentPlayer = this.getFirstPlayer();		
		this.status = GameStatus.Starting;
		this.round = 0;
		for(IPlayer player : this.players)
			player.reset();
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
		if(this.currentPlayer == null)
			throw new Exception("Game not Initialized.");
		if(this.players.size() <= 0)
			throw new Exception("Game has no Registered Players.");
		
		return this.getNextPlayer(this.currentPlayer);
	}
	
	private IPlayer getNextPlayer(IPlayer currentPlayer)
	{
		int index = this.players.indexOf(this.currentPlayer) + 1;
		
		if(index >= this.players.size())
			index = 0;
		
		return this.players.get(index);
	}
	
	public boolean validateBet(IBet bet)
	{
		if(this.tableBet == null)
			return true;
		
		return bet.compareTo(this.tableBet) > 0;
	}
	
	public int getRound()
	{
		return round;
	}

	private IPlayer getLastPlayer() throws Exception
	{
		return this.getLastPlayer(this.currentPlayer);
	}
	
	private IPlayer getLastPlayer(IPlayer currentPlayer)
	{
		int index = this.players.indexOf(this.currentPlayer) - 1;		
		
		if(index < 0)
			index = this.players.size() - 1;	
		
		return this.players.get(index);
	}
			
	public void update() throws Exception
	{
		switch(this.status)
		{
			case Betting:
				this.onBet();
				break;
			case Declaring:
				this.onDeclare();				
				break;
			case Ending:
				this.onEnd();
				break;
			case Moving:
				this.onMove();				
				break;
			case Restarting:
				this.onRestart();
				break;
			case Rolling:
				this.onRoll();
				break;
			case Starting:
				this.onStart();
				break;
			case Stopping:
				this.onStop();
				break;
			case Waiting:
				this.onWait();
				break;
			default:
				break;			
		}
		//System.out.println("Changing GameStatus to: " + this.status.toString());
	}


	@Override
	public void onPlayerDeclare(Object sender, PlayerEventArgs o)
	{
		if(sender instanceof IPlayer)
			if( ((IPlayer) sender).getId() == this.currentPlayer.getId())
			{								
				this.currentPlayer.updateDeclare(o.isDeclared());
				if(this.currentPlayer.isDeclaring() != null)
					this.status = GameStatus.Declaring;
			}
					
	}

	@Override
	public void onPlayerRoll(Object sender, PlayerEventArgs o)
	{		
		if(sender instanceof IPlayer)
			if( ((IPlayer) sender).getId() == this.currentPlayer.getId())
			{
				this.currentPlayer.updateRoll(o.getRoll());
				this.status = GameStatus.Rolling;
			}				
	}

	@Override
	public void onPlayerBet(Object sender, PlayerEventArgs o)
	{
		if(sender instanceof IPlayer)
			if( ((IPlayer) sender).getId() == this.currentPlayer.getId())
			{
				this.currentPlayer.updateBet(o.getBet());
				this.status = GameStatus.Betting;			
			}
	}

	@Override
	public void onStart() throws Exception
	{
		this.status = GameStatus.Moving;
	}

	@Override
	public void onMove() throws Exception
	{
		this.currentPlayer = this.getNextPlayer();		
		this.onWait();	
	}

	@Override
	public void onDeclare() throws Exception
	{
		if(this.currentPlayer.isDeclaring() == null || this.round <= 1) 
		{
			this.currentPlayer.updateDeclare(null);
			this.onWait();
			return;		
		}
		
		IPlayer lastPlayer = this.getLastPlayer();		
		
		IBet curr = new DiceBet(); 
		curr.save(this.currentPlayer.getDiceList());
		
		if(this.tableBet.compareTo(curr) >= 0)
		{
			this.currentPlayer.addScore(-2);
			lastPlayer.addScore(1);
		}
		else
		{
			lastPlayer.addScore(-2);
			this.currentPlayer.addScore(1);
		}
		this.status = GameStatus.Stopping;	
	}

	@Override
	public void onRoll() throws Exception
	{
		DiceList dice = this.currentPlayer.getDiceList();
		for(int i : this.currentPlayer.getRoll())
		{
			dice.roll(i);
		}		
		this.onWait();
	}

	@Override
	public void onBet() throws Exception
	{
		this.tableBet = this.currentPlayer.getBet();
		this.status = GameStatus.Stopping;	
	}

	@Override
	public void onStop() throws Exception
	{
		this.status = GameStatus.Starting;
		this.round++;
	}

	@Override
	public void onRestart() throws Exception
	{
		this.init();		
	}

	@Override
	public void onEnd() throws Exception
	{
		
	}

	@Override
	public void onWait() throws Exception
	{
		this.status = GameStatus.Waiting;
	}
}
