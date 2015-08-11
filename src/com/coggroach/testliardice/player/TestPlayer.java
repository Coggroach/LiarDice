package com.coggroach.testliardice.player;

import org.junit.Assert;
import org.junit.Test;

import com.coggroach.liardice.faux.FauxPlayer;
import com.coggroach.liardice.player.IPlayer;
import com.coggroach.liardice.player.Player;

public class TestPlayer
{
	@Test
	public void onInvalidPlayer()
	{
		IPlayer player = new Player("RealPlayer", 0);
		IPlayer faux = new FauxPlayer(0);
		
		Assert.assertTrue(player instanceof Player);
		Assert.assertFalse(faux instanceof Player);
	}
	
}
