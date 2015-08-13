package com.coggroach.testliardice;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.coggroach.liardice.dice.DiceList;
import com.coggroach.liardice.dice.DiceLogic;
import com.coggroach.liardice.dice.IBet;
import com.coggroach.liardice.dice.IDice;
import com.coggroach.liardice.faux.FauxDice;
import com.coggroach.liardice.game.Game;
import com.coggroach.liardice.game.GameStatus;
import com.coggroach.liardice.player.IPlayer;
import com.coggroach.liardice.player.Player;
import com.coggroach.liardice.player.PlayerEventArgs;

public class GameMain
{
	static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args)
	{
		Game game = new Game();
		game.addPlayer(new Player("Player1", 0));
		game.addPlayer(new Player("Player2", 1));
		game.addPlayer(new Player("Player3", 2));
		PrintGame(game);
		
		game.init();
		
		PlayerEventArgs event = new PlayerEventArgs();
		
		
		while(game.getGameStatus() != GameStatus.Ending)
		{
			try 
			{
				IPlayer player = game.getCurrentPlayer();								
				
				if(game.getGameStatus() == GameStatus.Waiting)
				{
					PrintPlayer(player);
					PrintDiceList(player.getDiceList());
					
					//Declare	
					if(game.getRound() >= 1)
					{
						event.setDeclared(ParseDeclared(game));
						game.onPlayerDeclare(player, event);					
						game.update();	
						if(game.getGameStatus() == GameStatus.Restarting)
							continue;				
					}
					//Roll					
					event.setRoll(ParseRollList());										
					game.onPlayerRoll(player, event);
					game.update();
					PrintDiceList(player.getDiceList());
					
					//Bet		
					IBet bet = ParseBetList();
					while(!game.validateBet(bet))
					{
						System.err.println("Bid not High Enough.");
						bet = ParseBetList();
					}
					event.setBet(bet);					
					game.onPlayerBet(player, event);
					game.update();
				}
				game.update();				
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
			
		}
	}
	
	private static IPlayer ParseDeclared(Game game) throws Exception
	{
		System.out.print("Declare: ");
		int id = Integer.parseInt(scanner.nextLine());
		try {
			return game.getPlayerFromId(id);
		}
		catch(Exception ex)
		{
			System.err.println(ex.getMessage());
			return null;	
		}
	}
	
	private static List<Integer> ParseRollList()
	{
		System.out.print("Reroll: ");
		String roll = scanner.nextLine();
		String[] input = roll.replace(" ", "").split(",");
		List<Integer> list = new ArrayList<Integer>();
		
		for(int i = 0; i < input.length; i++)
			if(!input[i].isEmpty())
				list.add(Integer.parseInt(input[i]));
		return list;
	}
	
	private static IBet ParseBetList()
	{
		System.out.print("Bet: ");
		String bet = scanner.nextLine();
		String[] dice = bet.trim().split(" ");
		DiceList betList = new DiceList();
		for(int i = 0; i < dice.length; i++)
			if(!dice[i].isEmpty())
				betList.add(new FauxDice( Integer.parseInt(dice[i]) ));
		return DiceLogic.getDiceBet(betList);
	}
	
	private static void PrintGame(Game game)
	{
		System.out.println(game.getGameStatus().toString());		
	}
	
	private static void PrintPlayer(IPlayer player)
	{
		System.out.print(player.getName());
		System.out.println(":" + player.getScore());
	}
	
	private static void PrintDiceList(DiceList list)
	{
		for(IDice i : list)
		{
			System.out.print(i.getValue() + " ");
		}
		System.out.println();
		IBet bet = DiceLogic.getDiceBet(list);
		System.out.println(bet.getDiceLogic().toString() + " (" +  bet.getValue() + ")");
	}
}
