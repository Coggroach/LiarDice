package com.coggroach.testliardice;

import java.util.Scanner;

import com.coggroach.liardice.Game;
import com.coggroach.liardice.GameStatus;
import com.coggroach.liardice.dice.DiceList;
import com.coggroach.liardice.dice.IDice;
import com.coggroach.liardice.player.IPlayer;
import com.coggroach.liardice.player.Player;

public class TestGame
{
	static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args)
	{
		System.out.println("Starting...");
		Game game = new Game();
		game.addPlayer(new Player("Player1", 0));
		game.addPlayer(new Player("Player2", 1));
		game.addPlayer(new Player("Player3", 2));
		PrintGame(game);
		
		while(game.getGameStatus() != GameStatus.Stopped)
		{
			try
			{
				game.updateCurrentPlayer();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			
			IPlayer player = game.getCurrentPlayer();
			PrintPlayer(player);
			
			DiceList dice = game.getDiceList();
			PrintDiceList(dice);
			break;
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
