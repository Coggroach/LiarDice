package com.coggroach.liardice.player;

public interface PlayerListener
{
	public void onPlayerDeclare(PlayerEvent o);
	public void onPlayerRoll(PlayerEvent o);
	public void onPlayerBet(PlayerEvent o);	
}
