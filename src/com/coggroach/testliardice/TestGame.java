package com.coggroach.testliardice;

import org.junit.Assert;
import org.junit.Test;

import com.coggroach.liardice.Game;

public class TestGame
{
	@Test
	public void createGame()
	{
		Game game = new Game();
		Assert.assertNotNull(game);
	}
	
	
}
