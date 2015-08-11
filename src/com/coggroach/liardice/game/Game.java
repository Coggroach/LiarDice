package com.coggroach.liardice.game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.coggroach.liardice.dice.DiceBet;
import com.coggroach.liardice.dice.DiceList;
import com.coggroach.liardice.dice.DiceListHelper;
import com.coggroach.liardice.dice.DiceLogic;
import com.coggroach.liardice.dice.IBet;
import com.coggroach.liardice.player.IPlayer;
import com.coggroach.liardice.player.PlayerEvent;
import com.coggroach.liardice.player.PlayerListener;

public class Game implements PlayerListener, IGameStatus
{
	private List<IPlayer> players;
	private DiceList gameDiceList;
	private IPlayer currentPlayer;
	private Random rand;
	private GameStatus status;
	private int round;
	
	public Game()
	{
		this.players = new ArrayList<IPlayer>();
		this.gameDiceList = DiceListHelper.getInstance().getStandardDiceList();
		this.rand = new Random();
		this.currentPlayer = null;
		this.status = GameStatus.Starting;
	}
	
	public void addPlayer(IPlayer player)
	{
		this.players.add(player);
	}
	
	public void init()
	{
		this.currentPlayer = this.getFirstPlayer();
		this.gameDiceList.rollAll();
		this.status = GameStatus.Sending;
		this.round = 0;
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
		
		if(index >= this.players.size())
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
			if(iterator.next().hasFolded())
				i++;
		}		
		return i;
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
			case Sending:
				this.onSend();
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
	public void onPlayerDeclare(PlayerEvent o)
	{
		if(o.getPlayerId() == this.currentPlayer.getId())		
			if(this.currentPlayer.isDeclaring())
				this.status = GameStatus.Declaring;		
	}

	@Override
	public void onPlayerRoll(PlayerEvent o)
	{		
		if(o.getPlayerId() == this.currentPlayer.getId())				
			this.status = GameStatus.Rolling;	
	}

	@Override
	public void onPlayerBet(PlayerEvent o)
	{
		if(o.getPlayerId() == this.currentPlayer.getId())				
			this.status = GameStatus.Betting;		
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
		this.status = GameStatus.Sending;		
	}

	@Override
	public void onSend() throws Exception
	{
		this.currentPlayer.updateBet(this.gameDiceList);
		this.onWait();
	}

	@Override
	public void onDeclare() throws Exception
	{
		if(!this.currentPlayer.isDeclaring() || this.round <= 1) 
		{
			this.onWait();
			return;		
		}
		
		IPlayer lastPlayer = this.getLastPlayer();
		
		IBet last = lastPlayer.getBet();
		IBet curr = new DiceBet(); 
		curr.save(this.gameDiceList);
		
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
		this.status = GameStatus.Stopping;	
	}

	@Override
	public void onRoll() throws Exception
	{
		for(int i : this.currentPlayer.getRoll())
		{
			this.gameDiceList.roll(i);
		}		
		this.onWait();
	}

	@Override
	public void onBet() throws Exception
	{
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
