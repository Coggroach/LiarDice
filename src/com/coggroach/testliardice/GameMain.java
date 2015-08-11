package com.coggroach.testliardice;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.coggroach.liardice.dice.DiceList;
import com.coggroach.liardice.dice.IDice;
import com.coggroach.liardice.faux.FauxDice;
import com.coggroach.liardice.game.Game;
import com.coggroach.liardice.game.GameStatus;
import com.coggroach.liardice.player.IPlayer;
import com.coggroach.liardice.player.Player;
import com.coggroach.liardice.player.PlayerEvent;

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
		
		while(game.getGameStatus() != GameStatus.Ending)
		{
			try 
			{
				IPlayer player = game.getCurrentPlayer();
				PlayerEvent event = new PlayerEvent(player);				
				
				if(game.getGameStatus() == GameStatus.Waiting)
				{
					PrintPlayer(player);
					PrintDiceList(game.getDiceList());
					
					//Declare
					System.out.println("Declaring: ");					
					String declare = scanner.nextLine();
					player.updateDeclare(Boolean.parseBoolean(declare));
					game.onPlayerDeclare(event);					
					game.update();	
					if(game.getGameStatus() == GameStatus.Stopping)
						break;
					
					
					//Roll
					System.out.println("Reroll: ");
					String roll = scanner.nextLine();
					String[] input = roll.replace(" ", "").split(",");
					List<Integer> list = new ArrayList<Integer>();
					
					for(int i = 0; i < input.length; i++)
						if(!input[i].isEmpty())
							list.add(Integer.parseInt(input[i]));
					
					player.updateRoll(list);
					game.onPlayerRoll(event);
					game.update();
					
					//Bet
					String bet = scanner.nextLine();
					String[] dice = bet.trim().split(" ");
					DiceList betList = new DiceList();
					for(int i = 0; i < dice.length; i++)
						if(!dice[i].isEmpty())
							betList.add(new FauxDice( Integer.parseInt(dice[i]) ));
					
					player.updateBet(betList);					
					game.onPlayerBet(event);
					game.update();
				}
				game.update();	
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
			
		}
	}
	
	private static void PrintGame(Game game)
	{
		System.out.println(game.getGameStatus().toString());		
	}
	
	private static void PrintPlayer(IPlayer player)
	{
		System.out.println(player.getName());
		System.out.println(player.getScore());
	}
	
	private static void PrintDiceList(DiceList list)
	{
		for(IDice i : list)
		{
			System.out.print(i.getValue() + " ");
		}
		System.out.println();
	}
}
